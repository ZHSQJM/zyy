package com.kinglian.screeninquiry.config;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayDomainSimpleImpl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author
 * @desc
 **/
public class WxConfig extends WXPayConfig {
    @Override
    public int getReportWorkerNum() {
        return super.getReportWorkerNum();
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 3000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 3000;
    }

    @Override
    public int getReportBatchSize() {
        return super.getReportBatchSize();
    }

    @Override
    public int getReportQueueMaxSize() {
        return super.getReportQueueMaxSize();
    }

    @Override
    public String getKey() {
        return "Hxinjingling0951797833333333333h";
    }

    @Override
    public String getAppID() {
        return "wxb26f6444f9aa9a4f";
    }

    @Override
    public String getMchID() {
        return "1491684712";
    }

    @Override
    public InputStream getCertStream() {
        return new ByteArrayInputStream(null);
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
//        return new IWXPayDomain() {
//            @Override
//            public void report(String domain, long elapsedTimeMillis, Exception ex) {
//
//            }
//
//            @Override
//            public DomainInfo getDomain(WXPayConfig config) {
//                return new DomainInfo("api.mch.weixin.qq.com", true);
//            }
//        };
        return WXPayDomainSimpleImpl.instance();
    }
}
