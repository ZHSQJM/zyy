package com.kinglian.screeninquiry.model.dto;
import lombok.Data;

import java.io.Serializable;

/**
 * @author dell
 */
@Data
public class RequestHeadParam implements Serializable {

    private   String token;

    private  String time_stamp;


    private  String version;


    private  String osType;

    private  String appByClinicId;


}
