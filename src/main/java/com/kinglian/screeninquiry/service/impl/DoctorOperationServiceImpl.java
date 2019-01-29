package com.kinglian.screeninquiry.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.kinglian.screeninquiry.dao.DoctorOperationMapper;
import com.kinglian.screeninquiry.dao.MedOvPresSheetMapper;
import com.kinglian.screeninquiry.model.dto.*;
import com.kinglian.screeninquiry.model.entity.MedOvPresSheet;
import com.kinglian.screeninquiry.service.DoctorOperationService;
import com.kinglian.screeninquiry.utils.DateConvertUtils;
import com.kinglian.screeninquiry.utils.GetAge;
import com.kinglian.screeninquiry.utils.JsonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author HXC
 * @date 2019-1-17
 */
@Service
@Transactional
public class DoctorOperationServiceImpl implements DoctorOperationService {

    @Autowired
    private DoctorOperationMapper doctorOperationMapper;

    @Autowired
    private MedOvPresSheetMapper medOvPresSheetMapper;

    /**
     * 获取医生端待处理列表
     *
     * @param time
     * @param doctorId
     * @return
     */
    @Override
    public List pendingOrder(String time, String doctorId) {
        //查询待接诊订单
        List<DoctorPendingOrderRep> newOrder = doctorOperationMapper.selectPengdingOrder(doctorId);
        newOrder.stream().forEach(x-> {
            x.setType(1);
            if (x.getBirthDay() != null) {
                try {
                    x.setAge(GetAge.getAge(x.getBirthDay()));
                    x.setFrommatDay(DateConvertUtils.dateToStrLong(x.getCreatDay()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        //查询审核未通过订单
        List<DoctorPendingOrderRep> failOrder = doctorOperationMapper.selectFailOrder(doctorId);
        failOrder.stream().forEach(x->{
            x.setType(2);
            try {
                if (x.getBirthDay() != null) {
                    x.setAge(GetAge.getAge(x.getBirthDay()));
                    x.setFrommatDay(DateConvertUtils.dateToStrLong(x.getCreatDay()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        List<DoctorPendingOrderRep> result = new ArrayList<>();
        result.addAll(newOrder);
        result.addAll(failOrder);
        return result;
    }

    /**
     * 根据关键字获取药品信息
     *
     * @param searchKey
     * @return
     */
    @Override
    public List searchDrugInfo(String searchKey) {
        JSONObject reqJ=new JSONObject();
        reqJ.put("command", "command_search_drug_list");
        reqJ.put("timestamp", System.currentTimeMillis()+"");
        JSONObject body=new JSONObject();
        body.put("drugStoreId", "149457522472b8c7201549f142cc9542");
        body.put("searchKey", searchKey);
        body.put("searchType", "1");
        reqJ.put("body", body);
        String url = "http://183.63.114.204:8888/hrs/api/cloudHospital?data={data}";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class,reqJ.toString());
        JSONObject strBody = JSON.parseObject(entity.getBody());
        SearchDurgInfoRep searchDurgInfoRep = JSON.toJavaObject(strBody, SearchDurgInfoRep.class);
        List<DrugInfoRep> result = searchDurgInfoRep.getBody().getList();
        return result;
    }

    /**
     * 获取医生端已完成问诊订单
     *
     * @param doctorId
     * @return
     */
    @Override
    public List<CompleteOrderRep> completeProfile(String doctorId) {
        List<CompleteOrderRep> result = doctorOperationMapper.selectCompleteOrder(doctorId);
        result.stream().forEach(x->{
            try {
                x.setAge(GetAge.getAge(x.getBirthDay()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return result;
    }

    /**
     * 获取医生端历史订单
     * @return
     */
    @Override
    public List historyOrder(JsonEntity jsonEntity) {
        String doctorId = "";
        String beginTime = null;
        String endTime = null;
        String patientName = null;
        Integer patientType = null;
        Integer type = null;
        String drugstoreName = null;
        if (jsonEntity.getBody().containsKey("doctorId")) {
            doctorId = jsonEntity.getBody().get("doctorId");
        }
        if (jsonEntity.getBody().containsKey("beginTime")) {
            beginTime = jsonEntity.getBody().get("beginTime");
        }
        if (jsonEntity.getBody().containsKey("endTime")) {
            endTime = jsonEntity.getBody().get("endTime");
        }
        if (jsonEntity.getBody().containsKey("patientName")) {
            patientName = jsonEntity.getBody().get("patientName");
        }
        if (jsonEntity.getBody().containsKey("patientType")) {
            if (!"".equals(jsonEntity.getBody().get("patientType")) && jsonEntity.getBody().get("patientType") != null) {
                patientType = Integer.parseInt(jsonEntity.getBody().get("patientType"));
            }
        }
        if (jsonEntity.getBody().containsKey("type")) {
            if (!"".equals(jsonEntity.getBody().get("type")) && jsonEntity.getBody().get("type")!= null) {
                type = Integer.parseInt(jsonEntity.getBody().get("type"));
            }
        }
        if (jsonEntity.getBody().containsKey("drugstoreName")) {
            drugstoreName = jsonEntity.getBody().get("drugstoreName");
        }
        List<HistoryOrderRep> result = doctorOperationMapper.selectHistoryOrder(doctorId, beginTime, endTime, patientName, patientType, type, drugstoreName);
        result.stream().forEach(x-> {
            try {
                x.setAge(GetAge.getAge(x.getBirthDay()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return result;
    }

    /**
     * 保存处方接口
     * @param saveDrugInfoReq
     * @return
     */
    @Override
    public boolean saveDrugInfo(SaveDrugInfoReq saveDrugInfoReq, BigDecimal totalCost) {
        if (saveDrugInfoReq.getIsSave() == 0) {
            MedOvPresSheet medOvPresSheet = new MedOvPresSheet();
            medOvPresSheet.setVisitid(saveDrugInfoReq.getOrderId());
            medOvPresSheet.setDeleted(false);
            medOvPresSheet.setAuditStatus(0);
            medOvPresSheet.setAttention(saveDrugInfoReq.getAttention());
            medOvPresSheet.setAdvice(saveDrugInfoReq.getAdvice());
            medOvPresSheet.setCreatedBy(saveDrugInfoReq.getDoctorId());
            medOvPresSheet.setCreatedDate(new Date());
            //medOvPresSheet.setTotalPrice(totalCost);
            return medOvPresSheetMapper.insert(medOvPresSheet)>0;
        } else {
            MedOvPresSheet medOvPresSheet = new MedOvPresSheet();
            medOvPresSheet.setVisitid(saveDrugInfoReq.getOrderId());
            medOvPresSheet.setDeleted(false);
            medOvPresSheet.setAuditStatus(0);
            medOvPresSheet.setAttention(saveDrugInfoReq.getAttention());
            medOvPresSheet.setAdvice(saveDrugInfoReq.getAdvice());
            medOvPresSheet.setUpdatedBy(saveDrugInfoReq.getDoctorId());
            medOvPresSheet.setUpdatedDate(new Date());
            //medOvPresSheet.setTotalPrice(totalCost);
            return medOvPresSheetMapper.update(medOvPresSheet,new EntityWrapper<MedOvPresSheet>().eq("visitid",saveDrugInfoReq.getOrderId()))>0;
        }

    }

}
