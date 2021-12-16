package Dao;

import dto.UserDTO;
import models.User;

public class UserDTODaoImpl implements UserDTODao{

    UserDao userDao =new UserDaoImpl();
    @Override
    public Boolean loginValidation(UserDTO userDTO) {
        String user_name1=userDTO.getUsername();
        User user =userDao.getOneUserByUsername(user_name1);
        String user_password1=userDTO.getPassword();
        String user_password2=user.getPassword();
        return user_password1.equals(user_password2);



}}
