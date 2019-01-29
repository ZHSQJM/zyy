package com.kinglian.screeninquiry.model.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * @author lky
 * @date 2019-1-21
 */
@Data
public class DoctorDepartmentVto implements Serializable {
    public String doctorDeptId;
    public String doctorDeptName;
}
