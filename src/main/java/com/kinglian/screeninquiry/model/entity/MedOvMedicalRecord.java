package com.kinglian.screeninquiry.model.entity;

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

    private static final long serialVersionUID = 1L;
}
