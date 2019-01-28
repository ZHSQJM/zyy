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
import com.kinglian.screeninquiry.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈微信消息推送前端控制器〉
 * @author weiyz
 * @create 2019/1/25
 * @since 1.0.0
 */
@RestController
@RequestMapping("message/template")
public class WeChatController {

    //#模板ID: Template
    @Value("Template")
    private String Template;
    //#跳转页面路径:HaveALook
    @Value("HaveALook")
    private String HaveALook;

    @Autowired
    MedOfficeVisitService medOfficeVisitService;

    @Autowired
   MedOvPrescriptionService medOvPrescriptionService;

    @PostMapping("send")
    public Boolean PushNotification(@RequestParam String code){
        //获取openId
        String openId = Constant.gainOpenId(code);
        //获取accessToken
        String accessToken = Constant.getAccessToken(code);
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("openid",openId);
        Page record = medOfficeVisitService.getMedicalRecordByOpenId(new Query<>(queryMap));
        String visitid = (String) record.getCondition().get("visitid");

        //获取“初步诊断”设置模板消息
        Map<String, String> Map = medOfficeVisitService.SetWeChatTemplateInformation(visitid);
        String diagnosis = Map.get("diagnosis");
        //获取“用药信息”设置模板消息
        List<MedOvPrescription> medOvPrescriptions = medOvPrescriptionService.findByVisitId(visitid);
        StringBuffer stringBuffer = new StringBuffer();
        for (MedOvPrescription med : medOvPrescriptions){
            String stuffName = med.getStuffName();
            stringBuffer.append(stringBuffer).append(",").deleteCharAt(stringBuffer.length()-1);
        }
        //创建消息发送实体对象
        TemplateMessage templateMessage = new TemplateMessage();
        templateMessage.setTouser(openId);
        templateMessage.setTemplate_id(Template);
        StringBuffer HaveALookStringBuffer = new StringBuffer(HaveALook);
        templateMessage.setUrl(HaveALookStringBuffer.append(visitid).toString());
        //设置模板标题
        WeChatMessageTemplate first = new WeChatMessageTemplate("您有一条新的处方信息","#FF0000");
        //设置初步诊断:
        WeChatMessageTemplate keyword1 = new WeChatMessageTemplate(diagnosis,"#FF0000");
        //设置用药:
        WeChatMessageTemplate keyword2 = new WeChatMessageTemplate(stringBuffer.toString(),"#FF0000");
        //设置时间:
        WeChatMessageTemplate keyword3 = new WeChatMessageTemplate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"#FF0000");
        //设置跳转内容
        WeChatMessageTemplate remark = new WeChatMessageTemplate("点击查看详情","#FF0000");
        //创建模板信息数据对象
        RegisterData data = new RegisterData();
        data.setFirst(first);
        data.setKeyword1(keyword1);
        data.setKeyword1(keyword2);
        data.setKeyword1(keyword3);
        data.setRemark(remark);
       templateMessage.setData(data);
        //将封装的数据转成JSON
        String jsonString = JSON.toJSONString(templateMessage);
        boolean bool = Constant.SendTempletTest(accessToken, jsonString);
        return bool;
    }

}
