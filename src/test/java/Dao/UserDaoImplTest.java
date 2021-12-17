package Dao;

import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import util.H2Util;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

class UserDaoImplTest {
    UserDao userDao;
    UserDaoImplTest(){
        this.userDao=new UserDaoImpl(H2Util.url,H2Util.username,H2Util.password);
    }

    @BeforeEach
    void setUp() {
        H2Util.createTable();
        H2Util.add_data02();
        H2Util.add_data03();
        H2Util.add_data05();
//        H2Util.add_data04();
    }

    @AfterEach
    void tearDown() {
        H2Util.dropTable();
    }

    @Test
    void getAllUsers() {

        //arrange
        List<User> expectedResult=new ArrayList<>();
        expectedResult.add(new User(1,"testUsername1","testPassword1","test_firstname1","test_lastname1","test1@email.com",1));
        expectedResult.add(new User(2,"testUsername2","testPassword2","test_firstname2","test_lastname2","test2@email.com",1));
        expectedResult.add(new User(3,"testUsername3","testPassword3","test_firstname3","test_lastname3","test3@email.com",2));

        userDao.createUser(expectedResult.get(0));
        userDao.createUser(expectedResult.get(1));
        userDao.createUser(expectedResult.get(2));

        //act
        List<User> actualResult =userDao.getAllUsers();
        System.out.println(actualResult.toString());
        System.out.println(expectedResult.toString());

        //assert
        assertEquals(expectedResult.toString(),actualResult.toString());
    }

    @Test
    void getOneUser() {
        //arrange
        List<User> expectedResult=new ArrayList<>();
        expectedResult.add(new User(1,"testUsername1","testPassword1","test_firstname1","test_lastname1","test1@email.com",1));
        expectedResult.add(new User(2,"testUsername2","testPassword2","test_firstname2","test_lastname2","test2@email.com",1));
        expectedResult.add(new User(3,"testUsername3","testPassword3","test_firstname3","test_lastname3","test3@email.com",2));

        userDao.createUser(expectedResult.get(0));
        userDao.createUser(expectedResult.get(1));
        userDao.createUser(expectedResult.get(2));
        //act
        User actulResult =userDao.getOneUser(1);

        //assert
        assertEquals(expectedResult.get(0).toString(),actulResult.toString());

    }

    @Test
    void createUser() {
        //arrange
        List<User> expectedResult=new ArrayList<>();
        expectedResult.add(new User(1,"testUsername1","testPassword1","test_firstname1","test_lastname1","test1@email.com",1));
        expectedResult.add(new User(2,"testUsername2","testPassword2","test_firstname2","test_lastname2","test2@email.com",1));
        expectedResult.add(new User(3,"testUsername3","testPassword3","test_firstname3","test_lastname3","test3@email.com",2));

        userDao.createUser(expectedResult.get(0));
        userDao.createUser(expectedResult.get(1));
        userDao.createUser(expectedResult.get(2));

        //act
        Integer actualResult =userDao.getAllUsers().size();

        //assert
        assertEquals(expectedResult.size(),actualResult);

    }

//    @Test
//    void updateAUser() {
//    }
//
//    @Test
//    void deleteAUser() {
//    }

    @Test
    void getOneUserByUsername() {
        //arrange
        List<User> expectedResult=new ArrayList<>();
        expectedResult.add(new User(1,"testUsername1","testPassword1","test_firstname1","test_lastname1","test1@email.com",1));
        expectedResult.add(new User(2,"testUsername1","testPassword1","test_firstname1","test_lastname1","test2@email.com",1));
        expectedResult.add(new User(3,"testUsername3","testPassword3","test_firstname3","test_lastname3","test3@email.com",2));

        userDao.createUser(expectedResult.get(0));
        userDao.createUser(expectedResult.get(1));
        userDao.createUser(expectedResult.get(2));

        //act
        User user=userDao.getOneUserByUsername("testUsername1");
        String actualUsernameResult =user.getUsername();
        String expectUsernameedResult ="testUsername1";

        //assert
        assertEquals(actualUsernameResult,expectUsernameedResult);


    }

    @Test
    void getOneUserByUsernameAndPassword() {
        //arrange
        List<User> expectedResult=new ArrayList<>();
        expectedResult.add(new User(1,"testUsername1","testPassword1","test_firstname1","test_lastname1","test1@email.com",1));
        expectedResult.add(new User(2,"testUsername2","testPassword2","test_firstname2","test_lastname2","test2@email.com",1));
        expectedResult.add(new User(3,"testUsername3","testPassword3","test_firstname3","test_lastname3","test3@email.com",2));

        userDao.createUser(expectedResult.get(0));
        userDao.createUser(expectedResult.get(1));
        userDao.createUser(expectedResult.get(2));

        //act
        User user=userDao.getOneUserByUsernameAndPassword("testUsername1","testPassword1");
        Integer actualUser_id= user.getUser_id();
        Integer expectUser_id=1;

        //assert
        assertEquals(actualUser_id,expectUser_id);
    }
}