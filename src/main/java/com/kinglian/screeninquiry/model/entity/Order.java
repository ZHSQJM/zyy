package com.kinglian.screeninquiry.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * order
 * @author 
 */
@Data
public class Order implements Serializable {
    /**
     * 主键id
     */
    private String id;

    /**
     * 订单编号
     */
    private String orderNum;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片链接
     */
    private String picture;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 客户微信号
     */
    private String clientWechatId;

    /**
     * 订单状态（0：已完成，1：未完成）
     */
    private String state;

    /**
     * 收款状态（0：已收款，1：待收款）
     */
    private String gatherState;

    /**
     * 时长
     */
    private String showTime;

    /**
     * 产品id
     */
    private String productId;

    private String openId;

    private static final long serialVersionUID = 1L;
}