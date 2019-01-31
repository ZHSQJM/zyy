/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: WeChatController
 * Author:   weiyz
 * Date:     2019/1/25 11:28
 * Description: 微信消息推送前端控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.controller;

import cn.kinglian.spring.util.Query;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.kinglian.screeninquiry.dao.MedOfficeVisitMapper;
import com.kinglian.screeninquiry.model.entity.*;
import com.kinglian.screeninquiry.service.MedOfficeVisitService;
import com.kinglian.screeninquiry.service.MedOvPrescriptionService;
import com.kinglian.screeninquiry.utils.CheckSignature;
import com.kinglian.screeninquiry.service.WechatService;
import com.kinglian.screeninquiry.utils.Constant;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 〈微信消息推送前端控制器〉
 * @author weiyz
 * @create 2019/1/25
 * @since 1.0.0
 */
@RestController
@RequestMapping("/message/template")
public class WeChatController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatController.class);

   /* @Value("${DNBX_TOKEN}")
    private String DNBX_TOKEN;

    @Resource
    WechatService wechatService;*/

    /**
     * 微信接入
     * @param
     * @return
     * @throws IOException
     */
   /* @RequestMapping(value="/connect",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void connectWeixin(HttpServletRequest request, HttpServletResponse response) throws IOException {*/
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
//        request.setCharacterEncoding("UTF-8");  //微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
//        response.setCharacterEncoding("UTF-8"); //在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；
//        boolean isGet = request.getMethod().toLowerCase().equals("get");

//        PrintWriter out = response.getWriter();
//        if (isGet) {
//            String signature = request.getParameter("signature");// 微信加密签名
//            String timestamp = request.getParameter("timestamp");// 时间戳
//            String nonce = request.getParameter("nonce");// 随机数
//            String echostr = request.getParameter("echostr");//随机字符串

//            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  if (SignUtil.checkSignature(DNBX_TOKEN, signature, timestamp, nonce)) {
//            LOGGER.info("Connect the weixin server is successful.");
//            response.getWriter().write(echostr);
//        } /*else {
//                LOGGER.error("Failed to verify the signature!");
//            }*/ else {
//            String respMessage = "异常消息！";
//            try {
//                respMessage = wechatService.weixinPost(request);
//                out.write(respMessage);
//                LOGGER.info("The request completed successfully");
//                LOGGER.info("to weixin server " + respMessage);
//            } catch (Exception e) {
//                LOGGER.error("Failed to convert the message from weixin!");
//            }
//        }
//    }



//    //#模板ID: Template
//    @Value("Template")
//    private String Template;
//    //#跳转页面路径:HaveALook
//    @Value("HaveALook")
//    private String HaveALook;
//
//    @Autowired
//    MedOfficeVisitService medOfficeVisitService;
//
//    @Autowired
//   MedOvPrescriptionService medOvPrescriptionService;
//
//    @Autowired
//    Constant constant;
//
//    @PostMapping("send")
//    public Boolean PushNotification(@RequestParam String code){
//        //获取openId
//        String openId = constant.gainOpenId(code);
//        //获取accessToken
//        String accessToken = null;
////                constant.getAccessToken();
//        //创建消息发送实体对象
//        TemplateMessage templateMessage = new TemplateMessage();
//        Map<String, Object> queryMap = new HashMap<>();
//        queryMap.put("openid",openId);
//        Page record = medOfficeVisitService.getMedicalRecordByOpenId(new Query<>(queryMap));
//        String visitid = (String) record.getCondition().get("visitid");
//        Integer auditStatus = (Integer) record.getCondition().get("auditStatus");
//        String jsonString = null;
//        if (auditStatus == 0 || auditStatus == -1){
//            //"处方正在审核中，请稍等..."
//            jsonString = "处方正在审核中，请稍等...";
//        }
//
//        //获取“初步诊断”设置模板消息
//        Map<String, String> Map = medOfficeVisitService.SetWeChatTemplateInformation(visitid);
//        String diagnosis = Map.get("diagnosis");
//        //获取“用药信息”设置模板消息
//        List<MedOvPrescription> medOvPrescriptions = medOvPrescriptionService.findByVisitId(visitid);
//        StringBuffer stringBuffer = new StringBuffer();
//        for (MedOvPrescription med : medOvPrescriptions){
//            String stuffName = med.getStuffName();
//            stringBuffer.append(stringBuffer).append(",").deleteCharAt(stringBuffer.length()-1);
//        }
//
//        templateMessage.setTouser(openId);
//        templateMessage.setTemplate_id(Template);
//        StringBuffer HaveALookStringBuffer = new StringBuffer(HaveALook);
//        templateMessage.setUrl(HaveALookStringBuffer.append(visitid).toString());
//        //设置模板标题
//        WeChatMessageTemplate first = new WeChatMessageTemplate("您有一条新的处方信息","#FF0000");
//        //设置初步诊断:
//        WeChatMessageTemplate keyword1 = new WeChatMessageTemplate(diagnosis,"#FF0000");
//        //设置用药:
//        WeChatMessageTemplate keyword2 = new WeChatMessageTemplate(stringBuffer.toString(),"#FF0000");
//        //设置时间:
//        WeChatMessageTemplate keyword3 = new WeChatMessageTemplate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"#FF0000");
//        //设置跳转内容
//        WeChatMessageTemplate remark = new WeChatMessageTemplate("点击查看详情","#FF0000");
//        //创建模板信息数据对象
//        RegisterData data = new RegisterData();
//        data.setFirst(first);
//        data.setKeyword1(keyword1);
//        data.setKeyword1(keyword2);
//        data.setKeyword1(keyword3);
//        data.setRemark(remark);
//       templateMessage.setData(data);
//        //将封装的数据转成JSON
//        jsonString = JSON.toJSONString(templateMessage);
//        boolean bool = Constant.SendTempletTest(accessToken, jsonString);
//        return bool;
//    }

    @GetMapping(value = "/wx")
    public void get(HttpServletRequest request, HttpServletResponse response) throws Exception {


        System.out.println("========WechatController========= ");

        Enumeration pNames = request.getParameterNames();
        while (pNames.hasMoreElements()) {
            String name = (String) pNames.nextElement();
            String value = request.getParameter(name);
            // out.print(name + "=" + value);

        }

        String signature = request.getParameter("signature");/// 微信加密签名
        String timestamp = request.getParameter("timestamp");/// 时间戳
        String nonce = request.getParameter("nonce"); /// 随机数
        String echostr = request.getParameter("echostr"); // 随机字符串
        PrintWriter out = response.getWriter();

        if (CheckSignature.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    @PostMapping(value = "/wx")
    public void pos(HttpServletRequest request) throws IOException, DocumentException {
        Map<String,String> map = new HashMap<String,String>();
        SAXReader reader = new SAXReader();

        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);

        //获取根节点
        Element root = doc.getRootElement();

        List<Element> list = root.elements();

        for(Element e : list){
            map.put(e.getName(), e.getText());
        }
        ins.close();

        System.out.println(map);
    }




}
