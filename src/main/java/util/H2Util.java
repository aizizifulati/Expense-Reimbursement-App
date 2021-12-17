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

    public static void add_data02(){

        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO ERS_REIMBURSEMENT_TYPE(REIMB_TYPE_ID,REIMB_TYPE)  VALUES (DEFAULT,'LODGING');\n" +
                    "INSERT INTO ERS_REIMBURSEMENT_TYPE(REIMB_TYPE)  VALUES ('TRAVEL');\n" +
                    "INSERT INTO ERS_REIMBURSEMENT_TYPE(REIMB_TYPE)  VALUES ('FOOD');\n" +
                    "INSERT INTO ERS_REIMBURSEMENT_TYPE(REIMB_TYPE)  VALUES ('OTHER');";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void add_data03(){

        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO ERS_USER_ROLES(USER_ROLE) values('Employee'),('Manager');";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void add_data05(){

        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO ERS_REIMBURSEMENT_STATUS(REIMB_STATUS) VALUES('Pending');\n" +
                    "INSERT INTO ERS_REIMBURSEMENT_STATUS(REIMB_STATUS) VALUES('Approved');\n" +
                    "INSERT INTO ERS_REIMBURSEMENT_STATUS(REIMB_STATUS) VALUES('Denied');";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.executeUpdate();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void add_data04(){

        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO ERS_USERS(ERS_USERNAME,ERS_PASSWORD,USER_FIRST_NAME,USER_LAST_NAME,USER_EMAIL,USER_ROLE_ID)\n" +
                    "VALUES ('aizizi01',123456,'Aizizi','Fulati','aizizi@emial.com',1);\n" +
                    "INSERT INTO ERS_USERS(ERS_USERNAME,ERS_PASSWORD,USER_FIRST_NAME,USER_LAST_NAME,USER_EMAIL,USER_ROLE_ID)\n" +
                    "VALUES ('malory01',123456,'Malory','Archer','malory@emial.com',2),\n" +
                    "('lana01',123456,'Lana','Kane','lana@emial.com',1),\n" +
                    "('cyril01',123456,'Cyril','Figgis','cyril@emial.com',1),\n" +
                    "('Cheryl01',123456,'Cheryl','Tunt','cheryl@emial.com',1),\n" +
                    "('Pam01',123456,'Pam','Poovey','cyril@emial.com',1),\n" +
                    "('Ray01',123456,'Ray','Giillette','cyril@emial.com',1);\n";

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