package com.kinglian.screeninquiry.model.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * med_ov_medical_record
 * @author
 */
@Data
public class MedOvMedicalRecord implements Serializable {
    /**
     * 记录id
     */
    @TableId(value = "recordid",type = IdType.UUID)
    private String recordid;

    /**
     * 问诊主表id
     */
    private String visitid;

    private Integer clinicid;

    /**
     * 病人id
     */
    private String patientid;

    /**
     * 主诉
     */
    private String chiefComplaint;

    /**
     * 既往史
     */
    private String medicalHistory;

    /**
     * 简要病史
     */
    private String presentIllness;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    /**
     * 问诊记录表
     */
    private Date updatedDate;

    /**
     * 药物过敏史（0：无，1：青霉素，3：链霉素，4：磺胺，其他则直接输出文本）
     */
    private String drugAllergy;

    /**
     * 特殊人群（0：否，1：孕妇，2：产妇，3：哺乳期妇女）
     */
    private int specialCrowd;

    /**
     * 高血压（0：否，1：一级，2：二级，3：三级）
     */
    private int highBloodPressure;

    /**
     * 糖尿病（0：否，1：1型，2：2型，3：妊娠，4：其他，5：继发性）
     */
    private int diabetes;

    /**
     * 心脏病（0：否，1：有）
     */
    private int cardiopathy;

    /**
     * 其他特殊病（不填则无，填则有）
     */
    private String otherIllness;

    /**
     * 用药信息
     */
    private String pharmacyInfo;

    /**
     * 上次是否有不良反应（0：无，1：有）
     */
    private int untowardEffect;

    /**
     * 是否有不适反应（否为null，有则输出）
     */
    private String uncomfortableSymptom;

    /**
     * 初步诊断
     */
    private String diagnosis;

    /**
     * 是否确认该疾病
     */
    private Integer definiteCase;

    private static final long serialVersionUID = 1L;
}
