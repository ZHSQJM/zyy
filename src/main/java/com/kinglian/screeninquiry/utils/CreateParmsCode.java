package com.kinglian.screeninquiry.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.application.Application;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HXC
 * @date 2019-1-30
 */
@Component
public class CreateParmsCode {

    private static final String CODE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=";

    private static final String OPEN_ID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code&appid=";

    private static final String APP_ID = "wx08e2ea07eefdc16e";
    private static final String APP_SECRET = "e0706dd6b229eb1d78b2af285adb3ec3";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取带参数的临时二维码
     * @param visitId
     * @return
     */
    public String getParmsCode(String visitId) {
        String accessToken = KeyManager.getValue("getToken");
        if (accessToken == null) {
            String newToken = getAccessToken();
            KeyManager.putValue("getToken",newToken);
            accessToken = newToken;
        }
//        String accessToken = getAccessToken();
        String url = CODE_URL+accessToken;
        Map<String, Object> map = new HashMap<>();
        map.put("expire_seconds", "3600");
        map.put("action_name", "QR_STR_SCENE");
        Map<String, Object> scene = new HashMap<>();
        scene.put("scene_str", visitId);
        Map<String, Object> actionInfo = new HashMap<>();
        actionInfo.put("scene", scene);
        map.put("action_info", actionInfo);
        String parms = JSON.toJSONString(map);
        System.out.println(parms);
        String body = restTemplate.postForObject(url, parms, String.class);
        Map<String, String> data = JSONObject.parseObject(body, HashMap.class);
        String codeUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + data.get("ticket");
        return codeUrl;
    }

    public  String getAccessToken() {
        StringBuffer sb = new StringBuffer(ACCESS_TOKEN_URL);
        sb.append(APP_ID);
        sb.append("&secret=");
        sb.append(APP_SECRET);
        ResponseEntity<String> body = restTemplate.getForEntity(sb.toString(), String.class);
        Map<String, String> data = JSONObject.parseObject(body.getBody(), HashMap.class);
        return data.get("access_token");
    }

    public  String getOpenId(String code) {
        StringBuffer sb = new StringBuffer(OPEN_ID_URL);
        sb.append(APP_ID);
        sb.append("&secret=");
        sb.append(APP_SECRET);
        sb.append("&code=");
        sb.append(code);
        System.out.println(sb.toString());
        ResponseEntity<String> body = restTemplate.getForEntity(sb.toString(), String.class);
        System.out.println(body);
        Map<String, String> data = JSONObject.parseObject(body.getBody(), HashMap.class);
        String openid = data.get("openid");
        return openid;
    }


    /**
     * 获取全局Session
     *
     * @return HttpSession
     */
    @SuppressWarnings("unchecked")
    public static ServletContext getApplication() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            return request.getSession().getServletContext();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }



}
