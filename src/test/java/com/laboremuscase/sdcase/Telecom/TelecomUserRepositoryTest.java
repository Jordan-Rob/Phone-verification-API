package com.laboremuscase.sdcase.Telecom;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TelecomUserRepositoryTest {
    
    @Autowired
    private TelecomUserRepository undertest;

    @AfterEach
    void tearDown() {
        undertest.deleteAll();
    }

    @Test
    void findTelecomUserByNin() {
        //given 
        TelecomUser thelma = new TelecomUser(
            "Thelma",
            "Michelle",
            "Agwang",
            "FEMALE",
            "0708992222",
            "CM100999876LC23",
            "Active",
            "f677g889-s455-78ww-8u80-2321fg390006",
            null,
            null,
            null,
            LocalDateTime.now(),
            null
        );

        undertest.save(thelma);

        //when
        boolean telecomUserFound = undertest.findTelecomUserByNin("CM100999876LC23").isPresent();

        //then 
        assertThat(telecomUserFound).isTrue();

    }

    @Test
    void findTelecomUserByPhoneNumber() {
        //given 
        TelecomUser thelma = new TelecomUser(
            "Thelma",
            "Michelle",
            "Agwang",
            "FEMALE",
            "0708992222",
            "CM100999876LC23",
            "Active",
            "f677g889-s455-78ww-8u80-2321fg390006",
            null,
            null,
            null,
            LocalDateTime.now(),
            null
        );

        undertest.save(thelma);

        //when
        boolean telecomUserFound = undertest.findTelecomUserByPhoneNumber("0708992222").isPresent();

        //then 
        assertThat(telecomUserFound).isTrue();

    }
}
