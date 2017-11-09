import oracle.jdbc.pool.OracleDataSource;

import javax.swing.*;
import java.sql.*;
import java.util.Calendar;

public class Lab8 {

    // For security reasons, never PUT YOUR PASSWORD
    // IN YOUR SOURCE FILE LIKE THIS:
    // String password = "This is my password. Now, steal my money!";
    private static Connection conn;
    //ENUM of acceptable makes in DB
    public enum  MAKES {TOYOTA, HONDA, ACURA, VOLKSWAGEN}

    /**
     * Helper function to determine whether or not the user provided input is within the accepted values
     * @param m - the user provided string that identifies the vehicle 'make'
     * @return T/F indicating whether or not the provided string is an acceptable vehicle 'make'
     */
    private static boolean isInMakes(String m) throws IllegalArgumentException{
        //String cur_make = m.toUpperCase();
        try {
            switch (Enum.valueOf(MAKES.class, m)){
                case ACURA:
                case HONDA:
                case TOYOTA:
                case VOLKSWAGEN:
                    return true;
                default:
                    return false;
            }
        }catch (IllegalArgumentException e){
            System.out.println("Not a valid 'Make'!");
            return false;
        }

    }

    /**
     * This class gets the current DB connection. This is not to be used in
     * production environments. You should use a connection pool instead.
     *
     * @return current connection object
     * @throws SQLException If connection fails SQLException is thrown
     */
    private static Connection getDBConnection() throws SQLException {
        OracleDataSource ds = new OracleDataSource();
        String jdbcUrl = "jdbc:oracle:thin:@akka.d.umn.edu:1521:xe";
        ds.setURL(jdbcUrl);
        if(conn == null) {
            // Display a message to get the password from the user
            JLabel label = new JLabel("Oracle Username: ");
            JTextField jtf = new JTextField();
            JLabel label2 = new JLabel("Oracle Password:");
            JPasswordField jpf = new JPasswordField();
            JOptionPane.showConfirmDialog(null,
                    new Object[]{label, jtf, label2, jpf}, "Password:",
                    JOptionPane.OK_CANCEL_OPTION);

            //char[] input = jpf.getPassword();
            //System.out.println(String.valueOf(input));

            String password = String.valueOf(jpf.getPassword());
            conn = ds.getConnection(jtf.getText(), password );
        }
        conn.setAutoCommit(true);
        System.out.println("Connection established!");
        return conn;
    }


    /**
     * Method to insert a vehicle, prompts the user for VIN, MAKE, and POLICY_NUM
     * Checks MAKE to ensure its an acceptable value before executing insertion
     * @return T/F whether or not insert was successful
     * @throws SQLException Throws whenever an sql error is encountered
     */
    public static boolean insertVehicle() throws SQLException {

        //User Input Section
        JLabel vinLabel = new JLabel("Enter Vehicle VIN: ");
        JTextField vinJtf = new JTextField();
        JLabel makeLabel = new JLabel("Enter Vehicle Make:");
        JTextField makeJtf = new JTextField();
        JLabel policyLabel = new JLabel("Enter the vehicle policy Num: ");
        JTextField policyJtf = new JTextField();
        JOptionPane.showConfirmDialog(null,
                new Object[]{vinLabel, vinJtf, makeLabel, makeJtf, policyLabel, policyJtf}, "Enter Vehicle Details:",
                JOptionPane.OK_CANCEL_OPTION);

        //convert user input to SQL submission form
        String vin = vinJtf.getText().toUpperCase();
        String make = makeJtf.getText().toUpperCase();
        int policy_num = 0;
        if("".equals(policyJtf.toString()) || policyJtf.toString() == null){
            System.out.println("Invalid input for 'Policy'");
        }else {
            try {
                policy_num = Integer.parseInt(policyJtf.getText());
            }catch(NumberFormatException e) {
                System.out.println("Invalid Policy Number!");
                e.printStackTrace();
            }
        }

        assert(vin != null);
        assert (make != null);
        assert (policy_num != 0);
        int updatedRows;
        boolean insertSuccess = false;//default: false

        //provided make is an acceptable value proceed with insertion
        if (isInMakes(make)){
            getDBConnection();
            String vehInsertQuery = "INSERT INTO vehicle VALUES(?,?,?)";
            String vehQuery = "SELECT * FROM vehicle";

            try(PreparedStatement sqlInsertVeh = conn.prepareStatement(vehInsertQuery);
                PreparedStatement sqlVehQuery = conn.prepareStatement(vehQuery)) {
                conn.setAutoCommit(false);
                sqlInsertVeh.setString(1,vin);
                sqlInsertVeh.setString(2,make);
                sqlInsertVeh.setInt(3,policy_num);

                updatedRows = sqlInsertVeh.executeUpdate();
                if (updatedRows > 0){
                    conn.commit();
                    ResultSet rs = sqlVehQuery.executeQuery();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Insertion of row ('" + vin + "', '" + make + "', '" + policy_num + "') successful.\n");
                    sb.append("The 'Vehicle' table after insertion now looks like: \n");
                    sb.append("Vin \t\tMake \t\tPolicy Number\n");
                    String _vin;
                    String _make;
                    int _policy_num;

                    //Loop through result set and append to output string
                    while(rs.next()){
                        _vin = rs.getString(1);
                        _make = rs.getString(2);
                        _policy_num = rs.getInt(3);
                        sb.append(_vin + "\t\t");
                        sb.append(_make.equalsIgnoreCase(MAKES.VOLKSWAGEN.toString()) ? (_make + "\t") : (_make + "\t\t"));
                        sb.append(_policy_num + "\n");
                    }
                    System.out.print(sb.toString());
                    insertSuccess = true;
                }

            }
            catch (SQLException e){
                // Roll back the transaction
                conn.rollback();
                e.printStackTrace();
            }finally {
                conn.setAutoCommit(true);
            }
        }

        return insertSuccess;
    }

