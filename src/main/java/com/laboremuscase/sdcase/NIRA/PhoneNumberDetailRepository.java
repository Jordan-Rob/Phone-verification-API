package com.laboremuscase.sdcase.NIRA;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberDetailRepository extends JpaRepository<PhoneNumberDetail, Long>{
    
    @Query("SELECT s FROM PhoneNumberDetail s WHERE s.phoneNumber = ?1")
    Optional<PhoneNumberDetail> findPhoneNumberDetailByPhoneNumber(String phoneNumber);
    
    @Query("SELECT s FROM PhoneNumberDetail s WHERE s.nin = ?1")
    Optional<PhoneNumberDetail> findPhoneNumberDetailByNin(String nin);

    @Query("SELECT s FROM PhoneNumberDetail s WHERE s.referenceId = ?1")
    Optional<PhoneNumberDetail> findPhoneNumberDetailByReferenceId(String referenceId);

    @Query("SELECT s FROM PhoneNumberDetail s WHERE s.telecomUser.status = ?1")
    Page<PhoneNumberDetail> filterPNDByTelecomUserStatus(String status, PageRequest pageRequest);

    @Query("SELECT s FROM PhoneNumberDetail s WHERE s.telecomUser.gender = ?1")
    Page<PhoneNumberDetail> filterPNDByTelecomUserGender(String gender, PageRequest pageRequest);

    @Query("SELECT s FROM PhoneNumberDetail s WHERE s.telecomUser.createdAt = ?1")
    Page<PhoneNumberDetail> filterPNDByTelecomUserCreateDate(String createDate, PageRequest pageRequest);

    /*
    @Query("SELECT s FROM PhoneNumberDetail s WHERE s.telecomUser.status = ?1")
    Optional<PhoneNumberDetail> filterPNDByTelecomUserStatus(String status);
    @Query("SELECT s FROM PhoneNumberDetail s WHERE s.telecomUser.status = ?1")
    Optional<PhoneNumberDetail> filterPNDByTelecomUserStatus(String status);
    */
}
