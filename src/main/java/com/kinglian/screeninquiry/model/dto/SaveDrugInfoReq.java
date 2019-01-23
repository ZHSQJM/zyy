package com.kinglian.screeninquiry.model.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author HXC
 * @date 2019-1-22
 */
@Data
public class SaveDrugInfoReq {

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 医生帐号
     */
    private String  doctorId;

    /**
     * 注意事项
     */
    private String  attention;

    /**
     * 医嘱
     */
    private String advice;

    /**
     * 增加或者修改（0：增加，1：修改）
     */
    private int isSave;

    /**
     * 药品list
     */
    private List drugList;
}
