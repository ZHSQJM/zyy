package com.kinglian.screeninquiry.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author HXC
 * @date 2019-1-22
 */
@Data
public class HistoryOrderRep {

    /**
     * 接诊时间
     */
    private Date visitiDate;

    /**
     * 接诊号
     */
    private String visitiId;

    /**
     * 订单状态
     */
    private String visitiStauts;

    /**
     * 用户类型
     */
    private int patientType;

    /**
     * 诊金
     */
    private double totalCost;

    /**
     * 就诊人id
     */
    private String patientId;

    /**
     * 就诊人
     */
    private String patientName;

    /**
     * 就诊药店id
     */
    private String drugStoreId;

    /**
     * 就诊药店
     */
    private String drugstoreName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 生日
     */
    private Date birthDay;

    /**
     * 年龄
     */
    private int age;

    /**
     * 初诊
     */
    private String diagnosis;
}
