package com.kinglian.screeninquiry.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * med_office_visit
 * @author
 */
@Data
public class MedOfficeVisit implements Serializable {
    /**
     * 问诊id
     */
    private String visitid;

    /**
     * 医院id
     */
    private Integer clinicid;

    /**
     * 病人id
     */
    private String patientid;

    private Date visitDate;

    private String createdBy;

    private String createdByName;

    /**
     * 用户id
     */
    private String portalid;

    private Date createdDate;

    /**
     * 接诊医生id
     */
    private String cdid;

    /**
     * 接诊医生名称
     */
    private String cdName;

    /**
     * 接诊状态，0：待接诊，1：已接诊
     */
    private String visitStatus;

    /**
     * 问诊类型1:免费问诊
     */
    private Integer visitType;

    /**
     * 病人名称
     */
    private String patientName;

    /**
     * 病人出生日期
     */
    private Date patientDob;

    /**
     * 病人地址
     */
    private String patientAddress;

    /**
     * 1: 男；2：女
     */
    private Integer patientSex;

    /**
     * 病人手机号码
     */
    private String patientPhone;

    /**
     * 科室id
     */
    private String regDepartmentId;

    /**
     * 科室名称
     */
    private String regDepartmentName;

    private String updatedBy;

    private String updatedByName;

    /**
     * 处方数量
     */
    private Integer preSheetQty;

    /**
     * 更新时间
     */
    private Date updatedDate;

    /**
     * 删除标识
     */
    private Boolean deleted;

    /**
     * 1:免费；2：收费
     */
    private Integer businessType;

    /**
     * 药品订单号
     */
    private String drugOrderNo;

    /**
     * 是否申请加时
     */
    private Integer isApplyTime;

    /**
     * 申请加时时间
     */
    private Long applyTimeValue;

    /**
     * 是否处方申请入口
     */
    private Boolean presVisit;

    /**
     * 是否健康问诊
     */
    private Boolean healthVisit;

    /**
     * 是否测试单据
     */
    private Boolean test;

    /**
     * 药店id
     */
    private String drugstoreid;

    /**
     * 就诊医院名称
     */
    private String drugstoreName;


    private  int orderState;

    private  int payMode;

    private BigDecimal payCost;


    private BigDecimal getCost;

    private static final long serialVersionUID = 1L;

    private String orderNo;
}
