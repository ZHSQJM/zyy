package com.kinglian.screeninquiry.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * product
 * @author 
 */
@Data
public class Product implements Serializable {
    /**
     * 主键id
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品简介
     */
    private String intro;

    /**
     * 商家id
     */
    private String vendorId;

    private static final long serialVersionUID = 1L;

}