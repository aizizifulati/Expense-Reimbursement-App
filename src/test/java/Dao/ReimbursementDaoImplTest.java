package Dao;

import models.Reimbursement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import util.H2Util;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

class ReimbursementDaoImplTest {

    ReimbursementDao reimbursementDao;
    ReimbursementDaoImplTest(){
        this.reimbursementDao=new ReimbursementDaoImpl(H2Util.url,H2Util.username,H2Util.password);
    }

    @BeforeEach
    void setUp() {
         H2Util.createTable();
        H2Util.add_data02();
        H2Util.add_data03();
        H2Util.add_data05();
        H2Util.add_data04();
    }

    @AfterEach
    void tearDown() {
        H2Util.dropTable();
    }

    @Test
    void getAllReimbursements() {
        //ARRANGE
        List<Reimbursement> expectedResult=new ArrayList<>();
        Timestamp submitted1=new Timestamp(System.currentTimeMillis());
        Timestamp resolved1=new Timestamp(System.currentTimeMillis());
        Timestamp submitted2=new Timestamp(System.currentTimeMillis());
        Timestamp resolved2=new Timestamp(System.currentTimeMillis());
        Timestamp submitted3=new Timestamp(System.currentTimeMillis());
        Timestamp resolved3=new Timestamp(System.currentTimeMillis());


        expectedResult.add(new Reimbursement(1,600,submitted1,resolved1,2,"relocation fee",1,1,3));
        expectedResult.add(new Reimbursement(2,3800,submitted2,resolved2,2,"OCA Training fee",2,2,1));
        expectedResult.add(new Reimbursement(3,99999,submitted3,resolved3,2,"new pc and laptop",3,1,2));
//

        reimbursementDao.createReimbursement(expectedResult.get(0));
        reimbursementDao.createReimbursement(expectedResult.get(1));
        reimbursementDao.createReimbursement(expectedResult.get(2));


        //act
        List<Reimbursement> actualResult=reimbursementDao.getAllReimbursements();

        //assert
        assertEquals(expectedResult.toString(),actualResult.toString());
//
//
//
//        System.out.println(expectedResult);
//        reimbursementDao.createReimbursement();
//        reimbursementDao.createReimbursement(expectedResult.get(1));
//        reimbursementDao.createReimbursement(expectedResult.get(1));
//        List<Reimbursement> actualResult =reimbursementDao.getAllReimbursements();
//        System.out.println(actualResult);


//
//

//        H2Util.dropTable();
//        H2Util.dropTable();


    }

    @Test
    void getOneReimbursement() {

        //ARRANGE
        List<Reimbursement> expectedResult=new ArrayList<>();
        Timestamp submitted1=new Timestamp(System.currentTimeMillis());
        Timestamp resolved1=new Timestamp(System.currentTimeMillis());
        Timestamp submitted2=new Timestamp(System.currentTimeMillis());
        Timestamp resolved2=new Timestamp(System.currentTimeMillis());
        Timestamp submitted3=new Timestamp(System.currentTimeMillis());
        Timestamp resolved3=new Timestamp(System.currentTimeMillis());


        expectedResult.add(new Reimbursement(1,600,submitted1,resolved1,2,"relocation fee",1,1,3));
        expectedResult.add(new Reimbursement(2,3800,submitted2,resolved2,2,"OCA Training fee",2,2,1));
        expectedResult.add(new Reimbursement(3,99999,submitted3,resolved3,2,"new pc and laptop",3,1,2));


        reimbursementDao.createReimbursement(expectedResult.get(0));
        reimbursementDao.createReimbursement(expectedResult.get(1));
        reimbursementDao.createReimbursement(expectedResult.get(2));

        //act
        Reimbursement actualResult =reimbursementDao.getOneReimbursement(1);

        //assert
        assertEquals(expectedResult.get(0).toString(),actualResult.toString());





    }

