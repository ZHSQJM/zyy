package com.kinglian.screeninquiry.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * hospital_user_extension
 * @author
 */
@Data
public class HospitalUserExtension implements Serializable {
    /**
     * 用户id
     */
    private String userid;

    /**
     * 身份证号码
     */
    private String idcard;

    /**
     * 地址
     */
    private String address;

    private Date createdDate;

    private Date updatedDate;

    private Boolean deleted;

    /**
     * 上次登录时间
     */
    private Date lastLoginDate;

    /**
     * 登录时间
     */
    private Integer loginCount;

    /**
     * 短信是否接收
     */
    private Boolean messageModel;

    /**
     * 联系电话
     */
    private String contactPhone;

    private String qrCodeUrl;

    private Integer reFerralCode;

    private static final long serialVersionUID = 1L;

}
