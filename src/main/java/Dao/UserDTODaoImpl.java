package Dao;

import dto.UserDTO;
import models.User;

public class UserDTODaoImpl implements UserDTODao{
    @Override
    public User loginValidation(UserDTO userDTO) {
        userDTO.getUsername();
    }
}
