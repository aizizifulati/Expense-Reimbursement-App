package services;

import Dao.ReimbursementDao;
import Dao.ReimbursementDaoImpl;
import models.Reimbursement;

import java.sql.Timestamp;
import java.util.List;

public class ReimbursementService {

    ReimbursementDao reimbursementDao;

    public ReimbursementService(ReimbursementDao reimbursementDao){
        this.reimbursementDao=reimbursementDao;
    }

    public ReimbursementService(){
        this.reimbursementDao=new ReimbursementDaoImpl();
    }

    public List<Reimbursement> getAllReimbursements(){
        return reimbursementDao.getAllReimbursements();
    }

    public Reimbursement getOneReimbursement(Integer reimbursement_id){
        return reimbursementDao.getOneReimbursement(reimbursement_id);
    }

    public void createReimbursement(Reimbursement reimbursement){
        reimbursementDao.createReimbursement(reimbursement);
    }

    public void updateAReimbursement(Integer reimbursement_id, Integer reimbursement_status_id){
        reimbursementDao.updateAReimbursement(reimbursement_id,reimbursement_status_id);
    }

    public List<Reimbursement> getAllReimbursementsByuser_id(Integer user_id){
       return reimbursementDao.getAllReimbursementsByuser_id(user_id);
    }
    public List<Reimbursement> getAllUnresolvedReimbursements(){
        return reimbursementDao.getAllUnresolvedReimbursements();
    }
    public void approveAReimbursement(Integer reimbursement_id, Timestamp resolved, Integer resolver){
        reimbursementDao.approveAReimbursement(reimbursement_id,resolved,resolver);
    }

    public void denyAReimbursement(Integer reimbursement_id, Timestamp resolved, Integer resolver){
        reimbursementDao.denyAReimbursement(reimbursement_id,resolved,resolver);
    }


}