    @Test
    void createReimbursement() {



        //ARRANGE
        List<Reimbursement> expectedResult=new ArrayList<>();
        Timestamp submitted1=new Timestamp(System.currentTimeMillis());
        Timestamp resolved1=new Timestamp(System.currentTimeMillis());
        Timestamp submitted2=new Timestamp(System.currentTimeMillis());
        Timestamp resolved2=new Timestamp(System.currentTimeMillis());
        Timestamp submitted3=new Timestamp(System.currentTimeMillis());
        Timestamp resolved3=new Timestamp(System.currentTimeMillis());


        expectedResult.add(new Reimbursement(1,600,submitted1,resolved1,2,"relocation fee",1,1,3));
        expectedResult.add(new Reimbursement(2,3800,submitted2,resolved2,2,"OCA Training fee",2,2,1));
        expectedResult.add(new Reimbursement(3,99999,submitted3,resolved3,2,"new pc and laptop",3,1,2));


        reimbursementDao.createReimbursement(expectedResult.get(0));
        reimbursementDao.createReimbursement(expectedResult.get(1));
        reimbursementDao.createReimbursement(expectedResult.get(2));

        //act
        Integer actualResult =reimbursementDao.getAllReimbursements().size();

        //assert
        assertEquals(expectedResult.size(),actualResult);

    }


    @Test
    void deleteAReimbursement() {
        //ARRANGE
        List<Reimbursement> expectedResult=new ArrayList<>();
        Timestamp submitted1=new Timestamp(System.currentTimeMillis());
        Timestamp resolved1=new Timestamp(System.currentTimeMillis());
        Timestamp submitted2=new Timestamp(System.currentTimeMillis());
        Timestamp resolved2=new Timestamp(System.currentTimeMillis());
        Timestamp submitted3=new Timestamp(System.currentTimeMillis());
        Timestamp resolved3=new Timestamp(System.currentTimeMillis());


        expectedResult.add(new Reimbursement(1,600,submitted1,resolved1,2,"relocation fee",1,1,3));
        expectedResult.add(new Reimbursement(2,3800,submitted2,resolved2,2,"OCA Training fee",2,2,1));
        expectedResult.add(new Reimbursement(3,99999,submitted3,resolved3,2,"new pc and laptop",3,1,2));

        reimbursementDao.createReimbursement(expectedResult.get(0));
        reimbursementDao.createReimbursement(expectedResult.get(1));
        reimbursementDao.createReimbursement(expectedResult.get(2));

        //ACT
        reimbursementDao.deleteAReimbursement(1);
        expectedResult.remove(0);


        List<Reimbursement> actualResult =reimbursementDao.getAllReimbursements();

        //assert
        assertEquals(expectedResult.toString(),actualResult.toString());
        assertNull(reimbursementDao.getOneReimbursement(1));
    }

    @Test
    void getAllReimbursementsByuser_id() {
        //ARRANGE
        List<Reimbursement> expectedResult=new ArrayList<>();
        Timestamp submitted1=new Timestamp(System.currentTimeMillis());
        Timestamp resolved1=new Timestamp(System.currentTimeMillis());
        Timestamp submitted2=new Timestamp(System.currentTimeMillis());
        Timestamp resolved2=new Timestamp(System.currentTimeMillis());
        Timestamp submitted3=new Timestamp(System.currentTimeMillis());
        Timestamp resolved3=new Timestamp(System.currentTimeMillis());


        expectedResult.add(new Reimbursement(1,600,submitted1,resolved1,2,"relocation fee",1,1,3));
        expectedResult.add(new Reimbursement(2,3800,submitted2,resolved2,2,"OCA Training fee",1,2,1));
        expectedResult.add(new Reimbursement(3,99999,submitted3,resolved3,2,"new pc and laptop",3,1,2));

        reimbursementDao.createReimbursement(expectedResult.get(0));
        reimbursementDao.createReimbursement(expectedResult.get(1));
        reimbursementDao.createReimbursement(expectedResult.get(2));

        //act
        List<Reimbursement> reimbursements =reimbursementDao.getAllReimbursementsByuser_id(1);
        Integer actualId1=reimbursements.get(0).getAuthor();
        Integer actualId2=reimbursements.get(1).getAuthor();
        Integer expectedId=1;

        //assert
        assertEquals(actualId1,expectedId);
        assertEquals(actualId2,expectedId);


    }



