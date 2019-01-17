package com.kinglian.screeninquiry.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * fin_opb_fee_detail
 * @author
 */
@Data
public class FinOpbFeeDetail implements Serializable {
    private String feeid;

    /**
     * 社区医院id
     */
    private Integer clinicid;

    /**
     * 处方id
     */
    private String presid;

    /**
     * 费用主表id
     */
    private String invoiceid;

    /**
     * 药品id
     */
    private String stuffid;

    /**
     * 药品代码
     */
    private String stuffcode;

    /**
     * 药品名称
     */
    private String stuffname;

    /**
     * 规格
     */
    private String specs;

    /**
     * 单价
     */
    private BigDecimal retailPrice;

    /**
     * 数量
     */
    private BigDecimal presQty;

    /**
     * 包装
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

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

    /**
     * 每次用量
     */
    private BigDecimal presDosage;

    /**
     * 每次用量单位
     */
    private String presDosageUnit;

    private Integer presDays;

    private Integer presGroup;

    private Boolean deleted;

    /**
     * 处方类型
     */
    private String sheetType;

    /**
     * 用法
     */
    private String presDosages;

    /**
     * 诊断
     */
    private String diagnose;

    private static final long serialVersionUID = 1L;

}
