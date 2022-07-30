package com.laboremuscase.sdcase.NIRA;


import org.springframework.data.domain.Page;

public class ApiResponse {
    
    private int sizeOfPage;
    private int recordCount;
    private Page<PhoneNumberDetail> records;

    public ApiResponse(){};

    public ApiResponse( int sizeOfPage, int recordCount, Page<PhoneNumberDetail> records){
        this.sizeOfPage = sizeOfPage;
        this.recordCount = recordCount;
        this.records = records;
    };

    //Getters & Setters

    public int getSizeOfPage() {
        return sizeOfPage;
    }

    public void setSizeOfPage(int sizeOfPage) {
        this.sizeOfPage = sizeOfPage;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public Page<PhoneNumberDetail> getRecords() {
        return records;
    }

    public void setRecords( Page<PhoneNumberDetail> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "{" +
                " sizeOfPage=" + sizeOfPage +
                " recordCount=" + recordCount +
                ", records=" + records +
                "}";
    }

}
