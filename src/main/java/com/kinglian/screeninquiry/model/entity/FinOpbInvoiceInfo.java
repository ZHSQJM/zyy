package com.kinglian.screeninquiry.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * fin_opb_invoice_info
 * @author
 */
@Data
public class FinOpbInvoiceInfo implements Serializable {
    /**
     * 费用表主表id
     */
    private String invoiceid;

    /**
     * 医院id
     */
    private Integer clinicid;

    /**
     * 问诊id
     */
    private String visitid;

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 总价格
     */
    private BigDecimal totalCost;

    /**
     * 需要付款价格
     */
    private BigDecimal payCost;

    /**
     * 实际价格
     */
    private BigDecimal realCost;

    /**
     * 实收金额
     */
    private BigDecimal getMoney;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdDate;

    /**
     * 折扣金额
     */
    private BigDecimal discount;

    /**
     * 费用类型1:问诊：2：药品;3:健康咨询服务;4:设备;5:健康预约服务
     */
    private Integer businessType;

    /**
     * 订单状态 {1,"待付款"},   {2,"待发货"}, {3,"待收货"},  {4,"交易成功"},  {5,"交易关闭"}, {6,"服务中"},  {7,"服务完成"}, {8,"服务关闭"},{9,"退款中"}, {10,"退款失败"}, {11,"退款成功"},
     */
    private Integer orderStatus;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户id
     */
    private String portalid;

    /**
     * 付款状态
     */
    private Integer payStatus;

    /**
     * 是否退款
     */
    private Integer isRefund;

    /**
     * 理由
     */
    private String reason;

    /**
     * 退款理由
     */
    private String refundReason;

    /**
     * 药店id
     */
    private String drugstoreid;

    /**
     * 单号
     */
    private String drugstoreOrderNo;

    /**
     * 处方类型
     */
    private Integer prescriptionType;

    /**
     * 开药数量
     */
    private Integer drugCount;

    /**
     * 注意事项
     */
    private String useDrugAttention;

    /**
     * 付款时间
     */
    private Date payTime;

    private Integer drugstoreOrderStatus;

    /**
     * 条码
     */
    private String barCodeUrl;

    /**
     * 是否代付
     */
    private Integer payForAnother;

    /**
     * 处方号
     */
    private String presNo;

    /**
     * 通过聊天判断有效性
     */
    private Integer effcetive;

    /**
     * 三师问诊
     */
    private Integer isHealthVisit;

    /**
     * 1:代取药品服务(健管师取药)；2：到药店/药柜自提
     */
    private Integer isGetDrugWay;

    /**
     * 医保号
     */
    private String medInsuNo;

    private Boolean isTest;

    private static final long serialVersionUID = 1L;

}
