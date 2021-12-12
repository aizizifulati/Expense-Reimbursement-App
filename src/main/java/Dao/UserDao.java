package Dao;

import models.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getOneUser(Integer user_id);
    void createUser(User user);
    void updateAUser(Integer user_id,String user_name,String pass_word);
    void deleteAUser(Integer user_id);
    User getOneUserByUsername(String username);
}
