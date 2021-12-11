package services;

import Dao.ReimbursementDao;
import Dao.ReimbursementDaoImpl;
import models.Reimbursement;

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


}
