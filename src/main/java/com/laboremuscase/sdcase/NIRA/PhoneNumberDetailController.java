package com.laboremuscase.sdcase.NIRA;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/NIRA-phoneVerification")
public class PhoneNumberDetailController {
    
    private final PhoneNumberDetailService phoneNumberDetailService;

    public PhoneNumberDetailController(PhoneNumberDetailService phoneNumberDetailService) {
        this.phoneNumberDetailService =  phoneNumberDetailService;
    }

    // Get End Point retrieving Phone Number Details 
    @GetMapping(path = "phone-number-details")
    public ResponseEntity<ApiResponse> getPhoneNumberDetails(
        @RequestParam(required = false) String status,
        @RequestParam(required = false) String gender,
        @RequestParam(required = false) String createDate,
        @RequestParam(required = true) int pageNumber,
        @RequestParam(required = true) int pageSize

    ) {
        if( status != null ) {
            ApiResponse apiResponse = phoneNumberDetailService.filterPhoneNumberDetailByTelecomUserStatus(status, pageNumber, pageSize);
            String totalCountKey = "totalCount";
            String totalCountValue = String.valueOf(apiResponse.getRecordCount());
            String pageNumberKey = "pageNumber";
            String pageNumberValue = String.valueOf(pageNumber);
            String pageSizeKey = "pageSize";
            String pageSizeValue = String.valueOf(apiResponse.getSizeOfPage());
            ApiResponse responseBody = apiResponse;

            return ResponseEntity.ok()
                .header(totalCountKey, totalCountValue)
                .header(pageNumberKey, pageNumberValue)
                .header(pageSizeKey, pageSizeValue)
                .body(responseBody);
        };
        
        if( gender != null ) {
            ApiResponse apiResponse = phoneNumberDetailService.filterPhoneNumberDetailsByTelecomUserGender(gender, pageNumber, pageSize);
            String totalCountKey = "totalCount";
            String totalCountValue = String.valueOf(apiResponse.getRecordCount());
            String pageNumberKey = "pageNumber";
            String pageNumberValue = String.valueOf(pageNumber);
            String pageSizeKey = "pageSize";
            String pageSizeValue = String.valueOf(apiResponse.getSizeOfPage());
            ApiResponse responseBody = apiResponse;

            return ResponseEntity.ok()
                .header(totalCountKey, totalCountValue)
                .header(pageNumberKey, pageNumberValue)
                .header(pageSizeKey, pageSizeValue)
                .body(responseBody);
        };

        if( createDate != null ) {
            ApiResponse apiResponse = phoneNumberDetailService.filterPhoneNumberDetailsByTelecomUserCreateDate(createDate, pageNumber, pageSize);
            String totalCountKey = "totalCount";
            String totalCountValue = String.valueOf(apiResponse.getRecordCount());
            String pageNumberKey = "pageNumber";
            String pageNumberValue = String.valueOf(pageNumber);
            String pageSizeKey = "pageSize";
            String pageSizeValue = String.valueOf(apiResponse.getSizeOfPage());
            ApiResponse responseBody = apiResponse;

            return ResponseEntity.ok()
                .header(totalCountKey, totalCountValue)
                .header(pageNumberKey, pageNumberValue)
                .header(pageSizeKey, pageSizeValue)
                .body(responseBody);
        };


        ApiResponse apiResponse = phoneNumberDetailService.getPhoneNumberDetails(pageNumber, pageSize);
        String totalCountKey = "totalCount";
        String totalCountValue = String.valueOf(apiResponse.getRecordCount());
        String pageNumberKey = "pageNumber";
        String pageNumberValue = String.valueOf(pageNumber);
        String pageSizeKey = "pageSize";
        String pageSizeValue = String.valueOf(apiResponse.getSizeOfPage());
        ApiResponse responseBody = apiResponse;

        return ResponseEntity.ok()
            .header(totalCountKey, totalCountValue)
            .header(pageNumberKey, pageNumberValue)
            .header(pageSizeKey, pageSizeValue)
            .body(responseBody);
    }
    

    /* 
    @GetMapping
    public List<PhoneNumberDetail> getPhoneNumberDetails() {
        return phoneNumberDetailService.getPhoneNumberDetails();

    };

    */

    // Post End point adding and verifying Phone Number Details
    @PostMapping
    @ResponseBody
    public PhoneNumberDetail addAndVerifyPhoneNumberDetail(@Valid @RequestBody PhoneNumberDetail phoneNumberDetail) {
        return phoneNumberDetailService.addNewPhoneNumberDetailAndVerify(phoneNumberDetail);
    }


    // Put End point to update Phone Number Detail and re-verify it
    @PutMapping(path = "phone-number-details/{phoneNumberDetailId}")
    public void updatePhoneNumberDetail(
        @PathVariable("phoneNumberDetailId") Long phoneNumberDetailId,
        @RequestBody PhoneNumberDetail phoneNumberDetail
    ) {

        phoneNumberDetailService.updatePhoneNumberDetail(phoneNumberDetailId, phoneNumberDetail.getReferenceId(), 
        phoneNumberDetail.getNin(), phoneNumberDetail.getPhoneNumber());
    }

    
    
    /*
    * Delete End point to delete specified Phone Number detail 
    * Since TeleCom users are considered as a datastore not controlled
    * by NIRA but instead by the Telecoms, the Deleted Phone Number Detail does not delete associated TeleCom User
    */

    @DeleteMapping(path = "phone-number-details/{phoneNumberDetailId}")
    public void deletePhoneNumberDetail(
        @PathVariable("phoneNumberDetailId") Long phoneNumberDetailId
    ) {
        phoneNumberDetailService.deletePhoneNumberDetail(phoneNumberDetailId);
    }
}
