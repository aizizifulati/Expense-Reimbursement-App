package services;

import Dao.UserDao;
import Dao.UserDaoImpl;
import models.User;

import java.util.List;

public class UserService {
    UserDao userDao;

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





}
