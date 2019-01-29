package com.kinglian.screeninquiry.model.dto;

import com.kinglian.screeninquiry.utils.R;
import lombok.Data;

@Data
public class VisitResult {

    public  String visitId;
    public  String fee;
    public String doctorId;
    public String doctorName;
    public String visitType;
    public String orderStatus;
    public String url;
}
