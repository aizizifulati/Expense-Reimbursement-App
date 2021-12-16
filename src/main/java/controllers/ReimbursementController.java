package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UserDTO;
import models.Reimbursement;
import models.User;
import services.ReimbursementService;
import io.javalin.http.Context;
import services.UserService;

import java.sql.Timestamp;
import java.util.List;

public class ReimbursementController {


    static ReimbursementService reimbursementService =new ReimbursementService();
    static UserService userService=new UserService();

    public List<Reimbursement> getAllReimbursements (Context context) throws JsonProcessingException{
        context.contentType("application/json");

        List<Reimbursement> reimbursementList =reimbursementService.getAllReimbursements();

        String jsongString =new ObjectMapper().writeValueAsString(reimbursementList);

        context.result(jsongString);
        return reimbursementList;

    }

    public void getOneReimbursement(Context contex) throws JsonProcessingException{
        contex.contentType("application/json");

        Integer userID =Integer.parseInt(contex.formParam("userID"));

        Reimbursement reimbursement=reimbursementService.getOneReimbursement(userID);

        contex.result(new ObjectMapper().writeValueAsString(reimbursement));
    }

    public void createReimbursement(Reimbursement reimbursement){
//         Reimbursement reimbursement=context.bodyAsClass(Reimbursement.class);
         reimbursementService.createReimbursement(reimbursement);
    }

    public void updateReimbursement(Context context){
        Integer reimbursement_id =Integer.parseInt(context.formParam("reimbursement_id"));
        Integer reimbursement_status_id =Integer.parseInt(context.formParam("reimbursement_status_id"));

        reimbursementService.updateAReimbursement(reimbursement_id,reimbursement_status_id);

    }

    public List<Reimbursement> getAllReimbursementsByuser_id(Integer user_id) throws JsonProcessingException {
      return reimbursementService.getAllReimbursementsByuser_id(user_id);

    }
    public List<Reimbursement> getAllUnresolvedReimbursements(){
        return reimbursementService.getAllUnresolvedReimbursements();
    }

    public void approveAReimbursement(Integer reimbursement_id, Timestamp resolved, Integer resolver){
        reimbursementService.approveAReimbursement(reimbursement_id,resolved,resolver);
    }
    public void denyAReimbursement(Integer reimbursement_id, Timestamp resolved, Integer resolver){
        reimbursementService.denyAReimbursement(reimbursement_id,resolved,resolver);
    }

}
