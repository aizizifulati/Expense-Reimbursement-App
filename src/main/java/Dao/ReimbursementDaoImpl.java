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

            ResultSet rs= ps.getResultSet();

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
            logger.info("getOneReimbursement" +reimbursement);


        }catch (SQLException e){
            logger.error(e);
        }return reimbursement;
    }

    @Override
    public void createReimbursement(Reimbursement reimbursement) {
        try(Connection conn =DriverManager.getConnection(url,username,password)){
            String sql="INSERT INTO ers_reimbursement (reimb_id,reimb_amount,reimb_submitted,reimb_resolved,reimb_description,reimb_receipt,reimb_author,reimb_resolver,reimb_status_id,reimb_type_id)\n" +
                    "VALUES(DEFAULT,?,current_timestamp,DEFAULT,?,DEFAULT,?,DEFAULT,?,?);";

            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,reimbursement.getAmount());
            ps.setString(2,reimbursement.getDescription());
            ps.setInt(3,reimbursement.getAuthor());
            ps.setInt(4,reimbursement.getStatus_id());
            ps.setInt(5,reimbursement.getType_id());

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
}
