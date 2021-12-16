package Dao;

import dto.UserDTO;
import models.User;

public interface UserDTODao {
    Boolean loginValidation(UserDTO userDTO);
}
