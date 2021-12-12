package frontcontroller;
import controllers.ReimbursementController;
import controllers.UserController;
import dto.UserDTO;
import io.javalin.Javalin;

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
                UserDTO userDTO=context.bodyAsClass(UserDTO.class);
                Integer
            });






        }




}
