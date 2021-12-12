package Dao;

import dto.UserDTO;
import models.User;

public interface UserDTODao {
    User loginValidation(UserDTO userDTO);
}
