package com.kinglian.screeninquiry.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WxPayDto {
    private String orderId;//订单号
    private BigDecimal totalFee;//金额
    private String spbillCreateIp;//订单生成的机器 IP
    private String notifyUrl;//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等
    private String body;// 商品描述根据情况修改
    private String openId;//微信用户对一个公众号唯一
//getter、setter等方法
}
