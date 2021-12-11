package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Reimbursement;
import services.ReimbursementService;
import io.javalin.http.Context;

import java.util.List;

public class ReimbursementController {


    static ReimbursementService reimbursementService =new ReimbursementService();

    public void getAllReimbursements (Context context) throws JsonProcessingException{
        context.contentType("application/json");

        List<Reimbursement> reimbursementList =reimbursementService.getAllReimbursements();

        String jsongString =new ObjectMapper().writeValueAsString(reimbursementList);

        context.result(jsongString);

    }

    public void getOneReimbursement(Context contex) throws JsonProcessingException{
        contex.contentType("application/json");

        Integer userID =Integer.parseInt(contex.formParam("userID"));

        Reimbursement reimbursement=reimbursementService.getOneReimbursement(userID);

        contex.result(new ObjectMapper().writeValueAsString(reimbursement));
    }

    public void createReimbursement(Context context){
         Reimbursement reimbursement=context.bodyAsClass(Reimbursement.class);
         reimbursementService.createReimbursement(reimbursement);
    }

    public void updateReimbursement(Context context){
        Integer reimbursement_id =Integer.parseInt(context.formParam("reimbursement_id"));
        Integer reimbursement_status_id =Integer.parseInt(context.formParam("reimbursement_status_id"));

        reimbursementService.updateAReimbursement(reimbursement_id,reimbursement_status_id);

    }
}
