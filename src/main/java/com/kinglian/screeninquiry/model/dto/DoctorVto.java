package com.kinglian.screeninquiry.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lky
 * @date 2019-1-21
 */

@Data
public class DoctorVto implements Serializable {
    public String doctorId;
    public String doctorName;
    public String doctorStatus;
    public String rate;
    public String visitTime;
    public String doctorImgUrl;
    public List<DoctorDepartmentVto> listDept;


}
