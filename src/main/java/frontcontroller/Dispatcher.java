package frontcontroller;
import controllers.ReimbursementController;
import controllers.UserController;
import dto.UserDTO;
import io.javalin.Javalin;
import models.JsonResponse;
import models.Reimbursement;
import models.User;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.*;


public class Dispatcher {
    UserController userController =new UserController();
    ReimbursementController reimbursementController =new ReimbursementController();

    public Dispatcher(Javalin app){
        //what function we need???
         // for manager
        //1.log in then jump to the  manager dash board(getOneUser>validate>set session)
        //2.in manager dashboard ,show all  the reimbursement resuqests(getAllReimbursements)
        //3.operate on request (updateReimbursementStatus 1.deny 2.approve)
        //4.log out ,delet session

        //for employee
        //1.log in then jump to the employee dash board(getOne User>validate>set session)
        //2.in employee dashboard , show all the reimbursement requests(getAllReimbursements)
        //3.add a new Reimbursement request >(createReimbursement)
        //log out , delete session


        // front end design
        // pages
        //1.login page
        //2.manager dashboard >listPage
        //3.manager dashboard >detail page
        //4.employee dashboard>listpage
        //5.employee dashboard>detail page


            app.get("api/users",context -> {
                userController.getAllUsers(context);

                context.status(200);
            });
            app.post("api/login",context -> {
                context.contentType("application/json");
                String name =context.formParam("username");
                String password =context.formParam("password");
                User user=userController.getOneUserByUsernameAndPassword(name,password);
                System.out.println(name);
                System.out.println(password);
                if(user!=null){System.out.println(user.toString());
                    context.result(user.toString());
                    context.sessionAttribute("user-session",user);
                    context.json(new JsonResponse(true, "login successful", user));
                }
                else{context.result("wrong pasword or username!!!!!!!!!!");}



//                System.out.println(user.toString());
//                if(user.getPassword().equals(userDTO.getPassword())){
////                    System.out.println("LANA !!!!!!!!!!!!!!!!!!!!!!");
//                    context.sessionAttribute("user-session",userDTO);
//                    context.json(new JsonResponse(true,"login successful",user));



//
//                if(userController.loginValidation(userDTO)){
//                    User user = userController.getOneUserByUsername(userDTO.getUsername());
//                    context.json(new JsonResponse(true,"login successful",user));
//
//                };

            });
            app.get("api/reimbursements",context -> {
                context.contentType("application/json");
//
//                String name =context.formParam("name");
////                System.out.println(name);
//                User user=userController.getOneUserByUsername(name);
////                System.out.println(user);
//                Integer user_id =user.getUser_id();
//                System.out.println(user_id);
////                List<Reimbursement> reimbursements= reimbursementController.getAllReimbursementsByuser_id(user_id);
////                System.out.println(reimbursements);

                List<Reimbursement> reimbursements= reimbursementController.getAllReimbursements(context);
                context.json(new JsonResponse(true, "login successful", reimbursements));
                System.out.println(reimbursements);

            });

        app.get("/api/check-session", context -> {
            User user = context.sessionAttribute("user-session");

            if(user == null){
                context.json(new JsonResponse(false, "no session found", null));
            }else{
                context.json(new JsonResponse(true, "session found", user));
            }

        });

        app.delete("/api/logout", context -> {
            context.sessionAttribute("user-session",null);
            context.json(new JsonResponse(true, "Session has been destroyed and you have successfully logged out", null));
        });

        app.get("/api/reimbursementsbyusername",context -> {
//            context.contentType("application/json");
//            String name =context.formParam("username");
//                System.out.println(name);
//            User user=userController.getOneUserByUsername(name);
//                System.out.println(user);
            User user = context.sessionAttribute("user-session");
            System.out.println(user);
            Integer user_id =user.getUser_id();
//

            System.out.println(user_id);
            List<Reimbursement> reimbursements=reimbursementController.getAllReimbursementsByuser_id(user_id);
            System.out.println(reimbursements);
            context.json(new JsonResponse(true, "login successful", reimbursements));


        });
        app.post("/api/createreimbursement",context -> {
            context.contentType("application/json");
            String description =context.formParam("description");
            Integer amount=Integer.parseInt(context.formParam("amount"));
            Integer type_id=Integer.parseInt(context.formParam("type_id"));
            User user = context.sessionAttribute("user-session");
//            byte[] fileByteArray= context.formParam("file").getBytes();
//            String password =context.formParam("password");

//            System.out.println(fileByteArray);
            //Reimbursement(Integer amount, Timestamp submitted, String description, Integer author, Integer status_id, Integer type_id)
            Reimbursement reimbursement=new Reimbursement(0,amount,new Timestamp(System.currentTimeMillis()),description,user.getUser_id(),1,type_id);
            System.out.println("Description : "+description + "Amount : " +amount +"Type_od : "+type_id+"user info : "+user+" new reimburse : "+reimbursement);
            reimbursementController.createReimbursement(reimbursement);


        });
        
        app.get("/api/unresolvedrequirements",context -> {
            context.contentType("application/json");


            List<Reimbursement> reimbursements= reimbursementController.getAllUnresolvedReimbursements();
            context.json(new JsonResponse(true, "login successful", reimbursements));
            System.out.println(reimbursements);
            
        });

        app.post("/api/approveareimbursement",context -> {
            context.contentType("application/json");
            Integer id=Integer.parseInt(context.formParam("id"));
            User user = context.sessionAttribute("user-session");
            Timestamp resolved=new Timestamp(System.currentTimeMillis());
            Integer resolver=user.getUser_id();
            reimbursementController.approveAReimbursement(id,resolved, resolver);
        });

        app.post("/api/denyareimbursement",context -> {
            context.contentType("application/json");
            Integer id=Integer.parseInt(context.formParam("id"));
//            System.out.println(id);
            User user = context.sessionAttribute("user-session");
//            System.out.println(user);
            Integer resolver=user.getUser_id();
////            System.out.println(id);
//            System.out.println(resolver);
////            System.out.println(new Timestamp(System.currentTimeMillis()));
            Timestamp resolved=new Timestamp(System.currentTimeMillis());

//
       reimbursementController.denyAReimbursement(id,resolved, resolver);
            System.out.println("id :" + id+"timestamp : " +resolved+"resolver  :" +resolver);
        });










    }




}
