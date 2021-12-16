package services;

import Dao.UserDao;
import Dao.UserDaoImpl;
import dto.UserDTO;
import Dao.UserDTODao;
import models.User;

import java.util.List;

public class UserService {
    UserDao userDao;
    UserDTODao userDTODao;

    public UserService(UserDao userDao){
        this.userDao=userDao;
    }

    public UserService(){
        this.userDao=new UserDaoImpl();
    }

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public User getOneUser(Integer user_id){
        return userDao.getOneUser(user_id);
    }

    public void createUser(User user){
        userDao.createUser(user);
    }

    public void updateAUser(Integer user_id, String user_name, String pass_word){
        userDao.updateAUser(user_id,user_name,pass_word);
    }

    public void deleteAUser(Integer user_id){
        userDao.deleteAUser(user_id);
    }

    public User getOneUserByUsername(String user_name){

        return userDao.getOneUserByUsername(user_name);}

    public Boolean loginValidation(UserDTO userDTO){
        return userDTODao.loginValidation(userDTO);
    }
    public User getOneUserByUsernameAndPassword(String user_name,String user_password){
        return userDao.getOneUserByUsernameAndPassword(user_name,user_password);
    }




}
