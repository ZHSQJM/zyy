package com.kinglian.screeninquiry.model.dto;

import lombok.Data;

/**
 * @author HXC
 * @date 2019-1-19
 */
@Data
public class DrugInfoRep {
    /**
     * 药品id
     */
    private String id;

    /**
     * 药品名称
     */
    private String drugName;

    /**
     * 药品通用名
     */
    private String universalName;

    /**
     * 规格
     */
    private String norms;

    /**
     * 药品单位
     */
    private String unit;

    /**
     * 药品默认用法
     */
    private String useage;

    /**
     * 药品默认用量
     */
    private String dosage;

    /**
     * 药品价格，两位小数向上取整
     */
    private double price;

    /**
     * 生产厂商
     */
    private String manufacturer;

    /**
     * 二次精神药品标志：0否 1是
     */
    private int ecjsbFlag;
}
