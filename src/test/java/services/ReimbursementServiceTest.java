package services;

import Dao.ReimbursementDao;
import models.Reimbursement;
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

class ReimbursementServiceTest {

    ReimbursementDao reimbursementDao=Mockito.mock(ReimbursementDao.class);

    ReimbursementService reimbursementService;

    public ReimbursementServiceTest(){
        this.reimbursementService=new ReimbursementService(reimbursementDao);
    }



    @Test
    void getAllReimbursements() {
        //ARRANGE
        List<Reimbursement> reimbursements=new ArrayList<>();
        Timestamp submitted1=new Timestamp(System.currentTimeMillis());
        Timestamp resolved1=new Timestamp(System.currentTimeMillis());
        Timestamp submitted2=new Timestamp(System.currentTimeMillis());
        Timestamp resolved2=new Timestamp(System.currentTimeMillis());
        Timestamp submitted3=new Timestamp(System.currentTimeMillis());
        Timestamp resolved3=new Timestamp(System.currentTimeMillis());

        Timestamp resolved4=new Timestamp(System.currentTimeMillis());


        reimbursements.add(new Reimbursement(1,600,submitted1,resolved1,2,"relocation fee",1,1,3));
        reimbursements.add(new Reimbursement(2,3800,submitted2,resolved2,2,"OCA Training fee",1,2,1));
        reimbursements.add(new Reimbursement(3,99999,submitted3,resolved3,2,"new pc and laptop",3,1,2));

        List<Reimbursement> expectedValue=reimbursements;
        Mockito.when(reimbursementDao.getAllReimbursements()).thenReturn(reimbursements);

        //act
        List<Reimbursement> actualResult =reimbursementDao.getAllReimbursements();

        //assert
        assertEquals(expectedValue,actualResult);




    }

    @Test
    void getOneReimbursement() {

        //arrange
        Timestamp submitted1=new Timestamp(System.currentTimeMillis());
        Timestamp resolved1=new Timestamp(System.currentTimeMillis());

        Reimbursement expectedResult =new Reimbursement(1,600,submitted1,resolved1,2,"relocation fee",1,1,3);
        Mockito.when(reimbursementDao.getOneReimbursement(expectedResult.getId())).thenReturn(expectedResult);

        //act
        Reimbursement actualResult =reimbursementService.getOneReimbursement(expectedResult.getId());


        //assert
        assertEquals(expectedResult,actualResult);

    }

    @Test
    void createReimbursement() {

        //arrange
        Timestamp submitted1=new Timestamp(System.currentTimeMillis());
        Timestamp resolved1=new Timestamp(System.currentTimeMillis());
//        Integer reimId=1;

        Reimbursement reimbursement =new Reimbursement(1,600,submitted1,resolved1,2,"relocation fee",1,1,3);

        //act
        reimbursementService.createReimbursement(reimbursement);

        //assert
        Mockito.verify(reimbursementDao,Mockito.times(1)).createReimbursement(reimbursement);

    }



    @Test
    void getAllReimbursementsByuser_id() {

        //ARRANGE
        List<Reimbursement> reimbursements=new ArrayList<>();
        Timestamp submitted1=new Timestamp(System.currentTimeMillis());
        Timestamp resolved1=new Timestamp(System.currentTimeMillis());
        Timestamp submitted2=new Timestamp(System.currentTimeMillis());
        Timestamp resolved2=new Timestamp(System.currentTimeMillis());
        Timestamp submitted3=new Timestamp(System.currentTimeMillis());
        Timestamp resolved3=new Timestamp(System.currentTimeMillis());

        Timestamp resolved4=new Timestamp(System.currentTimeMillis());
        Reimbursement reimbursement01 =new Reimbursement(1,600,submitted1,resolved1,2,"relocation fee",1,1,3);
        Reimbursement reimbursement02=new Reimbursement(2,3800,submitted2,resolved2,2,"OCA Training fee",1,2,1);
        Reimbursement reimbursement03 =new Reimbursement(3,99999,submitted3,resolved3,2,"new pc and laptop",3,1,2);


        reimbursements.add(reimbursement01);
        reimbursements.add(reimbursement02);
        reimbursements.add(reimbursement03);


        List<Reimbursement> expectedResult=new ArrayList<>();
        expectedResult.add(reimbursement03);
        Mockito.when(reimbursementDao.getAllReimbursementsByuser_id(3)).thenReturn(expectedResult);

        //act
        List<Reimbursement> actualResult =reimbursementDao.getAllReimbursementsByuser_id(3);

        //assert
        assertEquals(expectedResult,actualResult);


    }

    @Test
    void getAllUnresolvedReimbursements() {

        //arrange
        //ARRANGE
        List<Reimbursement> reimbursements=new ArrayList<>();
        Timestamp submitted1=new Timestamp(System.currentTimeMillis());
        Timestamp resolved1=new Timestamp(System.currentTimeMillis());
        Timestamp submitted2=new Timestamp(System.currentTimeMillis());
        Timestamp resolved2=new Timestamp(System.currentTimeMillis());
        Timestamp submitted3=new Timestamp(System.currentTimeMillis());
        Timestamp resolved3=new Timestamp(System.currentTimeMillis());

        Timestamp resolved4=new Timestamp(System.currentTimeMillis());
        Reimbursement reimbursement01 =new Reimbursement(1,600,submitted1,resolved1,2,"relocation fee",1,1,3);
        Reimbursement reimbursement02=new Reimbursement(2,3800,submitted2,resolved2,2,"OCA Training fee",1,2,1);
        Reimbursement reimbursement03 =new Reimbursement(3,99999,submitted3,resolved3,2,"new pc and laptop",3,1,2);


        reimbursements.add(reimbursement01);
        reimbursements.add(reimbursement02);
        reimbursements.add(reimbursement03);


        List<Reimbursement> expectedResult=new ArrayList<>();
        expectedResult.add(reimbursement01);
        expectedResult.add(reimbursement03);
        Mockito.when(reimbursementDao.getAllUnresolvedReimbursements()).thenReturn(expectedResult);

        //act
        List<Reimbursement> actualResult=reimbursementService.getAllUnresolvedReimbursements();

        //assert
        assertEquals(expectedResult,actualResult);



    }

    @Test
    void approveAReimbursement() {
        //arrange
        Integer reimId=1;
        Timestamp resolved1=new Timestamp(System.currentTimeMillis());
        Integer resolver1=2;

        //act
        reimbursementService.approveAReimbursement(reimId,resolved1,resolver1);

        //assert
        Mockito.verify(reimbursementDao,Mockito.times(1)).approveAReimbursement(reimId,resolved1,resolver1);
    }

    @Test
    void denyAReimbursement() {
        //arrange
        Integer reimId=1;
        Timestamp resolved1=new Timestamp(System.currentTimeMillis());
        Integer resolver1=2;

        //act
        reimbursementService.denyAReimbursement(reimId,resolved1,resolver1);

        //assert
        Mockito.verify(reimbursementDao,Mockito.times(1)).denyAReimbursement(reimId,resolved1,resolver1);
    }
}