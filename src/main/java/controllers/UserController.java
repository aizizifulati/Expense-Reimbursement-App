package controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UserDTO;
import models.User;
import services.UserService;
import io.javalin.http.Context;

import java.util.List;

public class UserController {
    static UserService userService=new UserService();
    public void getAllUsers(Context context) throws JsonProcessingException{
        context.contentType("application/json");

        List<User> userList =userService.getAllUsers();

        String jsongString = new ObjectMapper().writeValueAsString(userList);

        context.result(jsongString);
    }

    public void getOneUser(Context context) throws JsonProcessingException{
        context.contentType("application/json");

        Integer userID =Integer.parseInt(context.formParam("userID"));

        User user =userService.getOneUser(userID);
        context.result(new ObjectMapper().writeValueAsString(user));
    }
    public void createUser(Context context){

        // receive all the keys from form for User object ,but for now , put context
        User user =context.bodyAsClass(User.class);
        userService.createUser(user);
    }
    public void updateUser(Context context){

        //Integer user_id, String user_name, String pass_word
        Integer user_id=Integer.parseInt(context.formParam("user_id"));
        String user_name=context.formParam("user_name");
        String pass_word=context.formParam("pass_word");

        userService.updateAUser(user_id,user_name,pass_word);
    }

    public void deleteUser(Context context){
        Integer user_id=Integer.parseInt(context.formParam("user_id"));
        userService.deleteAUser(user_id);

    }

    public User getOneUserByUsername(String user_name){
        return userService.getOneUserByUsername(user_name);
    }

//    public Boolean loginValidation(UserDTO userDTO){
//        return userService.loginValidation(userDTO);
//    }
    public User getOneUserByUsernameAndPassword(String user_name,String user_password){
        return userService.getOneUserByUsernameAndPassword(user_name,user_password);
    }



}
