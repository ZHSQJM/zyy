package com.kinglian.screeninquiry.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * vendor
 * @author 
 */
@Data
public class Vendor implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 商家名称
     */
    private String vendorName;

    /**
     * 商家介绍
     */
    private String vendorIntro;

    /**
     * 商家图片
     */
    private String picture;
    private String openId;

    private static final long serialVersionUID = 1L;

}