package com.kinglian.screeninquiry.WeixinPay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @project: zblog
 * @author: zhs
 * @date: 2019/4/15 09:59
 * @package: com.kinglian.screeninquiry.model.entity
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class WxparaInfo {


    private Integer wid;

    /**类型  1 普通商户  2 服务商*/
    private Integer wtype;

    /**小程序名称*/
    private String sname;

    /**appid*/
    private String said;

    /**商户号*/
    private String smid;

    /**密钥*/
    private String srt;

    /**商品描述*/
    private String sbody;

    /**附加信息  可自定义*/
    private String sath;

    /**设备号*/
    private String sdvi;

    /**用户ip*/
    private String sip;

    /**回调地址*/
    private String snou;

    /**交易类型*/
    private String stdp;

    /**key  自定义*/
    private String skey;

    /**子appid*/
    private String ssaid;

    /**子商户号*/
    private String ssmid;

    /**所属商家*/
    private String seid;

    /**创建时间*/
    private Date createTime;


}
