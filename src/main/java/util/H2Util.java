package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2Util {
    public static String url = "jdbc:h2:./h2/db";
    public static String username = "sa";
    public static String password = "sa";

    public static void createTable(){
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "CREATE TABLE ERS_REIMBURSEMENT_STATUS(\n" +
                    "REIMB_STATUS_ID SERIAL PRIMARY KEY,\n" +
                    "REIMB_STATUS VARCHAR(10) NOT NULL\n" +
                    ");" +
                    "CREATE TABLE ERS_REIMBURSEMENT_TYPE(\n" +
                    "REIMB_TYPE_ID SERIAL PRIMARY KEY,\n" +
                    "REIMB_TYPE VARCHAR(10) NOT NULL\n" +
                    ");" +
                    "CREATE TABLE ERS_USER_ROLES(\n" +
                    "ERS_USER_ROLE_ID SERIAL PRIMARY KEY,\n" +
                    "USER_ROLE VARCHAR(10) NOT NULL\n" +
                    ");" +
                    "CREATE TABLE ERS_USERS(\n" +
                    "ERS_USERS_ID SERIAL PRIMARY KEY,\n" +
                    "ERS_USERNAME VARCHAR(50) UNIQUE NOT NULL,\n" +
                    "ERS_PASSWORD VARCHAR(50) NOT NULL,\n" +
                    "USER_FIRST_NAME VARCHAR(100) NOT NULL,\n" +
                    "USER_LAST_NAME VARCHAR(100) NOT NULL,\n" +
                    "USER_EMAIL VARCHAR(100) NOT NULL,\n" +
                    "USER_ROLE_ID INT NOT NULL REFERENCES ERS_USER_ROLES(ERS_USER_ROLE_ID) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ");" +
                    "CREATE TABLE ERS_REIMBURSEMENT (\n" +
                    "REIMB_ID SERIAL PRIMARY KEY,\n" +
                    "REIMB_AMOUNT INT NOT NULL,\n" +
                    "REIMB_SUBMITTED TIMESTAMP NOT NULL,\n" +
                    "REIMB_RESOLVED TIMESTAMP,\n" +
                    "REIMB_DESCRIPTION VARCHAR(250),\n" +
                    "REIMB_RECEIPT BYTEA,\n" +
                    "REIMB_AUTHOR INT NOT NULL REFERENCES ERS_USERS(ERS_USERS_ID) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "REIMB_RESOLVER INT  REFERENCES ERS_USERS(ERS_USERS_ID) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "REIMB_STATUS_ID INT NOT NULL REFERENCES ERS_REIMBURSEMENT_STATUS(REIMB_STATUS_ID) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "REIMB_TYPE_ID INT NOT NULL REFERENCES ERS_REIMBURSEMENT_TYPE(REIMB_TYPE_ID) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ");";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void dropTable(){
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "DROP TABLE ERS_REIMBURSEMENT_STATUS,ERS_REIMBURSEMENT_TYPE,ERS_USER_ROLES,ERS_USERS,ERS_REIMBURSEMENT;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}