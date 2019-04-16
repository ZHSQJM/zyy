package com.kinglian.screeninquiry.constant;

/**
 * @project: zblog
 * @author: zhs
 * @date: 2019/4/16 09:06
 * @package: com.kinglian.screeninquiry.constant
 * @description:
 */
public class Constant {


    public static final String DOMAIN = "http://localhost:8088";

    public static final String APP_ID = "wxb26f6444f9aa9a4f";

    public static final String APP_SECRET = "99ce971b63e87a062540b6599f113ce1";

    public static final String APP_KEY = "Hxinjingling0951797833333333333h";

    //商户号
    public static final String MCH_ID = "1491684712";

    public static final String URL_UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    public static final String URL_NOTIFY = Constant.DOMAIN + "/wxpay/views/payInfo.jsp";

    public static final String TIME_FORMAT = "yyyyMMddHHmmss";

    public static final int TIME_EXPIRE = 2;  //单位是day

}
