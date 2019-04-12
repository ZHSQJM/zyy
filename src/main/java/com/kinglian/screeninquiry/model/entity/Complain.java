package com.kinglian.screeninquiry.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * complain
 * @author 
 */
@Data
public class Complain implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 投诉内容
     */
    private String content;

    /**
     * 截图
     */
    private String printscreen;

    /**
     * 客户id
     */
    private String clientWechatId;

    /**
     * 投诉人
     */
    private String complainant;

    /**
     * 订单id
     */
    private String orderId;

    private static final long serialVersionUID = 1L;

}