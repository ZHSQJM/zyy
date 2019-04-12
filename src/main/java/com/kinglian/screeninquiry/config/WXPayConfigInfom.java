package com.kinglian.screeninquiry.config;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayDomainSimpleImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Component()
@ConfigurationProperties(prefix = "wechat")
@PropertySource(value = "classpath:/wechatpay.properties")
public class WXPayConfigInfom {

    private byte[] certData;
//    private static WXPayConfigInfo INSTANCE;
    private String appID ;
    private String mchId;
    private String key;
    private String notifyUrl;

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
