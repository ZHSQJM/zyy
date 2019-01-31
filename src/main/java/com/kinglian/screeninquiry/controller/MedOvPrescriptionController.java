/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: MedOvPrescriptionController
 * Author:   weiyz
 * Date:     2019/1/17 14:25
 * Description: MedOvPrescription控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.controller;

import cn.kinglian.spring.util.Query;
import cn.kinglian.spring.util.R;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kinglian.screeninquiry.model.entity.DocEvaluation;
import com.kinglian.screeninquiry.model.entity.MedOfficeVisit;
import com.kinglian.screeninquiry.model.entity.MedOvPresSheet;
import com.kinglian.screeninquiry.service.*;
import com.kinglian.screeninquiry.utils.Constant;
import com.kinglian.screeninquiry.service.DocEvaluationService;
import com.kinglian.screeninquiry.service.MedOvPresSheetService;
import com.kinglian.screeninquiry.service.MedOvPrescriptionService;
import com.kinglian.screeninquiry.utils.CreateParmsCode;
import com.kinglian.screeninquiry.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.channels.SeekableByteChannel;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈MedOvPrescription控制器〉
 * @author weiyz
 * @create 2019/1/17
 * @since 1.0.0
 */
@CrossOrigin
@RestController
@RequestMapping("/screenInquiry/prescription")
public class MedOvPrescriptionController {

    @Autowired
    MedOvPrescriptionService medOvPrescriptionService;

    @Autowired
    MedOvPresSheetService medOvPresSheetService;

    @Autowired
    DocEvaluationService docEvaluationService;

    @Autowired
    MedOfficeVisitService medOfficeVisitService;

    @Autowired
    MedOvMedicalRecordService medOvMedicalRecordService;

    @Autowired
    MedPatientInfoService medPatientInfoService;

    @Autowired
    CreateParmsCode createParmsCode;

    @GetMapping("getPrescription")
    public R<Page> getPrescription(@RequestParam Map<String, Object> params){
        Page page = medOvPrescriptionService.getPreByPreId(new Query<Map>(params));
        return new R<>(page);
    }

    /**
     * 获取是否审核列表
     * @param params
     * @return
     */
    @GetMapping("getPresOrder")
    public R<Page> getPresOrder(@RequestParam Map<String, Object> params){
        return new R<>(medOvPrescriptionService.getPresOrder(new Query<Map>(params)));
    }

    /**
     * 审核处方
     * @param sheetid
     * @param auditStatus
     * @return
     */
    @GetMapping("review/{sheetid}/{auditStatus}")
    public R<Boolean> reviewPrescription(@PathVariable String sheetid,@PathVariable String auditStatus){
        MedOvPresSheet medOvPresSheet = new MedOvPresSheet();
        medOvPresSheet.setAuditStatus(Integer.parseInt(auditStatus));
        /**
         * 修改订单处方状态
         * by HXC
         */
        //订单审核通过时修改订单状态为已完成
        if ("1".equals(auditStatus)) {
            MedOfficeVisit medOfficeVisit = medOfficeVisitService.selectOne(new EntityWrapper<MedOfficeVisit>().eq("sheetid", sheetid));
            MedOfficeVisit updateEntity = new MedOfficeVisit();
            updateEntity.setUpdatedDate(new Date());
            updateEntity.setVisitStatus("3");
            medOfficeVisitService.update(updateEntity, new EntityWrapper<MedOfficeVisit>().eq("visitid", medOfficeVisit.getVisitid()));
        }
        //结束操作
//        medOvPresSheet.setSheetid(sheetid);
        return new R<>(medOvPresSheetService.update(medOvPresSheet,new EntityWrapper<MedOvPresSheet>().eq("sheetid",sheetid)));
    }

    /**
     * 对医生服务进行评价
     * @param docEvaluation
     * @return
     */
    @PostMapping("evaluate")
    public R<Boolean> evaluateDoctor(@RequestBody DocEvaluation docEvaluation){
        return new R<>(docEvaluationService.insert(docEvaluation));
    }

    /**
     * 获取病历
     * @param
     */
    @GetMapping("getMedicalRecord")
    public R<Page> getMedicalRecord(@RequestParam  String code) {/*HttpServletRequest request*/
        String openid = createParmsCode.getOpenId(code);
        String visitid = null;
//        String qrscene_visitid = null;
       /* Map<String, String> message = MessageUtil.parseXml(request);
        String event = message.get("Event");//事件
        String msgType = message.get("MsgType");//消息类型
        if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){
            if (event.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){//未关注公众号
                qrscene_visitid = message.get("EventKey");
                visitid = new StringBuffer(qrscene_visitid).substring(7);
            }else if (event.equals(MessageUtil.REQ_MESSAGE_TYPE_SCAN)){//已关注公众号
                visitid = message.get("EventKey");
            }
            }*/
        ServletContext application = CreateParmsCode.getApplication();
        Map map = (Map) application.getAttribute("map");
        visitid = (String) map.get("EventKey");
        MedOfficeVisit medOfficeVisit = medOfficeVisitService.getByVisitId(visitid);
        medPatientInfoService.updateById(medOfficeVisit.getPatientid());
//            visitid = message.get("visitid");
        Map<String, Object> params = new HashMap<>();
        params.put("openid",openid);
        return new R<>(medOfficeVisitService.getMedicalRecordByOpenId(new Query<Map>(params)));
    }

    /**
     * 病历详情
     * @param params
     * @return
     */
    @GetMapping("getMedicalRecordDetails")
    public R<Page> getMedicalRecordDetails(@RequestParam Map<String, Object> params){
        return new R<>(medOvMedicalRecordService.getMedicalRecordDetails(new Query<Map>(params)));
    }

    /**
     * 获取处方笺
     * @return
     */
    @GetMapping("ObtainPrescriptionPad")
    public R<Page> ObtainPrescriptionPad(@RequestParam Map<String, Object> params){
//        medOvPrescriptionService.findByVisitId((String) params.get("visitid"));
        return new R<>(medOvPrescriptionService.ObtainPrescriptionPad(new Query<Map>(params)));
    }

}
