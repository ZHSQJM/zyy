/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: Constant
 * Author:   weiyz
 * Date:     2019/1/22 9:49
 * Description: 微信公众号常量相关类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.utils;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈微信公众号常量相关类〉
 * @author weiyz
 * @create 2019/1/22
 * @since 1.0.0
 */
@Component
public class Constant {

    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static final String OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    /**
     * 模板消息请求路径
     */
    private static String TEMPLATEMESSAGE_URL="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    private static final String APP_ID = "wx08e2ea07eefdc16e";
//    @Value("${appSecret.password}")
    private static String APP_SECRET ="e0706dd6b229eb1d78b2af285adb3ec3";

    @Autowired
    private RestTemplate restTemplate;

    public String gainOpenId(String code){
        String url = OPENID_URL.replace("APPID", APP_ID).replace("SECRET", APP_SECRET).replace("CODE", code);
        ResponseEntity<String> body = restTemplate.getForEntity(url, String.class);
        Map<String, String> data = com.alibaba.fastjson.JSONObject.parseObject(body.getBody(), HashMap.class);
        return data.get("openid");
    }
    /**
     * 发送https请求
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {
                    new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            //conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jsonObject;
    }

    /**
     * 自定义发送模板信息
     * @param accessToken 公众号标识
     * @param data 发送的内容拼接成的
     * @return
     *
     */
    public static boolean SendTempletTest(String accessToken,String data){
//        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        if(StringUtil.isEmptyString(data)){
            System.out.println("模版参数为空");
            return false;
        }
        // 拼接请求地址
        TEMPLATEMESSAGE_URL = TEMPLATEMESSAGE_URL.replace("ACCESS_TOKEN", accessToken);
        try {
            //将这里修改为自己调用服务的方式
            JSONObject jsonObject = httpsRequest(TEMPLATEMESSAGE_URL, "POST",data);
            if(jsonObject!=null){
                if("0".equals(jsonObject.getString("errcode"))){
                    System.out.println("发送模板消息成功！");
                }else{
                    System.out.println(jsonObject.getString("errcode"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
