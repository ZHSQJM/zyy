/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: MyX509TrustManager
 * Author:   weiyz
 * Date:     2019/1/22 11:37
 * Description: MyX509TrustManager
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.utils;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 〈MyX509TrustManager 〉
 * @author weiyz
 * @create 2019/1/22
 * @since 1.0.0
 */
public class MyX509TrustManager implements X509TrustManager {
    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException
    {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException
    {
    }

    public X509Certificate[] getAcceptedIssuers()
    {
        return null;
    }
}
