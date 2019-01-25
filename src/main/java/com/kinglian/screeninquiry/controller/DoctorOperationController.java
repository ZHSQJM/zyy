package com.kinglian.screeninquiry.controller;

import cn.kinglian.spring.config.LoginException;
import cn.kinglian.spring.util.Query;
import cn.kinglian.spring.util.R;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kinglian.screeninquiry.model.dto.DrugList;
import com.kinglian.screeninquiry.model.dto.SaveCase;
import com.kinglian.screeninquiry.model.dto.SaveDrugInfoReq;
import com.kinglian.screeninquiry.model.entity.MedOfficeVisit;
import com.kinglian.screeninquiry.model.entity.MedOvPrescription;
import com.kinglian.screeninquiry.service.*;
import com.kinglian.screeninquiry.utils.JsonEntity;
import com.kinglian.screeninquiry.utils.JsonEntityOfObject;
import com.xiaoleilu.hutool.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;


/**
 * @author HXC
 * @date 2019-1-16
 */
@RestController
@RequestMapping("/screenInquiry/doctor")
public class DoctorOperationController {

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorOperationService doctorOperationService;

    @Autowired
    private MedOfficeVisitService medOfficeVisitService;

    @Autowired
    private MedOvMedicalRecordService medOvMedicalRecordService;

    @Autowired
    private MedOvPrescriptionService medOvPrescriptionService;

    /**
     * 医生端登录接口
     * @return
     */
    @PostMapping("/login")
    public R<String> login(@RequestBody JsonEntity jsonEntity) throws Exception {
        if (!userService.login(jsonEntity.getBody().get("user"), jsonEntity.getBody().get("password"))) {
            throw new LoginException("帐号密码错误");
        } else {
            return new R<>(jsonEntity.getBody().get("user"));
        }
    }


    /**
     * 医生端待处理端口
     * @return
     */
    @RequestMapping("/pendingOrder")
    public R<List> pendingOrder(@RequestBody JsonEntity jsonEntity) {
        System.out.println(jsonEntity);
        return new R<>(doctorOperationService.pendingOrder(jsonEntity.getHeader().get("timestamp"), jsonEntity.getBody().get("doctorId")));
    }

    /**
     * 医生端接单操作
     * @return
     */
    @RequestMapping("/clinicalReception")
    public R<Boolean> clinicalReception(@RequestBody JsonEntity jsonEntity) {
        return new R<>(medOfficeVisitService.clinicalReception(jsonEntity.getBody().get("orderId")));
    }

    /**
     * 医生端已完成问诊接口
     * @return
     */
    @RequestMapping("/completeProfile")
    public R<List> completeProfile(@RequestBody JsonEntity jsonEntity) {
        return new R<>(doctorOperationService.completeProfile(jsonEntity.getBody().get("doctorId")));
    }

    /**
     * 医生端药品查询接口
     * @return
     */
    @RequestMapping("/searchKey")
    public R<List> searchDrugInfo(@RequestBody JsonEntity jsonEntity) {
        return new R<>(doctorOperationService.searchDrugInfo(jsonEntity.getBody().get("searchKey")));
    }

    /**
     * 医生端查询历史订单
     * @return
     */
    @RequestMapping("/historyOrder")
    public R<List> historyOrder(@RequestBody JsonEntity jsonEntity) throws ParseException {
        return new R<>(doctorOperationService.historyOrder(jsonEntity));
    }

    /**
     * 医生端病例填写接口
     * @return
     */
    @RequestMapping("/saveCaseHistory")
    public R<Boolean> saveCaseHistory(@RequestBody JsonEntity jsonEntity) {
        SaveCase saveCase = BeanUtil.mapToBean(jsonEntity.getBody(), SaveCase.class, false);
        return new R<>(medOvMedicalRecordService.saveCaseHistory(saveCase));
    }

    /**
     * 医生端关闭问诊接口
     * @return
     */
    @RequestMapping("/closeInquiry")
    public R<Boolean> closeInquiry(@RequestBody JsonEntity jsonEntity) {
        MedOfficeVisit medOfficeVisit = new MedOfficeVisit();
        medOfficeVisit.setVisitid(jsonEntity.getBody().get("orderId"));
        medOfficeVisit.setVisitStatus("2");
        medOfficeVisit.setUpdatedBy(jsonEntity.getBody().get("doctorId"));
        medOfficeVisit.setUpdatedDate(new Date());
        return new R<>(medOfficeVisitService.update(medOfficeVisit,new EntityWrapper<MedOfficeVisit>().eq("visitid",jsonEntity.getBody().get("orderId"))));
    }

    /**
     * 病历详情
     * @return
     */
    @RequestMapping("/getMedicalRecordDetails")
    public R<Page> getMedicalRecordDetails(@RequestBody JsonEntity jsonEntity){
        Map params = new HashMap();
        params.put("visitid",jsonEntity.getBody().get("visitid"));
        List<MedOvPrescription> medList = medOvPrescriptionService.findByVisitId((String) params.get("visitid"));
        return new R<>(medOvMedicalRecordService.getMedicalRecordDetails(new Query<Map>(params)));
    }

    /**
     * 医生端处方保存接口
     * @param jsonEntity
     * @return
     */
    @RequestMapping("/saveDrugInfo")
    public R<Boolean> saveDrugInfo(@RequestBody JsonEntityOfObject jsonEntity) throws IOException {
        SaveDrugInfoReq saveDrugInfoReq = BeanUtil.mapToBean(jsonEntity.getBody(), SaveDrugInfoReq.class, false);
        List<MedOvPrescription> medOvPrescriptionList = new ArrayList<>();
        BigDecimal totalCost = new BigDecimal(0);
        List<BigDecimal> totalPrice = new ArrayList();
        saveDrugInfoReq.getDrugList().stream().forEach(x->{
            DrugList drugList = BeanUtil.mapToBean((Map<?, ?>) x, DrugList.class, false);
            System.out.println(drugList);
            MedOvPrescription medOvPrescription = new MedOvPrescription();
            medOvPrescription.setVisitid(saveDrugInfoReq.getOrderId());
            medOvPrescription.setStuffid(drugList.getDrugId());
            medOvPrescription.setStuffName(drugList.getDrugName());
            medOvPrescription.setDosage(drugList.getDosage());
            medOvPrescription.setPresFreq(drugList.getFreq());
            medOvPrescription.setPresUsage(drugList.getUseage());
            medOvPrescription.setSpecs(drugList.getNorms());
            medOvPrescription.setPresQty(drugList.getCount());
            medOvPrescription.setRetailPrice(drugList.getPrice());
            medOvPrescriptionList.add(medOvPrescription);
            totalPrice.add(drugList.getPrice().multiply(new BigDecimal(drugList.getCount())));
        });
        for (int i = 0; i < totalPrice.size(); i++) {
            totalCost = totalPrice.get(i).add(totalCost);
        }
        //判断删除时先删除处方
        if (saveDrugInfoReq.getIsSave() == 1) {
            medOvPrescriptionService.delete(new EntityWrapper<MedOvPrescription>().eq("visitid", saveDrugInfoReq.getOrderId()));
        }
        if (doctorOperationService.saveDrugInfo(saveDrugInfoReq, totalCost) &&
                medOvPrescriptionService.insertBatch(medOvPrescriptionList,medOvPrescriptionList.size())) {
            return new R<>(true);
        }
        return new R<>(false);
    }
}
