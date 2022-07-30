package com.laboremuscase.sdcase.Telecom;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TelecomUserRepository extends JpaRepository<TelecomUser, Long> {
    
    @Query("SELECT s FROM TelecomUser s WHERE s.phoneNumber = ?1")
    Optional<TelecomUser> findTelecomUserByPhoneNumber(String phoneNumber);
    
    @Query("SELECT s FROM TelecomUser s WHERE s.nin = ?1")
    Optional<TelecomUser> findTelecomUserByNin(String nin);

    

}