    /**
     * Method to associate a given vehicle vin with a given vehicle accident ID,
     * both values are provided by the user via prompts
     * @return T/F whether or not insert was successful
     * @throws SQLException Throws whenever an sql error is encountered
     */
    private static boolean vehicleInAccident() throws SQLException{
        //User input section
        JLabel accIDLabel = new JLabel("Enter Accident ID:");
        JTextField accID_Jtf = new JTextField();
        JLabel vinLabel = new JLabel("Enter Vehicle VIN: ");
        JTextField vinJtf = new JTextField();
        JOptionPane.showConfirmDialog(null,
                new Object[]{accIDLabel, accID_Jtf, vinLabel, vinJtf}, "Enter Vehicle & Accident Details:",
                JOptionPane.OK_CANCEL_OPTION);

        //convert user input to SQL submission form
        String accID = accID_Jtf.getText().toUpperCase();
        String vehVin = vinJtf.getText().toUpperCase();
//        System.out.println("AccidentID val: " + accID);//Debugging
//        System.out.println("Vehicle VIN: " + vehVin);//Debugging

        assert (vehVin != null);
        assert (accID != null);
        getDBConnection();
        String veh_in_acc_insert_query = "INSERT INTO VEHICLES_IN_ACCIDENT VALUES(?,?)";
        String veh_in_acc_query = "SELECT * FROM VEHICLES_IN_ACCIDENT";
        boolean insertSuccess = false;
        int updatedRows = 0;

        //Attempt SQL query, catching any SQL exception that may occur
        try(PreparedStatement sqlVehAccInsQuery = conn.prepareStatement(veh_in_acc_insert_query);
            PreparedStatement sqlVehAccQuery = conn.prepareStatement(veh_in_acc_query)) {
            conn.setAutoCommit(false);
            sqlVehAccInsQuery.setString(1, accID);
            sqlVehAccInsQuery.setString(2, vehVin);

//            System.out.println("attempting to run insert");//debugging
            updatedRows = sqlVehAccInsQuery.executeUpdate();
//            System.out.println("insert attempt finished");//debugging
            if (updatedRows > 0) {
//                System.out.println("# of update rows from operation: " + updatedRows);//debugging
                conn.commit();
//                System.out.println("changes committed, running query on updated table");//debugging
                ResultSet rs = sqlVehAccQuery.executeQuery();
                StringBuilder sb = new StringBuilder();
                sb.append("Insertion of row ('" + accID + "', '" + vehVin + "') successful.\n");
                sb.append("The 'vehicle_in_accident' table after insertion now looks like: \n");
                sb.append("Accident ID \t\tVehicle VIN \n");

                //Loop through result set and append to output string
                while (rs.next()) {
                    accID = rs.getString(1);
                    vehVin = rs.getString(2);
                    sb.append(accID + "\t\t\t\t" + vehVin + "\n");
                }
                System.out.print(sb.toString());
                insertSuccess = true;
            }
        }
        catch (SQLException e){
            // Roll back the transaction
            conn.rollback();
            e.printStackTrace();
        }finally {
            conn.setAutoCommit(true);
        }

        return insertSuccess;
    }

