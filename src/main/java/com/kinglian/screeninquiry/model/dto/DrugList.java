package com.kinglian.screeninquiry.model.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author HXC
 * @date 2019-1-22
 */
@Data
public class DrugList {

    /**
     * 药品名称
     */
    private String drugName;

    /**
     * 药品id
     */
    private String drugId;

    /**
     * 每次用量
     */
    private String dosage;

    /**
     * 频次
     */
    private String freq;

    /**
     * 用法
     */
    private String useage;

    /**
     * 规格
     */
    private String norms;

    /**
     * 数量
     */
    private int count;

    /**
     * 价格
     */
    private BigDecimal price;
}
