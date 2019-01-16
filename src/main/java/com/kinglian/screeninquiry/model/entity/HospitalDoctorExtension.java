package com.kinglian.screeninquiry.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * hospital_doctor_extension
 * @author
 */
@Data
public class HospitalDoctorExtension implements Serializable {
    private String id;

    /**
     * 医生名称
     */
    private String doctorName;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 医生性别 M-男性 F-女性
     */
    private String gender;

    /**
     * 医生简介
     */
    private String desc;

    /**
     * 医生账号
     */
    private String userAccount;

    /**
     * 身份证号
     */
    private String sfzh;

    /**
     * 医生职称
     */
    private String title;

    /**
     * 医生图片路径UR
     */
    private String pictureUrl;

    /**
     * 科室名称
     */
    private String deptName;

    private String hospitalName;

    /**
     * 1:团队管理员0：团队成员
     */
    private Integer isManager;

    /**
     * 1：医院医生0：非医院医生
     */
    private Integer isAdmin;

    /**
     * 医生（医生团队是否在线）：0离线 1在线
     */
    private Integer online;

    /**
     * 用户类型（1、病人  2、系统超级管理员  3、普通管理员  4、医生  5、主任医生  6、护士  7、护士长 8、院长与副院长9、平台管理员10、医院管理员  11,专科医生，12全科医生，13，健康管理师）
     */
    private String userType;

    /**
     * 用户id(对应sys_user表的id)
     */
    private String userId;

    /**
     * 快速问诊（0未开通，1已开通）
     */
    private String kswz;

    /**
     * 图文问诊（0未开通，1已开通）
     */
    private String twwz;

    /**
     * 视频问诊（0未开通，1已开通）
     */
    private String spwz;

    /**
     * 视频会诊（0未开通，1已开通）
     */
    private String sphz;

    /**
     * 申请开处方（0未开通，1已开通）
     */
    private String sqkcf;

    /**
     * 是否是测试用户（1是，0否）
     */
    private String isTestUser;

    /**
     * 是否通过审核(0审核中,1已实名,2未通过,-1未提交审核)
     */
    private String isPassedGuest;

    /**
     * 是否有效 (0 正常, 1 删除)
     */
    private String delFlag;

    private static final long serialVersionUID = 1L;

}
