package com.laboremuscase.sdcase.NIRA;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.laboremuscase.sdcase.Telecom.TelecomUser;
import com.laboremuscase.sdcase.Telecom.TelecomUserRepository;

@DataJpaTest
public class PhoneNumberDetailRepositoryTest {
    
    @Autowired
    private PhoneNumberDetailRepository undertest;

    @Autowired
    private TelecomUserRepository telecomRepo;

    @AfterEach
    void tearDown() {
        undertest.deleteAll();
        telecomRepo.deleteAll();
    }

    @Test
    void findPhoneNumberDetailByNin() {
        //given
        PhoneNumberDetail numberDetail = new PhoneNumberDetail(
            1l,
            "h689l088-e543-44kn-7k90-2321fg390006",
            "CM67778900LCC3",
            "0778890012",
            null,
            null

        );

        undertest.save(numberDetail);

        //when
        boolean numberDetailFound = undertest.findPhoneNumberDetailByNin("CM67778900LCC3").isPresent();

        assertThat(numberDetailFound).isTrue();
    }

    @Test
    void findPhoneNumberDetailByReferenceId() {
        //given
        PhoneNumberDetail numberDetail = new PhoneNumberDetail(
            1l,
            "h689l088-e543-44kn-7k90-2321fg390006",
            "CM67778900LCC3",
            "0778890012",
            null,
            null

        );

        undertest.save(numberDetail);

        //when
        boolean numberDetailFound = undertest.findPhoneNumberDetailByReferenceId("h689l088-e543-44kn-7k90-2321fg390006").isPresent();

        assertThat(numberDetailFound).isTrue();
    }

    @Test
    void findPhoneNumberDetailByPhoneNumber() {
        //given
        PhoneNumberDetail numberDetail = new PhoneNumberDetail(
            1l,
            "h689l088-e543-44kn-7k90-2321fg390006",
            "CM67778900LCC3",
            "0778890012",
            null,
            null

        );

        undertest.save(numberDetail);

        //when
        boolean numberDetailFound = undertest.findPhoneNumberDetailByPhoneNumber("0778890012").isPresent();

        assertThat(numberDetailFound).isTrue();
    }

    
    @Test
    void filterPhoneNumberDetailByTelecomUserStatus() {
        //given
        TelecomUser telecomUser = new TelecomUser(
            "Chris",
                "Micheal",
                "Mubiru",
                "MALE",
                "0778902311",
                "CM993899JKL",
                "Active",
                "f456w233-e455-67ew-7f90-2321fg390006",
                null,
                null,
                null,
                LocalDateTime.now(),
                null
            );
        
        telecomRepo.save(telecomUser);

        TelecomUser savedTelecomUser = telecomRepo.findTelecomUserByNin("CM993899JKL").get();


        PhoneNumberDetail numberDetail = new PhoneNumberDetail(
            1l,
            "h689l088-e543-44kn-7k90-2321fg390006",
            "CM67778900LCC3",
            "0778890012",
            savedTelecomUser,
            null

        );

        undertest.save(numberDetail);

        //when
        Page<PhoneNumberDetail> numberDetailFound = undertest.filterPNDByTelecomUserStatus("Active", PageRequest.of(0, 5));

        assertThat(numberDetailFound.getNumberOfElements()).isEqualTo(1);
    }

    @Test
    void filterPhoneNumberDetailByTelecomUserGender() {
        //given
        TelecomUser telecomUser = new TelecomUser(
            "Chris",
                "Micheal",
                "Mubiru",
                "MALE",
                "0778902311",
                "CM993899JKL",
                "Active",
                "f456w233-e455-67ew-7f90-2321fg390006",
                null,
                null,
                null,
                LocalDateTime.now(),
                null
            );
        
        telecomRepo.save(telecomUser);

        TelecomUser savedTelecomUser = telecomRepo.findTelecomUserByNin("CM993899JKL").get();


        PhoneNumberDetail numberDetail = new PhoneNumberDetail(
            1l,
            "h689l088-e543-44kn-7k90-2321fg390006",
            "CM67778900LCC3",
            "0778890012",
            savedTelecomUser,
            null

        );

        undertest.save(numberDetail);

        //when
        Page<PhoneNumberDetail> numberDetailFound = undertest.filterPNDByTelecomUserGender("MALE", PageRequest.of(0, 5));

        assertThat(numberDetailFound.getNumberOfElements()).isEqualTo(1);
    }

    @Test
    void filterPhoneNumberDetailsByTelecomUserCreateDate() {
        //given
        TelecomUser telecomUser = new TelecomUser(
            "Chris",
                "Micheal",
                "Mubiru",
                "MALE",
                "0778902311",
                "CM993899JKL",
                "Active",
                "f456w233-e455-67ew-7f90-2321fg390006",
                null,
                null,
                null,
                LocalDateTime.now(),
                null
            );
        
        telecomRepo.save(telecomUser);

        TelecomUser savedTelecomUser = telecomRepo.findTelecomUserByNin("CM993899JKL").get();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

        String formattedDateTime = savedTelecomUser.getCreatedAt().format(dateTimeFormatter);

        //System.out.println(savedTelecomUser.getCreatedAt());

        PhoneNumberDetail numberDetail = new PhoneNumberDetail(
            1l,
            "h689l088-e543-44kn-7k90-2321fg390006",
            "CM67778900LCC3",
            "0778890012",
            savedTelecomUser,
            null

        );

        undertest.save(numberDetail);

        //when
        Page<PhoneNumberDetail> numberDetailFound = undertest.filterPNDByTelecomUserCreateDate( formattedDateTime, PageRequest.of(0, 5));

        assertThat(numberDetailFound.getNumberOfElements()).isEqualTo(1);
    }


}
