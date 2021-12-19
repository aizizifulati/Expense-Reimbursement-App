package services;import Dao.ReimbursementDao;
import Dao.UserDao;
import models.Reimbursement;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    UserDao userDao=Mockito.mock(UserDao.class);

    UserService userService;

    public UserServiceTest(){
        this.userService=new UserService(userDao);
    }
    @Test
    void getOneUserByUsernameAndPassword(){
        //arrange
        User ecpectedResult = new User(3,"testUsername3","testPassword3","test_firstname3","test_lastname3","test3@email.com",2);



        Mockito.when(userDao.getOneUserByUsernameAndPassword(ecpectedResult.getUsername(),ecpectedResult.getPassword())).thenReturn(ecpectedResult);

        //act
        User actualResult =userService.getOneUserByUsernameAndPassword(ecpectedResult.getUsername(),ecpectedResult.getPassword());

        //assert
        assertEquals(ecpectedResult,actualResult);

    }

    @Test
    void getOneUserByUsername(){
        //arrange
        User ecpectedResult = new User(3,"testUsername3","testPassword3","test_firstname3","test_lastname3","test3@email.com",2);



        Mockito.when(userDao.getOneUserByUsername(ecpectedResult.getUsername())).thenReturn(ecpectedResult);

        //act
        User actualResult =userService.getOneUserByUsername(ecpectedResult.getUsername());

        //assert
        assertEquals(ecpectedResult,actualResult);



    }


}
