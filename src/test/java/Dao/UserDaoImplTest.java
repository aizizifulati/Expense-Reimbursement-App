package Dao;

import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.H2Util;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {

    UserDao userDao;
    UserDaoImplTest(){
        this.userDao=new UserDaoImpl();
    }

    @BeforeEach
//    void setUp() {
//        H2Util.createTable();
////        H2Util.dropTable();
//    }
//
//    @AfterEach
//    void tearDown() {
//        H2Util.dropTable();
//    }

    @Test
    void getAllUsers() {
        List<User> actualResult=userDao.getAllUsers();
//        System.out.println(actualResult.toString());


    }

    @Test
    void getOneUser() {
        User actualResult=userDao.getOneUser(1);
//        System.out.println(actualResult.toString());
    }

    @Test
    void createUser() {
        User user1=new User(0,"archer02","123456","Sterling","Archer","archer@email.com",1);
        System.out.println(user1);
        userDao.createUser(user1);

    }

    @Test
    void updateAUser() {
        userDao.updateAUser(0,"fenix0","updatedPass");
        System.out.println(userDao.getOneUser(0).toString());;
    }


    @Test
    void deleteAUser() {
        userDao.deleteAUser(10);

    }
}