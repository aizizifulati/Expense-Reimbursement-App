package Dao;
import models.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserDaoImpl implements UserDao {
    String url;
    String username;
    String password;

    Logger logger =Logger.getLogger(UserDaoImpl.class);

    public UserDaoImpl(){

        this.url = "jdbc:postgresql://" + System.getenv("AWS_RDS_ENDPOINT") + "/ERS";
        this.username =  System.getenv("RDS_USERNAME");
        this.password = System.getenv("RDS_PASSWORD");


    }

    public UserDaoImpl(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }



    @Override
    public List<User> getAllUsers() {
        List<User> users =new ArrayList<>();

        try(Connection conn =DriverManager.getConnection(url,username,password)){

            String sql="SELECT * FROM ers_users ;";
            PreparedStatement ps= conn.prepareStatement(sql);


            ResultSet rs=ps.executeQuery();

            System.out.println(rs);

            while(rs.next()){
                users.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7)));
            }
            logger.info("getAllUsers() " +users.toString());
        }catch (SQLException e){
            logger.error(e);
        }

        return users;
    }

    @Override
    public User getOneUser(Integer user_id) {
        User user=null;

        try(Connection conn=DriverManager.getConnection(url,username,password)){

            String sql="SELECT * FROM ers_users WHERE ers_users_id =?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,user_id);

            ResultSet rs= ps.executeQuery();

            while(rs.next()){
                user=new User(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
            }
            logger.info("getOneUser() " +user.toString());
        }catch (SQLException e){
            logger.error(e);
        }
        return user;


    }

    @Override
    public void createUser(User user) {

        try(Connection conn =DriverManager.getConnection(url,username,password)){

            String sql="INSERT INTO ERS_USERS(ERS_USERS_ID,ERS_USERNAME,ERS_PASSWORD,USER_FIRST_NAME,USER_LAST_NAME,USER_EMAIL,USER_ROLE_ID)\n" +
                    "VALUES (DEFAULT,?,?,?,?,?,?)";
            PreparedStatement ps=conn.prepareStatement(sql);

//            ps.setInt(1,user.getUser_id());
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getFirst_name());
            ps.setString(4,user.getLast_name());
            ps.setString(5,user.getEmail());
            ps.setInt(6,user.getRole_id());

            ps.executeUpdate();
            logger.info("createUser() " + user.toString());
        }catch (SQLException e){
            logger.error(e);
        }

    }

    @Override
    public void updateAUser(Integer user_id, String user_name, String pass_word) {

        try(Connection conn=DriverManager.getConnection(url,username,password)){

            String sql="UPDATE ers_users SET ers_username = ?,ers_password = ? \n" +
                    "WHERE ers_users_id = ?;";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,user_name);
            ps.setString(2,pass_word);
            ps.setInt(3,user_id);

            ps.executeUpdate();
            logger.info("updateAUser() "+getOneUser(user_id).toString());
        }catch (SQLException e){
            logger.error(e);
        }

    }

    @Override
    public void deleteAUser(Integer user_id) {

        try(Connection conn =DriverManager.getConnection(url,username,password)){

            String sql="DELETE FROM ers_users where ers_users_id =?;";

            PreparedStatement ps=conn.prepareStatement(sql);

            ps.setInt(1,user_id);

            ps.executeUpdate();

            logger.info("deleteAUser "+user_id);
        }catch (SQLException e){logger.error(e);}



    }

    @Override
    public User getOneUserByUsername(String username) {
        User user=null;

        try(Connection conn=DriverManager.getConnection(url,username,password)){

            String sql="SELECT * FROM ers_users WHERE ers_users_id =?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,user_id);

            ResultSet rs= ps.executeQuery();

            while(rs.next()){
                user=new User(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
            }
            logger.info("getOneUser() " +user.toString());
        }catch (SQLException e){
            logger.error(e);
        }
    }
}
