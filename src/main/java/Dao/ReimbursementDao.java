package Dao;

import models.Reimbursement;

import java.util.List;

public interface ReimbursementDao {
    List<Reimbursement> getAllReimbursements();
    Reimbursement getOneReimbursement(Integer reimbursement_id);
    void createReimbursement(Reimbursement reimbursement);
    void updateAReimbursement(Integer reimbursement_id,Integer reimbursement_status_id);
    void deleteAReimbursement(Integer reimbursement_id);
}
