package com.laboremuscase.sdcase.NIRA;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.laboremuscase.sdcase.Telecom.TelecomUser;
import com.laboremuscase.sdcase.Telecom.TelecomUserRepository;

public class phoneNumberDetailServiceTest {
    
    @Mock
    private PhoneNumberDetailRepository phoneNumberDetailRepository;
    
    @Mock
    private TelecomUserRepository telecomUserRepository;

    private AutoCloseable autoCloseable;
    private PhoneNumberDetailService underTest;

    //private ApiResponse phoneNumberDetails;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new PhoneNumberDetailService(phoneNumberDetailRepository, telecomUserRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getPhoneNumberDetails() {
        //when
        int pageNumber = 0;
        int pageSize = 10;

        PhoneNumberDetail one = new PhoneNumberDetail(
            1l,
            "h689l088-e543-44kn-7k90-2321fg390006",
            "CM67778900LCC3",
            "0778890012",
            null,
            null

        );

        PhoneNumberDetail two = new PhoneNumberDetail(
            1l,
            "f677g889-s455-78ww-8u80-2321fg390006",
            "CM100999876LC23",
            "0708992222",
            null,
            null

        );

        List<PhoneNumberDetail> numbers = new ArrayList<>();
        numbers.addAll(List.of(one, two));

        Page<PhoneNumberDetail> numbersPage = new PageImpl<>(numbers);

        //when(phoneNumberDetailRepository.save(any(PhoneNumberDetail.class))).thenReturn(one) ;
        //PhoneNumberDetail saved = phoneNumberDetailRepository.save(one);
        //assertThat(saved.getPhoneNumber()).isNotNull();

        when(phoneNumberDetailRepository.findAll(PageRequest.of(pageNumber, pageSize))).thenReturn(numbersPage);


        underTest.getPhoneNumberDetails(pageNumber, pageSize);

        //then
        verify(phoneNumberDetailRepository).findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Test
    void filterPhoneNumberDetailByTelecomUserStatus() {
        //when
        int pageNumber = 0;
        int pageSize = 10;

        
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

        when(telecomUserRepository.save(any(TelecomUser.class))).thenReturn(thelma);
        TelecomUser savedTelecomUser = telecomUserRepository.save(thelma);

        PhoneNumberDetail one = new PhoneNumberDetail(
            1l,
            "h689l088-e543-44kn-7k90-2321fg390006",
            "CM67778900LCC3",
            "0778890012",
            savedTelecomUser,
            null

        );

        
        List<PhoneNumberDetail> numbers = new ArrayList<>();
        numbers.add(one);

        Page<PhoneNumberDetail> numbersPage = new PageImpl<>(numbers);

        //when(phoneNumberDetailRepository.save(any(PhoneNumberDetail.class))).thenReturn(one) ;
        //PhoneNumberDetail saved = phoneNumberDetailRepository.save(one);
        //assertThat(saved.getPhoneNumber()).isNotNull();

        when(phoneNumberDetailRepository.filterPNDByTelecomUserStatus("Active",PageRequest.of(pageNumber, pageSize))).thenReturn(numbersPage);


        underTest.filterPhoneNumberDetailByTelecomUserStatus("Active", pageNumber, pageSize);

        //then
        verify(phoneNumberDetailRepository).filterPNDByTelecomUserStatus("Active", PageRequest.of(pageNumber, pageSize));
    }

    @Test
    void filterPhoneNumberDetailsByTelecomUserGender() {
        //when
        int pageNumber = 0;
        int pageSize = 10;

        
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

        when(telecomUserRepository.save(any(TelecomUser.class))).thenReturn(thelma);
        TelecomUser savedTelecomUser = telecomUserRepository.save(thelma);

        PhoneNumberDetail one = new PhoneNumberDetail(
            1l,
            "h689l088-e543-44kn-7k90-2321fg390006",
            "CM67778900LCC3",
            "0778890012",
            savedTelecomUser,
            null

        );

        
        List<PhoneNumberDetail> numbers = new ArrayList<>();
        numbers.add(one);

        Page<PhoneNumberDetail> numbersPage = new PageImpl<>(numbers);

        //when(phoneNumberDetailRepository.save(any(PhoneNumberDetail.class))).thenReturn(one) ;
        //PhoneNumberDetail saved = phoneNumberDetailRepository.save(one);
        //assertThat(saved.getPhoneNumber()).isNotNull();

        when(phoneNumberDetailRepository.filterPNDByTelecomUserGender("FEMALE",PageRequest.of(pageNumber, pageSize))).thenReturn(numbersPage);


        underTest.filterPhoneNumberDetailsByTelecomUserGender("FEMALE", pageNumber, pageSize);

        //then
        verify(phoneNumberDetailRepository).filterPNDByTelecomUserGender("FEMALE", PageRequest.of(pageNumber, pageSize));
    }


    @Test
    void filterPhoneNumberDetailsByTelecomUserCreateDate() {
        //when
        int pageNumber = 0;
        int pageSize = 10;

        
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

        when(telecomUserRepository.save(any(TelecomUser.class))).thenReturn(thelma);
        TelecomUser savedTelecomUser = telecomUserRepository.save(thelma);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

        String formattedDateTime = savedTelecomUser.getCreatedAt().format(dateTimeFormatter);

        PhoneNumberDetail one = new PhoneNumberDetail(
            1l,
            "h689l088-e543-44kn-7k90-2321fg390006",
            "CM67778900LCC3",
            "0778890012",
            savedTelecomUser,
            null

        );

        
        List<PhoneNumberDetail> numbers = new ArrayList<>();
        numbers.add(one);

        Page<PhoneNumberDetail> numbersPage = new PageImpl<>(numbers);

        //when(phoneNumberDetailRepository.save(any(PhoneNumberDetail.class))).thenReturn(one) ;
        //PhoneNumberDetail saved = phoneNumberDetailRepository.save(one);
        //assertThat(saved.getPhoneNumber()).isNotNull();

        when(phoneNumberDetailRepository.filterPNDByTelecomUserCreateDate( formattedDateTime,PageRequest.of(pageNumber, pageSize))).thenReturn(numbersPage);


        underTest.filterPhoneNumberDetailsByTelecomUserCreateDate( formattedDateTime, pageNumber, pageSize);

        //then
        verify(phoneNumberDetailRepository).filterPNDByTelecomUserCreateDate( formattedDateTime, PageRequest.of(pageNumber, pageSize));
    }



    
}
