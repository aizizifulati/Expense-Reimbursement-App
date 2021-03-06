package Dao;

import models.Reimbursement;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDaoImpl implements ReimbursementDao{
    String url;
    String username;
    String password;

    Logger logger =Logger.getLogger(UserDaoImpl.class);

    public ReimbursementDaoImpl(){
        this.url = "jdbc:postgresql://" + System.getenv("AWS_RDS_ENDPOINT") + "/ERS";
        this.username =  System.getenv("RDS_USERNAME");
        this.password = System.getenv("RDS_PASSWORD");
    }

    public ReimbursementDaoImpl(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }


    @Override
    public List<Reimbursement> getAllReimbursements() {

        List<Reimbursement> reimbursements=new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url,username,password)){
            String sql="SELECT * FROM ers_reimbursement;";
            PreparedStatement ps=conn.prepareStatement(sql);

            ResultSet rs= ps.executeQuery();

            System.out.println(rs);

            while(rs.next()){
                reimbursements.add(new Reimbursement(rs.getInt(1),rs.getInt(2),rs.getTimestamp(3),rs.getTimestamp(4),rs.getString(5),rs.getBytes(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10)));
            }
            logger.info("getAllReimbursements " +reimbursements);
        }catch (SQLException e){
            logger.error(e);
        }
        return reimbursements;
    }
    // TODO: 12/11/2021 fix H2 create table
    // TODO: 12/11/2021 create the tests


    @Override
    public Reimbursement getOneReimbursement(Integer reimbursement_id) {
        Reimbursement reimbursement=null;

        try(Connection conn=DriverManager.getConnection(url,username,password)){

            String sql="SELECT * FROM ers_reimbursement WHERE reimb_id =?;";
            PreparedStatement ps=conn.prepareStatement(sql);

            ps.setInt(1,reimbursement_id);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                reimbursement=new Reimbursement(rs.getInt(1),rs.getInt(2),rs.getTimestamp(3),rs.getTimestamp(4),rs.getString(5),rs.getBytes(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10));
            }
            logger.info("get One Reimbursement" +reimbursement);


        }catch (SQLException e){
            logger.error(e);
        }return reimbursement;
    }

    @Override
    public void createReimbursement(Reimbursement reimbursement) {
        try(Connection conn =DriverManager.getConnection(url,username,password)){
            String sql="INSERT INTO ers_reimbursement (reimb_id,reimb_amount,reimb_submitted,reimb_resolved,reimb_description,reimb_receipt,reimb_author,reimb_resolver,reimb_status_id,reimb_type_id)\n" +
                    "VALUES(DEFAULT,?,?,?,?,DEFAULT,?,?,?,?);";

            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,reimbursement.getAmount());
            ps.setTimestamp(2,reimbursement.getSubmitted());
            ps.setTimestamp(3,reimbursement.getResolved());
            ps.setString(4,reimbursement.getDescription());
            ps.setInt(5,reimbursement.getAuthor());
            ps.setInt(6,reimbursement.getResolver());
            ps.setInt(7,reimbursement.getStatus_id());
            ps.setInt(8,reimbursement.getType_id());

            ps.executeUpdate();
            logger.info("createReimbursement()" +reimbursement);



        }catch (SQLException e){logger.error(e);}

    }

    @Override
    public void updateAReimbursement(Integer reimbursement_id, Integer reimbursement_status_id) {

        try (Connection conn=DriverManager.getConnection(url,username,password)){
            String sql ="UPDATE ers_reimbursement SET reimb_status_id =2 WHERE reimb_id =?;";

            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,reimbursement_id);

            ps.executeUpdate();

            logger.info("updateReimbursement() the new reimbursement_status_id is " +reimbursement_status_id);
        }catch (SQLException e){
            logger.error(e);
        }

    }

    @Override
    public void deleteAReimbursement(Integer reimbursement_id) {
        try(Connection conn = DriverManager.getConnection(url,username,password)){

            String sql="delete FROM ers_reimbursement WHERE reimb_id =?;";



            PreparedStatement ps=conn.prepareStatement(sql);

            ps.setInt(1,reimbursement_id);

            ps.executeUpdate();

            logger.info("deleteAReimbursement id " + reimbursement_id);


        }catch (SQLException e){logger.error(e);}

    }

    @Override
    public List<Reimbursement> getAllReimbursementsByuser_id(Integer user_id) {
        List<Reimbursement> reimbursements=new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url,username,password)){
            String sql="SELECT * FROM ers_reimbursement WHERE reimb_author =?;";
            PreparedStatement ps=conn.prepareStatement(sql);

            ps.setInt(1,user_id);

            ResultSet rs= ps.executeQuery();

            System.out.println(rs);

            while(rs.next()){
                reimbursements.add(new Reimbursement(rs.getInt(1),rs.getInt(2),rs.getTimestamp(3),rs.getTimestamp(4),rs.getString(5),rs.getBytes(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10)));
            }
            logger.info("get all reimbursementsby user id " +reimbursements);
        }catch (SQLException e){
            logger.error(e);
        }
        return reimbursements;
    }

    @Override
    public List<Reimbursement> getAllReimbursementsByUsername(String username) {
        return null;
    }

    @Override
    public List<Reimbursement> getAllUnresolvedReimbursements() {
        List<Reimbursement> reimbursements=new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url,username,password)){
            String sql="SELECT * FROM ers_reimbursement WHERE reimb_status_id =1;";
            PreparedStatement ps=conn.prepareStatement(sql);

            ResultSet rs= ps.executeQuery();

            System.out.println(rs);

            while(rs.next()){
                reimbursements.add(new Reimbursement(rs.getInt(1),rs.getInt(2),rs.getTimestamp(3),rs.getTimestamp(4),rs.getString(5),rs.getBytes(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10)));
            }
            logger.info("get all pending Reimbursements " +reimbursements);
        }catch (SQLException e){
            logger.error(e);
        }
        return reimbursements;

    }



    @Override
    public void approveAReimbursement(Integer reimbursement_id,Timestamp resolved,Integer resolver) {
        try (Connection conn=DriverManager.getConnection(url,username,password)){
            String sql ="UPDATE ers_reimbursement SET reimb_status_id =2,reimb_resolver=?,reimb_resolved=? WHERE reimb_id =?;";

            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,resolver);
            ps.setTimestamp(2,resolved);
            ps.setInt(3,reimbursement_id);
            ps.executeUpdate();

            logger.info("approve a reimbursement of id : " + reimbursement_id);
        }catch (SQLException e){
            logger.error(e);
        }
    }

    @Override
    public void denyAReimbursement(Integer reimbursement_id, Timestamp resolved,Integer resolver) {
        try (Connection conn=DriverManager.getConnection(url,username,password)){
            String sql ="UPDATE ers_reimbursement SET reimb_status_id =3,reimb_resolver=?,reimb_resolved=? WHERE reimb_id =?;";

            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,resolver);
            ps.setTimestamp(2,resolved);
            ps.setInt(3,reimbursement_id);

            ps.executeUpdate();

            logger.info("deny a reimbursement of id : " + reimbursement_id);
        }catch (SQLException e){
            logger.error(e);
        }

    }

    @Override
    public List<Reimbursement> getAllApprovedReimbursements() {
        List<Reimbursement> reimbursements=new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url,username,password)){
            String sql="SELECT * FROM ers_reimbursement WHERE reimb_status_id =2;";
            PreparedStatement ps=conn.prepareStatement(sql);

            ResultSet rs= ps.executeQuery();

            System.out.println(rs);

            while(rs.next()){
                reimbursements.add(new Reimbursement(rs.getInt(1),rs.getInt(2),rs.getTimestamp(3),rs.getTimestamp(4),rs.getString(5),rs.getBytes(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10)));
            }
            logger.info("get all approved reimbursements " +reimbursements);
        }catch (SQLException e){
            logger.error(e);
        }
        return reimbursements;
    }

    @Override
    public List<Reimbursement> getAllDeniedReimbursements() {
        List<Reimbursement> reimbursements=new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url,username,password)){
            String sql="SELECT * FROM ers_reimbursement WHERE reimb_status_id =3;";
            PreparedStatement ps=conn.prepareStatement(sql);

            ResultSet rs= ps.executeQuery();

            System.out.println(rs);

            while(rs.next()){
                reimbursements.add(new Reimbursement(rs.getInt(1),rs.getInt(2),rs.getTimestamp(3),rs.getTimestamp(4),rs.getString(5),rs.getBytes(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10)));
            }
            logger.info("get all denied reimbursements " +reimbursements);
        }catch (SQLException e){
            logger.error(e);
        }
        return reimbursements;
    }
}

