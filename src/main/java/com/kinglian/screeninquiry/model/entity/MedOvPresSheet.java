package com.kinglian.screeninquiry.model.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * med_ov_pres_sheet
 * @author
 */
@Data
@ToString
public class MedOvPresSheet implements Serializable {
    /**
     * 处方id
     */
    @TableId(value = "sheetid",type = IdType.UUID)
    private String sheetid;

    /**
     * 问诊id
     */
    private String visitid;

    /**
     * 处方数量
     */
    private Integer sheetNum;

    /**
     * 医院id
     */
    private Integer clinicid;

    private String createdBy;

    private Date createdDate;

    /**
     * 打印人
     */
    private String printedBy;

    /**
     * 打印时间
     */
    private Date printedDate;

    private String updatedBy;

    private Date updatedDate;

    /**
     * 处方类型；drug;西药
     */
    private String sheetType;

    /**
     * 删除标志
     */
    private Boolean deleted;

    /**
     * 总价格
     */
    private BigDecimal totalPrice;

    /**
     * 处方审核状态 0:未审核；1：审核通过；-1审核未通过
     */
    private Integer auditStatus;

    /**
     * 审核备注
     */
    private String auditNote;

    /**
     * 审核人
     */
    private String auditBy;

    /**
     * 审核时间
     */
    private Date auditDate;

    /**
     * 审核医生账号
     */
    private String auditDoctorAccount;

    /**
     * 审核医生姓名
     */
    private String auditDoctorName;

    /**
     * 审核医生签名路径
     */
    private String auditDoctorSignUrl;

    /**
     * 处方号
     */
    private String presNo;

    /**
     * 医网签处方签名
     */
    private String drugSignUrl;

    /**
     * 医网签处方id
     */
    private String uniqueid;

    /**
     * 云药店医网签id
     */
    private String yydUniqueid;

    /**
     * 待签状态：1；已签：2
     */
    private Integer signStatus;

    /**
     * 签了时间
     */
    private Date signUpDate;

    /**
     * 医网签认证状态
     */
    private Integer signAuthStatus;

    private Boolean isTest;

    //private String test;

    /**
     * 注意事项
     */
    private String attention;

    /**
     * 医嘱
     */
    private String advice;

    private static final long serialVersionUID = 1L;
}
