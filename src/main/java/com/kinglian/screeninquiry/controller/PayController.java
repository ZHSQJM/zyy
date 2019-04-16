package com.kinglian.screeninquiry.controller;

import com.kinglian.screeninquiry.WeixinPay.WxparaInfo;
import com.kinglian.screeninquiry.WeixinPay.WxpayInfo;
import com.kinglian.screeninquiry.WeixinPay.WxpayUtil;
import com.kinglian.screeninquiry.config.WxpayConfig;
import com.kinglian.screeninquiry.model.Vo.PayWeiXinVo;
import com.kinglian.screeninquiry.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @project: zblog
 * @author: zhs
 * @date: 2019/4/15 10:03
 * @package: com.kinglian.screeninquiry.controller
 * @description:
 */
@Controller
@RequestMapping("api")
@Slf4j
public class PayController {

    /**
     * 统一下单  直接传参
     * @param orderid
     * @param openid
     * @param fee
     * @param wxparaInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/prepay", produces = "text/html;charset=UTF-8")
    public R<PayWeiXinVo> unifiedOrderByPara(String orderid, String openid, String fee, WxparaInfo wxparaInfo){
        PayWeiXinVo payWeiXinVo = new PayWeiXinVo();
        // 封装统一下单基本参数
        WxpayInfo wxinfo = new WxpayInfo();
        wxinfo.setAppid(wxparaInfo.getSaid());
        wxinfo.setMch_id(wxparaInfo.getSmid());

        wxinfo.setSub_appid(wxparaInfo.getSsaid());
        wxinfo.setSub_mch_id(wxparaInfo.getSsmid());

        wxinfo.setAttach(wxparaInfo.getSath());
        wxinfo.setBody(wxparaInfo.getSbody());

        wxinfo.setNonce_str(WxpayUtil.create_nonce_str());
        wxinfo.setNotify_url(wxparaInfo.getSnou());

        wxinfo.setOut_trade_no(orderid);
        //.multiply(new BigDecimal(100))
        wxinfo.setTotal_fee(fee);
        wxinfo.setTrade_type(wxparaInfo.getStdp());
        wxinfo.setSpbill_create_ip(wxparaInfo.getSip());
        wxinfo.setSub_openid(openid);
        wxinfo.setDevice_info(wxparaInfo.getSdvi());
        System.out.println("签名前参数："+wxinfo.toString());
        String sign = WxpayUtil.getSign(wxinfo,wxparaInfo.getSkey());
        wxinfo.setSign(sign);

        Map<String, String> map = WxpayUtil.httpsRequestToXML(WxpayConfig.unifiedOrderUrl,WxpayConfig.method,WxpayUtil.payInfoToXML(wxinfo));
        System.out.println("统一下单结果："+map.toString());
        String preid = map.get("prepay_id");
        System.out.println("预支付id结果："+preid);
        Map<String, Object> result = new HashMap<String, Object>();

        // 返回结果（请求支付参数）进行封装
        if(map.get("return_code") != null && map.get("return_code").equals("SUCCESS")) {
            long times = System.currentTimeMillis() / 1000;

            payWeiXinVo.setNonceStr(map.get("nonce_str")).setPackage1(map.get("prepay_id")).setTimeStamp(String.valueOf(times));

          /*  result.put("appId", map.get("appid"));
            result.put("nonceStr", map.get("nonce_str"));
            result.put("package", "prepay_id="+map.get("prepay_id"));
            result.put("timeStamp", String.valueOf(times));
            result.put("orderid",orderid);
            map.put("times", String.valueOf(times));*/
            String signs = WxpayUtil.getSingWxPayApp(map,wxparaInfo.getSkey());
            payWeiXinVo.setPaySign(signs);
        }else{
            System.out.println("统一下单失败！！！");
        }

        return new R<PayWeiXinVo>(payWeiXinVo);
    }

}
