package com.kinglian.screeninquiry.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * med_ov_prescription
 * @author
 */
@Data
public class MedOvPrescription implements Serializable {
    /**
     * 明细id
     */
    private String presid;

    /**
     * 医院id
     */
    private Integer clinicid;

    /**
     * 病人id
     */
    private String patientid;

    /**
     * 问诊id
     */
    private String visitid;

    /**
     * 药品id
     */
    private String stuffid;

    /**
     * 药品名称
     */
    private String stuffName;

    /**
     * 药品代码
     */
    private String stuffCode;

    /**
     * 处方类型
     */
    private String sheetType;

    private String isInjection;

    /**
     * 规格
     */
    private String specs;

    /**
     * 价格
     */
    private BigDecimal retailPrice;

    /**
     * 用量
     */
    private BigDecimal presDosage;

    /**
     * 单位
     */
    private String presDosageUnit;

    /**
     * 数量
     */
    private Integer presQty;

    /**
     * 包装规格单位
     */
    private String presPackageUnit;

    /**
     * 用法
     */
    private String presUsage;

    /**
     * 用法名称
     */
    private String presUsageName;

    /**
     * 频次
     */
    private String presFreq;

    /**
     * 频次名称
     */
    private String presFreqName;

    /**
     * 频次组
     */
    private Integer presGroup;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

    /**
     * 数量
     */
    private Integer sheetNum;

    /**
     * 处方主表id
     */
    private String preSheetId;

    private static final long serialVersionUID = 1L;
}
