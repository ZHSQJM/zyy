package com.kinglian.screeninquiry.model.dto;

import com.kinglian.screeninquiry.utils.DateConvertUtils;
import lombok.Data;

import java.util.Date;

/**
 * @author HXC
 * @date 2019-1-17
 */
@Data
public class DoctorPendingOrderRep {

    /**
     * 患者姓名
     */
    private String patientName;

    /**
     * 患者id
     */
    private String patinetId;

    /**
     * 是否为新用户
     */
    private Integer newUser;

    /**
     * 人脸识别id
     */
    private String opId;

    /**
     * 患者性别
     */
    private String sex;

    /**
     * 患者出生日期
     */
    private Date birthDay;

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
     * 备注
     */
    private String remark;

    /**
     * 1:待接订单，2：审核未通过订单
     */
    private int type;

    /**
     * 格式化日期时间
     */
    private String frommatDay;

    public String getFrommatDay() {
        if (creatDay != null) {
            return DateConvertUtils.dateToStrLong(creatDay);
        } else {
            return "";
        }
    }
}
