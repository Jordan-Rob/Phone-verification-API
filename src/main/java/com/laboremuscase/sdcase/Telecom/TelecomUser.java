package com.laboremuscase.sdcase.Telecom;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.laboremuscase.sdcase.NIRA.PhoneNumberDetail;

@Entity
@Table
public class TelecomUser {
    
    @Id
    @SequenceGenerator(
        name = "telecomUser_sequence",
        sequenceName = "telecomUser_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "telecomUser_sequence"
    )

    private Long id;
    private String firstName;
    private String middleName;
    private String surName;
    private String gender;
    private String phoneNumber;
    private String nin;
    private String status;
    private String referenceId;
    private LocalDateTime processedAt;
    private String errorCode;
    private String errorMessage;
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "telecomUser")
    private PhoneNumberDetail phoneNumberDetail;

    // Constructors
    public TelecomUser () {
    }

    public TelecomUser(Long id, String firstName, String middleName, 
        String surName, String gender, String phoneNumber, String nin, 
        String status, String referenceId, LocalDateTime processedAt, 
        String errorCode, String errorMessage, LocalDateTime createdAt, PhoneNumberDetail phoneNumberDetail) {
            
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surName = surName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.nin = nin;
        this.status = status;
        this.referenceId = referenceId;
        this.processedAt = processedAt;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.createdAt = createdAt;
        this.phoneNumberDetail = phoneNumberDetail;
    }

    public TelecomUser(String firstName, String middleName, 
        String surName, String gender, String phoneNumber, String nin, 
        String status, String referenceId, LocalDateTime processedAt, 
        String errorCode, String errorMessage, LocalDateTime createdAt, PhoneNumberDetail phoneNumberDetail) {
            
        this.firstName = firstName;
        this.middleName = middleName;
        this.surName = surName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.nin = nin;
        this.status = status;
        this.referenceId = referenceId;
        this.processedAt = processedAt;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.createdAt = createdAt;
        this.phoneNumberDetail = phoneNumberDetail;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }


    public LocalDateTime getProcessedAt() {
        return null;
    }

    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = null;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", surName=" + surName +
                ", gender='" + gender + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", nin='" + nin + '\'' +
                ", status='" + status + '\'' +
                ", referenceId='" + referenceId + '\'' +
                ", processedAt='" + processedAt + '\'' +
                ", errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                ", createdAt=" + createdAt +
                "}";
    }


}
