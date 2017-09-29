package com.connecText;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.DataSource;
import sql.SQL;

/**
 *
 * @author grant page, alexa chiu, abigail reed, michael ellison, peter braband
 */
@ManagedBean
@Named(value = "userBean")
//@Dependent
@SessionScoped
public class userBean {
    
    @Resource(name = "jdbc/projectdb")
    DataSource dataSource;

    /**
     * Creates a new instance of userBean
     */
    
    //Private Fields
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password1; // for registration purposes
    private String password2; // for registration purposes
    private String loginPassword; // for login attempt
    
    private String returning_email;
    private String returning_password;
    private Boolean alreadyExists = false;
    private Boolean passwordsMatch = true;
    private ResultSet userLoginRow = null; // the table to be filled during login
    private user currentUser;
    
    private Boolean loggedIn = false;
    private Boolean badEmail = false;
    private Boolean badPassword = false;
    
    public userBean() {
    }
    
    // Adding a user
    public String addUser() throws Exception {
            //check to see if they're already in the database
            Boolean forLogin = false;
            if(userExists(/*forLogin*/)){
                alreadyExists = true;
                return "registration";
            }
            
            // check to see if passwords match
            if(!password1.equals(password2)){
                passwordsMatch = false;
                return "registration";
            }
            //add user
            int rowCount = 0;
            try(Connection con = dataSource.getConnection()){
                String update = SQL.getSQL("person-insert");
                PreparedStatement ps = con.prepareStatement(update);
                ps.setString(1, this.firstName);
                ps.setString(2, this.lastName);
                ps.setString(3, this.password1);
                ps.setString(4, this.phoneNumber);
                ps.setString(5, this.email);
                rowCount = ps.executeUpdate();
            }
            catch(SQLException se){
                return se.getMessage();
            }
            catch(Exception e){
                return e.getMessage();
            }
            if(rowCount > 0){
                if(currentUser == null) currentUser = new user();
                currentUser.setEmail(this.email);
                currentUser.setFirst(firstName);
                currentUser.setLast(lastName);
                currentUser.setPassword(password1);
                currentUser.setPhoneNumber(phoneNumber);
            }
        return "profile";
    }
    
    public String login() throws Exception {
        String return_val = "index";
        //PreparedStatement ps = null;
        ResultSet rs = null;
        try(Connection con = dataSource.getConnection()) {
            /*if ((this.userExists("email", email) && 
                    this.userExists("password", password1)) == true) {
                */
            //check if we have email
            Boolean forLogin = true;
            if(this.userExists(/*forLogin*/)){ // we do
                
                badEmail = false;
                //String query = SQL.getSQL("person-query");
                //PreparedStatement ps = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //ps.setString(1, email);
                //rs = ps.executeQuery();
                //check password
                //if(rs.first()){
                if(userLoginRow.first()){  
                if(this.loginPassword.equals(userLoginRow.getString("password"))){
                        loggedIn = true;
                        badPassword = false;
                        return_val = "profile";
                    }
                    else {
                        loggedIn = false;
                        badPassword = true;
                    }
                }
                if(currentUser == null) currentUser = new user();
                if(userLoginRow.first()){
                currentUser.setEmail(userLoginRow.getString("email"));
                currentUser.setFirst(userLoginRow.getString("first_name"));
                currentUser.setLast(userLoginRow.getString("last_name")); 
                }
            }
            else badEmail = true;
        }
        catch(SQLException se){
            return "sql excePTION"+se.getMessage();
        }
        catch(Exception e){
            return e.getMessage();
        }
        return return_val;
    }
    
    public String logout() {
        this.currentUser = null;
        email = "";
        password1="";
        password2="";
        firstName="";
        lastName="";
        phoneNumber="";
        loggedIn=false;
        return "index";
    }
    
    public String removeAction(String email, String title){
        try(Connection con = dataSource.getConnection()){
                String update = SQL.getSQL("book-delete");
                PreparedStatement ps = con.prepareStatement(update);
                ps.setString(1, email);
                ps.setString(2, title);
                int rowCount = ps.executeUpdate();
            }
            catch(SQLException se){
                return se.getMessage();
            }
            catch(Exception e){
                return e.getMessage();
            }
        return "profile";
    }

    /*
    PREVOUSLY CALLED SEARCHUSER
    A function that returns true if the user is found and false otherwise
    */
    private Boolean userExists(/*Boolean forLogin*/) throws Exception {
        Boolean return_val = false;
        ResultSet rs = null;
        try(Connection con = dataSource.getConnection()){
            String query = SQL.getSQL("person-query");
            PreparedStatement ps = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, this.email);
            rs = ps.executeQuery();
            if(rs != null){
                userLoginRow = rs;
                return_val = true;
            }
        }
        catch(SQLException se){
            
        }
        catch(Exception e){
        }
        
        if(rs != null){ 
        if(!rs.first()) return_val = false; //result set returned 0 rows. email wasn't in database
        }
        return return_val;
    }
    
    public List<book> getListedBooks() throws SQLException{
      ResultSet rs = null;
      List<book> inventory = new ArrayList<book>();
      
      try(Connection con = dataSource.getConnection()){
        String query = SQL.getSQL("inventory-query");
        PreparedStatement pStmt = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        pStmt.setString(1, this.email); // FIXME! Need currentUser.email() to work
        rs = pStmt.executeQuery();
        
      }
      catch(SQLException se){
          inventory.add(new book("SQLException", se.getMessage(), "in getListedBooks", "141", "0", "0", "0"));
                  
      }
        catch(Exception e){
            inventory.add(new book("Exception", e.getMessage(), "in getListedBooks", "144", "0", "0", "0"));
        }
        if(rs != null){
        while(rs.next()) {
                
                    inventory.add(new book(
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("forClass"),
                    rs.getString("isbn"),
                    rs.getString("edition"),
                    rs.getString("owner"),
                    rs.getString("price")
                    ));
            }
        }
        return inventory;
    }
    
    // Setters and Getters
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setPassword1(String password1) {
        this.password1 = password1;
    }
    
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setAlreadyExists(Boolean val){
        this.alreadyExists = val;
    }
    
    public Boolean getAlreadyExists(){
        return alreadyExists;
    }
    
    public void setPasswordsMatch(Boolean val){
        passwordsMatch = val;
    }
    
    public Boolean getPasswordsMatch(){
        return passwordsMatch;
    }
    
    public Boolean alreadyExists(){
        return alreadyExists;
    }
    
    public Boolean passwordsMatch(){
        return passwordsMatch;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword1() {
        return password1;
    }
    
    public String getPassword2() {
        return password2;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public user getCurrentUser() {
        return currentUser;
    }
    
    public Boolean getLoggedIn() {
        return loggedIn;
    }
    
    public Boolean getBadEmail(){
        return badEmail;
    }
    
    public Boolean getBadPassword(){
        return badPassword;
    }
    
    public void setBadPassword(Boolean pass){
        this.badPassword = pass;
    }
     
    public void setLoginPassword(String pass){
        this.loginPassword = pass;
    }
    
    public String getLoginPassword(){
        return loginPassword;
    }
    
}