    @Test
    void getAllUnresolvedReimbursements() {
        //ARRANGE
        List<Reimbursement> expectedResult=new ArrayList<>();
        Timestamp submitted1=new Timestamp(System.currentTimeMillis());
        Timestamp resolved1=new Timestamp(System.currentTimeMillis());
        Timestamp submitted2=new Timestamp(System.currentTimeMillis());
        Timestamp resolved2=new Timestamp(System.currentTimeMillis());
        Timestamp submitted3=new Timestamp(System.currentTimeMillis());
        Timestamp resolved3=new Timestamp(System.currentTimeMillis());


        expectedResult.add(new Reimbursement(1,600,submitted1,resolved1,2,"relocation fee",1,1,3));
        expectedResult.add(new Reimbursement(2,3800,submitted2,resolved2,2,"OCA Training fee",1,2,1));
        expectedResult.add(new Reimbursement(3,99999,submitted3,resolved3,2,"new pc and laptop",3,1,2));

        reimbursementDao.createReimbursement(expectedResult.get(0));
        reimbursementDao.createReimbursement(expectedResult.get(1));
        reimbursementDao.createReimbursement(expectedResult.get(2));

        //act
        List<Reimbursement> reimbursements=reimbursementDao.getAllUnresolvedReimbursements();
        Integer actualStatus_id1=reimbursements.get(0).getStatus_id();
        Integer actualStatus_id2=reimbursements.get(1).getStatus_id();
        Integer expectedStatus_id=1;

        //assert
        assertEquals(actualStatus_id1,actualStatus_id2);

    }

    @Test
    void approveAReimbursement() {
        //ARRANGE
        List<Reimbursement> expectedResult=new ArrayList<>();
        Timestamp submitted1=new Timestamp(System.currentTimeMillis());
        Timestamp resolved1=new Timestamp(System.currentTimeMillis());
        Timestamp submitted2=new Timestamp(System.currentTimeMillis());
        Timestamp resolved2=new Timestamp(System.currentTimeMillis());
        Timestamp submitted3=new Timestamp(System.currentTimeMillis());
        Timestamp resolved3=new Timestamp(System.currentTimeMillis());

        Timestamp resolved4=new Timestamp(System.currentTimeMillis());


        expectedResult.add(new Reimbursement(1,600,submitted1,resolved1,2,"relocation fee",1,1,3));
        expectedResult.add(new Reimbursement(2,3800,submitted2,resolved2,2,"OCA Training fee",1,2,1));
        expectedResult.add(new Reimbursement(3,99999,submitted3,resolved3,2,"new pc and laptop",3,1,2));

        reimbursementDao.createReimbursement(expectedResult.get(0));
        reimbursementDao.createReimbursement(expectedResult.get(1));
        reimbursementDao.createReimbursement(expectedResult.get(2));

        //act
        reimbursementDao.approveAReimbursement(1,resolved4,2);
        Integer actualStatusId=reimbursementDao.getOneReimbursement(1).getStatus_id();
        Integer expectedStatusId=2;

        //assert
        assertEquals(actualStatusId,expectedStatusId);

    }

    @Test
    void denyAReimbursement() {
        //ARRANGE
        List<Reimbursement> expectedResult=new ArrayList<>();
        Timestamp submitted1=new Timestamp(System.currentTimeMillis());
        Timestamp resolved1=new Timestamp(System.currentTimeMillis());
        Timestamp submitted2=new Timestamp(System.currentTimeMillis());
        Timestamp resolved2=new Timestamp(System.currentTimeMillis());
        Timestamp submitted3=new Timestamp(System.currentTimeMillis());
        Timestamp resolved3=new Timestamp(System.currentTimeMillis());

        Timestamp resolved4=new Timestamp(System.currentTimeMillis());


        expectedResult.add(new Reimbursement(1,600,submitted1,resolved1,2,"relocation fee",1,1,3));
        expectedResult.add(new Reimbursement(2,3800,submitted2,resolved2,2,"OCA Training fee",1,2,1));
        expectedResult.add(new Reimbursement(3,99999,submitted3,resolved3,2,"new pc and laptop",3,1,2));

        reimbursementDao.createReimbursement(expectedResult.get(0));
        reimbursementDao.createReimbursement(expectedResult.get(1));
        reimbursementDao.createReimbursement(expectedResult.get(2));

        //act
        reimbursementDao.denyAReimbursement(1,resolved4,2);
        Integer actualStatusId=reimbursementDao.getOneReimbursement(1).getStatus_id();
        Integer expectedStatusId=3;

        //assert
        assertEquals(actualStatusId,expectedStatusId);

    }
}