package com.laboremuscase.sdcase.NIRA;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.laboremuscase.sdcase.Telecom.TelecomUser;

import io.swagger.v3.oas.annotations.Hidden;

@Entity
@Table
public class PhoneNumberDetail {

    @Id
    @SequenceGenerator(
        name = "phoneNumberDetail_sequence",
        sequenceName = "phoneNumberDetail_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "phoneNumberDetail_sequence"
    )
    @Hidden
    private Long id;
    @NotBlank(message = "referenceId is required!")
    private String referenceId;
    @NotBlank(message = "nin is required!")
    private String nin;
    @NotBlank(message = "phoneNumber is required!")
    private String phoneNumber;
    @OneToOne
    @JoinColumn(name = "telecomUser_id", referencedColumnName = "id")
    @Hidden
    private TelecomUser telecomUser;
    @Hidden
    private String validity;

    // Constructors
    public PhoneNumberDetail () {
    }

    public PhoneNumberDetail(Long id, String referenceId, String nin, 
        String phoneNumber, TelecomUser telecomUser, String validity) {
            
        this.id = id;
        this.referenceId = referenceId;
        this.nin = nin;
        this.phoneNumber = phoneNumber;
        this.telecomUser = telecomUser;
        this.validity = validity;
    }

    public PhoneNumberDetail(String referenceId, String nin, 
        String phoneNumber, TelecomUser telecomUser, String validity) {
            
        this.referenceId = referenceId;
        this.nin = nin;
        this.phoneNumber = phoneNumber;
        this.telecomUser = telecomUser;
        this.validity = validity;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public TelecomUser getTelecomUser() {
        return telecomUser;
    }

    public void setTelecomUser(TelecomUser telecomUser) {
        this.telecomUser = telecomUser;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    @Override
    public String toString() {
        return "PhoneNumberDetail {" +
                "id=" + id +
                ", referenceId='" + referenceId + '\'' +
                ", nin='" + nin + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", telecomUser=" + telecomUser +
                ", validity=" + validity +
                "}";
    }


}
