/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: Prescription
 * Author:   weiyz
 * Date:     2019/1/17 14:34
 * Description: 处方笺
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.model.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 〈处方笺〉
 * @author weiyz
 * @create 2019/1/17
 * @since 1.0.0
 */
@Data
@ToString
public class Prescription {

    /**
     * 客户的名称
     */
    private String userName;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 地址
     */
    private String address;

    /**
     * 性别：M男 F女
     */
    private String sex;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 审核备注
     */
    private String auditNote;

    /**
     * 审核人
     */
    private String auditBy;

    /**
     * 审核医生姓名
     */
    private String auditDoctorName;

    /**
     * 药品id
     */
    private String stuffid;

    /**
     * 药品名称
     */
    private String stuffName;

    /**
     * 规格
     */
    private String specs;

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

    private String presUsageName;
}
