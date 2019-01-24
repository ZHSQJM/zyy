package com.kinglian.screeninquiry.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * med_patient_info
 * @author
 */
@Data
public class MedPatientInfo implements Serializable {
    /**
     * 主键,就诊人Id,修改或刪除
     */
    private String id;

    /**
     * 用户id
     */
    private String portalId;

    /**
     * 其他人身份证号
     */
    private String idCard;

    /**
     * 其他人姓名
     */
    private String memberName;

    /**
     * 其他人性别(M男，F女)
     */
    private String sex;

    /**
     * 其他人手机号
     */
    private String mobile;

    /**
     * 就诊人Id,修改或刪除就診人信息時填
     */
    private String memberId;

    /**
     * 类型
     */
    private String type;

    /**
     * 出生日期
     */
    private Date birthday;

    private Date createdDate;

    private Date updatedDate;

    private Boolean deleted;

    private Integer newUser;

    private String opId;

    public String getOpId() {
        return opId;
    }

    public void setOpId(String opId) {
        this.opId = opId;
    }

    private static final long serialVersionUID = 1L;

}
