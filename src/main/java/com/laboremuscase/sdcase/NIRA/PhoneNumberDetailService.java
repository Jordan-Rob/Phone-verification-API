package com.laboremuscase.sdcase.NIRA;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.laboremuscase.sdcase.Telecom.TelecomUser;
import com.laboremuscase.sdcase.Telecom.TelecomUserRepository;

@Service
public class PhoneNumberDetailService {
 
    private final PhoneNumberDetailRepository phoneNumberDetailRepository;

    private final TelecomUserRepository telecomUserRepository;

    @Autowired
    public PhoneNumberDetailService(PhoneNumberDetailRepository phoneNumberDetailRepository, 
        TelecomUserRepository telecomUserRepository) {

        this.phoneNumberDetailRepository = phoneNumberDetailRepository;
        this.telecomUserRepository = telecomUserRepository;
    };

    
    // Service Method to Get All Phone Number Details
    public ApiResponse getPhoneNumberDetails( int pageNumber, int pageSize) {
        Page<PhoneNumberDetail> numbers = phoneNumberDetailRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return new ApiResponse(numbers.getSize(), numbers.getNumberOfElements(), numbers);
    };

    // Service Method to Filter Phone Number Details by TelecomUser Status
    public ApiResponse filterPhoneNumberDetailByTelecomUserStatus( String status, int pageNumber, int pageSize) {
        Page<PhoneNumberDetail> numbers = phoneNumberDetailRepository.filterPNDByTelecomUserStatus(status, PageRequest.of(pageNumber, pageSize));
        return new ApiResponse(numbers.getSize(), numbers.getNumberOfElements(), numbers);
    };

    // Service Method to Filter Phone Number Details by TelecomUser Gender
    public ApiResponse filterPhoneNumberDetailsByTelecomUserGender(String gender, int pageNumber, int pageSize) {
        Page<PhoneNumberDetail> numbers = phoneNumberDetailRepository.filterPNDByTelecomUserGender(gender, PageRequest.of(pageNumber, pageSize));
        return new ApiResponse(numbers.getSize(), numbers.getNumberOfElements(), numbers);
    };

    // Service Method to Filter Phone Number Details by TelecomUser CreateDate
    public ApiResponse filterPhoneNumberDetailsByTelecomUserCreateDate(String createDate, int pageNumber, int pageSize) {
        Page<PhoneNumberDetail> numbers = phoneNumberDetailRepository.filterPNDByTelecomUserCreateDate(createDate, PageRequest.of(pageNumber, pageSize));
        return new ApiResponse(numbers.getSize(), numbers.getNumberOfElements(), numbers);
    };

    // Service Method to return Paginated Phone Number Details
    /*
     * public ApiResponse paginatedPhoneNumberDetails(int pageNumber, int pageSize) {
     *           Page<PhoneNumberDetail> numbers = phoneNumberDetailRepository.findAll(PageRequest.of(pageNumber, pageSize));
     *        return new ApiResponse(numbers.getSize(), numbers.getNumberOfElements(), numbers);
     *     }           
     * 
     */
    
    /* 
    public List<PhoneNumberDetail> getPhoneNumberDetails() {
        return phoneNumberDetailRepository.findAll();
    };

    */


    // Service Method to verify and validate Phone Number Detail if verified a Telecom user is attached to it
    @Transactional
    public PhoneNumberDetail verifyPhoneNumberDetail(PhoneNumberDetail phoneNumberDetail) {

        Optional<TelecomUser> existingTelecomUser = telecomUserRepository.findTelecomUserByNin(phoneNumberDetail.getNin());

        if(existingTelecomUser.isEmpty()) {

            phoneNumberDetail.setValidity("invalid");
            phoneNumberDetailRepository.save(phoneNumberDetail);
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Nin entered is invalid");
            
        }

        phoneNumberDetail.setValidity("valid");
        phoneNumberDetail.setTelecomUser(existingTelecomUser.get());

        phoneNumberDetailRepository.save(phoneNumberDetail);

        return phoneNumberDetail;

    }

    // Service Method to add and Store new Phone Number Detail as well as Verifying it using the above verify method

    public PhoneNumberDetail addNewPhoneNumberDetailAndVerify(PhoneNumberDetail phoneNumberDetail) {

        Optional<PhoneNumberDetail> existingPhoneNumberDetail = phoneNumberDetailRepository.findPhoneNumberDetailByPhoneNumber(phoneNumberDetail.getPhoneNumber());

        if(existingPhoneNumberDetail.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "PhoneNumber Detail already exists!");
        }

        phoneNumberDetailRepository.save(phoneNumberDetail);

        return verifyPhoneNumberDetail(phoneNumberDetail);


    }


    // Service Method to Update Phone Number Detail and re-verify it, attaching a Telecom user if successful
    
    @Transactional
    public PhoneNumberDetail updatePhoneNumberDetail(Long phoneNumberDetailId, String referenceId, String nin, String phoneNumber) {

        PhoneNumberDetail phoneNumberDetail = phoneNumberDetailRepository.findById(phoneNumberDetailId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST , "Phone Number Detail with id" + phoneNumberDetailId + "Does not exist!"));

        if ( referenceId != null && referenceId.length() > 0 &&
            !Objects.equals(phoneNumberDetail.getReferenceId(), referenceId)) {
            
                Optional<PhoneNumberDetail> optionalPhoneNumberDetail = 
                    phoneNumberDetailRepository
                    .findPhoneNumberDetailByReferenceId(referenceId);
                if(optionalPhoneNumberDetail.isPresent()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "referenceId taken!");
                }

                phoneNumberDetail.setReferenceId(referenceId);
        }

        if ( nin != null && nin.length() > 0 &&
            !Objects.equals(phoneNumberDetail.getNin(), nin)) {
            
                phoneNumberDetail.setNin(nin);
        }

        if ( phoneNumber != null && phoneNumber.length() > 0 &&
            !Objects.equals(phoneNumberDetail.getPhoneNumber(), phoneNumber)) {
            
                Optional<PhoneNumberDetail> optionalPhoneNumberDetail = 
                    phoneNumberDetailRepository
                    .findPhoneNumberDetailByPhoneNumber(phoneNumber);
                if(optionalPhoneNumberDetail.isPresent()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "phoneNumber taken!");
                }

                phoneNumberDetail.setPhoneNumber(phoneNumber);
        }

        return verifyPhoneNumberDetail(phoneNumberDetail);


    }

    // delete Phone Number Detail 
    public void deletePhoneNumberDetail(Long phoneNumberDetailId) {
        boolean exists = phoneNumberDetailRepository.existsById(phoneNumberDetailId);

        if(!exists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , 
                "Phone Number Detail with id" + phoneNumberDetailId + "does not exist"
            );
        }
        phoneNumberDetailRepository.deleteById(phoneNumberDetailId);
    }
}
