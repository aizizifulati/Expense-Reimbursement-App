package Dao;

import models.Reimbursement;

import java.sql.Timestamp;
import java.util.List;

public interface ReimbursementDao {

    
    List<Reimbursement> getAllReimbursements();
    Reimbursement getOneReimbursement(Integer reimbursement_id);
    void createReimbursement(Reimbursement reimbursement);
    void updateAReimbursement(Integer reimbursement_id,Integer reimbursement_status_id);
    void deleteAReimbursement(Integer reimbursement_id);
    List<Reimbursement> getAllReimbursementsByuser_id( Integer user_id);
    List<Reimbursement> getAllReimbursementsByUsername(String username);
    List<Reimbursement> getAllUnresolvedReimbursements();
    void approveAReimbursement(Integer reimbursement_id, Timestamp resolved,Integer resolver);
    void denyAReimbursement(Integer reimbursement_id,Timestamp resolved,Integer resolver);
    List<Reimbursement> getAllApprovedReimbursements();
    List<Reimbursement> getAllDeniedReimbursements();


}
