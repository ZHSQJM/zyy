package com.kinglian.screeninquiry.model.dto;

import lombok.Data;

@Data
public class SubmitVisitBodyParam  extends RequestBodyParam{
public  String orderId;
public  String doctorDeptId;
public  String visitId;

}
