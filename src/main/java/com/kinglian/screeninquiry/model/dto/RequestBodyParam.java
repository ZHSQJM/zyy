package com.kinglian.screeninquiry.model.dto;
import lombok.Data;

@Data
public class RequestBodyParam  {
    private  Integer pageNumber;
    private  String portalId;
    private  Integer clinicId;
    private  String  keyWord;
    private  String doctorId;

}

