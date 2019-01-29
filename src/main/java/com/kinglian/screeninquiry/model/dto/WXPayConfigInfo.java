package com.kinglian.screeninquiry.model.dto;


import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayDomainSimpleImpl;;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Component()

public class WXPayConfigInfo {

    private byte[] certData;
    //    private static WXPayConfigInfo INSTANCE;
    private String appID = "wx08e2ea07eefdc16e";
    private String mchId = "1491684712";
    private String key = "Hxinjingling0951797833333333333h";
    private String notifyUrl = "http://yun.kinglian.cn/ehr.payment/payment/wxpay/appPayNotifyPage.aspx";


/*
  #wechat.appID=wxb26f6444f9aa9a4f
    wechat.appID=wx08e2ea07eefdc16e
# 微信支付分配的商户号
    wechat.mchId=1491684712
            # 商户平台设置的key 在签名时需要验签
    wechat.key=Hxinjingling0951797833333333333h
#微信异步回调地址
    wechat.notifyUrl=http://www.thsqws.com/ehr.payment/payment/wxpay/appPayNotifyPage.aspx
*/


    public byte[] getCertData() {
        return certData;
    }

    public void setCertData(byte[] certData) {
        this.certData = certData;
    }

//    public static WXPayConfigInfo getINSTANCE() {
//        return INSTANCE;
//    }
//
//    public static void setINSTANCE(WXPayConfigInfo INSTANCE) {
//        WXPayConfigInfo.INSTANCE = INSTANCE;
//    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }


//    public static WXPayConfigInfo getInstance() throws Exception{
//        if (INSTANCE == null) {
//            synchronized (WXPayConfigInfo.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new WXPayConfigInfo();
//                }
//            }
//        }
//        return INSTANCE;
//    }
//
//    public String getAppID() {
//        return "wxb26f6444f9aa9a4f";
//    }
//
//    public String getMchID() {
//        return "1491684712";
//    }
//
//    public String getKey() {
//        return "Hxinjingling0951797833333333333h";
//    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }


    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    IWXPayDomain getWXPayDomain() {
        return WXPayDomainSimpleImpl.instance();
    }

    public String getPrimaryDomain() {
        return "api.mch.weixin.qq.com";
    }

    public String getAlternateDomain() {
        return "api2.mch.weixin.qq.com";
    }

    public int getReportWorkerNum() {
        return 1;
    }

    public int getReportBatchSize() {
        return 2;
    }
}