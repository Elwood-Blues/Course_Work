package com.connecText;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.sql.*;
import java.util.Arrays;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Named(value = "database")
@ApplicationScoped
public class Database {

    private final static int ITERATION_NUMBER = 1000;

    @Resource(name = "jdbc/databaseDB")
    private DataSource dataSource;

    public Database() {
    }

    public void addToFavorites(String name, int id)
            throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM favorites WHERE name = ? AND plan_id = ?");
            ps.setString(1, name);
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                try (Connection con2 = dataSource.getConnection()) {
                    ps = con2.prepareStatement("INSERT INTO favorites (name, plan_id) VALUES (?,?)");
                    ps.setString(1, name);
                    ps.setInt(2, id);
                    ps.executeUpdate();
                }
            }
        }
    }

//    public List<book> getFavoritesForUser(String username)
//            throws SQLException, IOException {
//        String query = "select * from plans where \"id\" in (select plan_id from favorites where name = ?)ORDER BY \"monthy_price\" ASC";
//        try (Connection conn = dataSource.getConnection()) {
//            PreparedStatement statement = conn.prepareStatement(query);
//            statement.setString(1, username);
//            ResultSet resultSet = statement.executeQuery();
//            List<book> list = new ArrayList<>();
//            while (resultSet.next()) {
//                list.add(new book());
//            }
//            return list;
//        }
//    }

    /**
     * Authenticates the user with a given login and password. If password
     * and/or login is null then always returns false. If the user does not
     * exist in the database returns false.
     *
     * @param login String The login of the user
     * @param password String The password of the user
     * @return boolean Returns true if the user is authenticated, false
     * otherwise
     * @throws SQLException If the database is inconsistent or unavailable (
     * (Two users with the same login, salt or digested password altered etc.)
     * @throws NoSuchAlgorithmException If the algorithm SHA-1 is not supported
     * by the JVM
     */
    public boolean authenticate(String login, String password)
            throws SQLException, NoSuchAlgorithmException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try (Connection con = dataSource.getConnection()) {
            boolean userExist = true;
            // INPUT VALIDATION
            if (login == null || password == null) {
                // TIME RESISTANT ATTACK
                // Computation time is equal to the time needed by a legitimate user
                userExist = false;
                login = "";
                password = "";
            }

            ps = con.prepareStatement("SELECT password, salt FROM users WHERE name = ?");
            ps.setString(1, login);
            rs = ps.executeQuery();
            String digest, salt;
            if (rs.next()) {
                digest = rs.getString("PASSWORD");
                salt = rs.getString("SALT");
                // DATABASE VALIDATION
                if (digest == null || salt == null) {
                    throw new SQLException("Database inconsistant Salt or Digested Password altered");
                }
                if (rs.next()) { // Should not append, because login is the primary key
                    throw new SQLException("Database inconsistent two CREDENTIALS with the same LOGIN");
                }
            } else { // TIME RESISTANT ATTACK (Even if the user does not exist the
                // Computation time is equal to the time needed for a legitimate user
                digest = "000000000000000000000000000=";
                salt = "00000000000=";
                userExist = false;
            }

            byte[] bDigest = base64ToByte(digest);
            byte[] bSalt = base64ToByte(salt);

            // Compute the new DIGEST
            byte[] proposedDigest = getHash(ITERATION_NUMBER, password, bSalt);

            return Arrays.equals(proposedDigest, bDigest) && userExist;
        } catch (IOException ex) {
            throw new SQLException("Database inconsistant Salt or Digested Password altered");
        }
    }

    /**
     * Inserts a new user in the database
     *
     * @param login String The login of the user
     * @param password String The password of the user
     * @return boolean Returns true if the login and password are ok (not null
     * and length(login)<=100
     * @throws SQLException If the database is unavailable
     * @throws NoSuchAlgorithmException If the algorithm SHA-1 or the
     * SecureRandom is not supported by the JVM
     * @throws java.io.UnsupportedEncodingException
     */
    public boolean createUser(String login, String password)
            throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        PreparedStatement ps = null;
        try (Connection con = dataSource.getConnection()) {
            if (login != null && password != null && login.length() <= 100) {
                // Uses a secure Random not a simple Random
                SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
                // Salt generation 64 bits long
                byte[] bSalt = new byte[8];
                random.nextBytes(bSalt);
                // Digest computation
                byte[] bDigest = getHash(ITERATION_NUMBER, password, bSalt);
                String sDigest = byteToBase64(bDigest);
                String sSalt = byteToBase64(bSalt);

                ps = con.prepareStatement("INSERT INTO users (name, password, salt) VALUES (?,?,?)");
                ps.setString(1, login);
                ps.setString(2, sDigest);
                ps.setString(3, sSalt);
                ps.executeUpdate();
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * From a password, a number of iterations and a salt, returns the
     * corresponding digest
     *
     * @param iterationNb int The number of iterations of the algorithm
     * @param password String The password to encrypt
     * @param salt byte[] The salt
     * @return byte[] The digested password
     * @throws NoSuchAlgorithmException If the algorithm doesn't exist
     * @throws java.io.UnsupportedEncodingException
     */
    public byte[] getHash(int iterationNb, String password, byte[] salt)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.reset();
        digest.update(salt);
        byte[] input = digest.digest(password.getBytes("UTF-8"));
        for (int i = 0; i < iterationNb; i++) {
            digest.reset();
            input = digest.digest(input);
        }
        return input;
    }

    /**
     * From a base 64 representation, returns the corresponding byte[]
     *
     * @param data String The base64 representation
     * @return byte[]
     * @throws IOException
     */
    public static byte[] base64ToByte(String data) throws IOException {
        return Base64.getDecoder().decode(data);
    }

    /**
     * From a byte[] returns a base 64 representation
     *
     * @param data byte[]
     * @return String
     */
    public static String byteToBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
}
