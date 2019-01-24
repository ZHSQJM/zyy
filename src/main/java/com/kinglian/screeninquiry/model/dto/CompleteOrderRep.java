package com.kinglian.screeninquiry.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author HXC
 * @date 2019-1-19
 */
@Data
public class CompleteOrderRep {
    /**
     * 问诊创建时间
     */
    private Date creatDay;

    /**
     * 患者年龄
     */
    private int age;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 患者姓名
     */
    private String patientName;

    /**
     * 患者id
     */
    private String patinetId;

    /**
     * 患者性别
     */
    private String sex;

    /**
     * 患者生日
     */
    private Date birthDay;

    /**
     * 人脸识别id
     */
    private String opId;
}
