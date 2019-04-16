package com.kinglian.screeninquiry.WeixinPay;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @project: zblog
 * @author: zhs
 * @date: 2019/4/15 10:56
 * @package: com.kinglian.screeninquiry.utils
 * @description:
 */
public class WxpayUtil {


    // 统一下单签名算法
    public static String getSign(WxpayInfo payInfo,String skey) {
        String sign = "";
        String signTemp = "appid=" + payInfo.getAppid()
                + "&attach=" + payInfo.getAttach()
                + "&body=" + payInfo.getBody()
                + "&device_info=" + payInfo.getDevice_info()
                + "&mch_id=" + payInfo.getMch_id()
                + "&nonce_str=" + payInfo.getNonce_str()
                + "&notify_url=" + payInfo.getNotify_url()
                + "&openid=" + payInfo.getOpenid()
                + "&out_trade_no=" + payInfo.getOut_trade_no()
                + "&spbill_create_ip=" + payInfo.getSpbill_create_ip()
                + "&sub_appid=" + payInfo.getSub_appid()
                + "&sub_mch_id=" + payInfo.getSub_mch_id()
                + "&sub_openid=" + payInfo.getSub_openid()
                + "&total_fee=" + payInfo.getTotal_fee()
                + "&trade_type=" + payInfo.getTrade_type()
                + "&key=" + skey; // 这个key注意
        try {
            System.out.println("签名拼接："+signTemp);
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(signTemp.getBytes("UTF-8"));
            sign = byteToStr(md.digest()).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("sign:" + sign);
        return sign;
    }

    // 查询订单状态 签名
    public static String getSignOrderQuery(WxpayInfo payInfo,String skey) {
        String sign = "";
        String signTemp = "appid=" + payInfo.getAppid()
                + "&mch_id=" + payInfo.getMch_id()
                + "&nonce_str=" + payInfo.getNonce_str()
                + "&out_trade_no=" + payInfo.getOut_trade_no()
                + "&sub_appid=" + payInfo.getSub_appid()
                + "&sub_mch_id=" + payInfo.getSub_mch_id()
                + "&key=" + skey; // 这个key注意
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(signTemp.getBytes("UTF-8"));
            sign = byteToStr(md.digest()).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("orderQuerySign:" + sign);
        return sign;
    }

    // xml转换
    private static XStream xstream = new XStream(new XppDriver(){
        @Override
        public HierarchicalStreamWriter createWriter(Writer out){
            return new PrettyPrintWriter(out){
                boolean cdata = true;
                @Override
                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                @Override
                protected void writeText(QuickWriter writer, String text){
                    if(cdata){
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }else{
                        writer.write(text);
                    }
                }
            };
        }
    });

    public static String payInfoToXML(WxpayInfo pi){
        xstream.alias("xml", pi.getClass());
        String res =  xstream.toXML(pi).replace("__", "_")
                .replace("<![CDATA[", "").replace("]]>", "");
        System.out.println("下单参数转为xml："+res);
        return res;
    }



    // 唯一字符生成
    public static String create_nonce_str() {
        return UUID.randomUUID().toString().replace("-","");
    }

    public static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i =0 ; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }
    private static String byteToHexStr(byte bytes) {
        char[] Digit = { '0','1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(bytes >>> 4) & 0X0F];
        tempArr[1] = Digit[bytes & 0X0F];
        String s = new String(tempArr);
        return s;
    }


    // http请求
    private static StringBuffer httpsRequest(String requestUrl, String requestMethod, String output)   throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException, MalformedURLException, IOException, ProtocolException, UnsupportedEncodingException {
        URL url = new URL(requestUrl);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod(requestMethod);
        if (null != output) {
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(output.getBytes("UTF-8"));
            outputStream.close();
        }   // 从输入流读取返回内容
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        StringBuffer buffer = new StringBuffer();
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        inputStream = null;
        connection.disconnect();
        return buffer;
    }

    public static Map<String, String> httpsRequestToXML(String requestUrl, String requestMethod, String outputStr) {
        Map<String, String> result = new HashMap<String, String>();
        try {
            StringBuffer buffer = httpsRequest(requestUrl, requestMethod, outputStr);
            result = parseXml(buffer.toString());
        } catch (ConnectException ce) {
//			log.error("连接超时："+ce.getMessage());
        } catch (Exception e) {
//			log.error("https请求异常："+e.getMessage());
            e.printStackTrace();
        }   return result;
    }

    // xml转换成map
    public static Map<String, String> parseXml(String xml) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        return map;
    }



    //微信支付生成签名(公众号)
    public static String getSingWxPayApp(Map<String, String> map,String skey){
        String sign = null;

        String rsult = "appId=" + map.get("sub_appid")
                + "&nonceStr=" + map.get("nonce_str")
                + "&package=prepay_id="+map.get("prepay_id")
                + "&signType=MD5"
                + "&timeStamp=" + map.get("times")
                + "&key=" + skey;
        System.out.println("发起支付签名："+rsult);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(rsult.getBytes("UTF-8"));
            sign = byteToStr(md.digest()).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("支付签名："+sign);
        return sign;
    }

}
