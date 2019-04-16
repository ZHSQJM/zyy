package com.kinglian.screeninquiry.WeixinPay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @project: zblog
 * @author: zhs
 * @date: 2019/4/15 10:52
 * @package: com.kinglian.screeninquiry.model.entity
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class WxpayInfo {

    /**公众账号ID[微信分配的公众账号ID（企业号corpid即为此appId）]*/
    private String appid;

    /**商户号[微信支付分配的商户号] */
    private String mch_id;

    private String sub_appid;

    private String sub_mch_id;

    /**设备号[终端设备号(门店号或收银设备ID),注意：PC网页或公众号内支付请传"WEB"] */
    private String device_info;

    /**随机字符串*/
    private String nonce_str;

    /**签名*/
    private String sign;

    /**商品描述*/
    private String body;

    /**附加数据*/
    private String attach;

    /**商户订单号*/
    private String out_trade_no;

    /**总金额*/
    private String total_fee;

    /**终端IP*/
    private String spbill_create_ip;

    /**通知地址*/
    private String notify_url;

    /**交易类型*/
    private String trade_type;

    /**用户标识*/
    private String openid;

    /**附加数据*/
    private String sub_openid;

}