    /**
     * Method that prompts the user for a date (YYYY-MM-DD), then runs a query
     * that removes policies prior to that date in the 'policies' table.
     * @return T/F whether or not removale was successful
     * @throws SQLException Throws whenever an sql error is encountered
     */
    private static boolean deleteAllPoliciesBeforeDate() throws SQLException{
        //User input section
        JLabel yearLabel = new JLabel("Enter desired policy cutoff year(YYYY):");
        JTextField year_Jtf = new JTextField();
        JLabel monthLabel = new JLabel("Enter desired policy cutoff month(MM): ");
        JTextField monthJtf = new JTextField();
        JLabel dayLabel = new JLabel("Enter desired policy cutoff day(DD): ");
        JTextField dayJtf = new JTextField();
        JOptionPane.showConfirmDialog(null,
                new Object[]{yearLabel, year_Jtf, monthLabel, monthJtf, dayLabel, dayJtf}, "Enter policy cut-off details:",
                JOptionPane.OK_CANCEL_OPTION);

        //convert user input to SQL submission form
        int yearIn = Integer.parseInt(year_Jtf.getText());
        int monthIn = Integer.parseInt(monthJtf.getText());
        int dayIn = Integer.parseInt(dayJtf.getText());

        assert (yearIn != 0);
        assert (monthIn != 0);
        assert (dayIn != 0);

        getDBConnection();

        String policy_delete_query = "DELETE FROM POLICY WHERE PDATE < ?";
        String after_delete_query = "SELECT * FROM POLICY";
        boolean insertSuccess = false;
        int updatedRows = 0;

        //NOTE: Java Calendar class uses zero index month values, so the following month is decremented before submitting to DB
        Calendar cutOffDate = new Calendar.Builder().setDate(yearIn, (monthIn -1), dayIn).build();

        try(PreparedStatement sql_policy_delete = conn.prepareStatement(policy_delete_query);
            PreparedStatement sql_after_delete = conn.prepareStatement(after_delete_query)) {
            conn.setAutoCommit(false);

            sql_policy_delete.setDate(1, new Date(cutOffDate.getTimeInMillis()));

            updatedRows = sql_policy_delete.executeUpdate();

            if (updatedRows > 0){
                String pNum;
                Date pDate;
                int cSsn;
//                System.out.println("# of deleted rows from operation: " + updatedRows);//debugging
                conn.commit();
                ResultSet rs_after_commit = sql_after_delete.executeQuery();
                StringBuilder sb = new StringBuilder();
                sb.append("Deletion of rows prior to " + cutOffDate.toString() +" successful");
                sb.append("The rows contained in 'policy' table after the deletion are:");
                sb.append("Policy Num \t\tPolicy Date \t\tClient SSN\n");

                //Loop through result set and append to output string
                while (rs_after_commit.next()){
                    pNum = rs_after_commit.getString(1);
                    pDate = rs_after_commit.getDate(2);

                    cSsn = rs_after_commit.getInt(3);
                    sb.append(pNum + "\t\t" + pDate.toString() + "\t\t" + Integer.toString(cSsn) + "\n");

                }
                System.out.print(sb.toString());
                insertSuccess = true;

            }

        }

        catch (SQLException e){
            // Roll back the transaction
            conn.rollback();
            e.printStackTrace();
        }finally {
            conn.setAutoCommit(true);
        }
        return insertSuccess;
    }



    /**
     * Main method which calls all the methods to complete lab8
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

        JLabel queriesLabel = new JLabel("Select which query to run: ");
        JComboBox queries = new JComboBox();
        queries.addItem(new queryObj("Insert Vehicle"));
        queries.addItem(new queryObj("Add vehicle to accident"));
        queries.addItem(new queryObj("Retrieve worst drivers"));
        queries.addItem(new queryObj("Delete policies"));

        JOptionPane.showConfirmDialog(null,
                new Object[]{queriesLabel, queries}, "Choose a query:",
                JOptionPane.OK_CANCEL_OPTION);

        String selection = queries.getSelectedItem().toString();

        switch (selection){
            case "Insert Vehicle":
                insertVehicle();
                break;
            case "Add vehicle to accident":
                vehicleInAccident();
                break;
            case "Retrieve worst drivers":
                //TODO: insert implementation of method
                break;
            case "Delete policies":
                deleteAllPoliciesBeforeDate();
                break;
        }

    }

    static class queryObj{
        String query;

        queryObj(String q){
            this.query = q;
        }

        @Override
        public String toString(){
            return this.query;
        }
    }


}
