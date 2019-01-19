package com.kinglian.screeninquiry.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.kinglian.screeninquiry.dao.DoctorOperationMapper;
import com.kinglian.screeninquiry.model.dto.DoctorPendingOrderRep;
import com.kinglian.screeninquiry.model.dto.DrugInfoRep;
import com.kinglian.screeninquiry.model.dto.SearchDurgBody;
import com.kinglian.screeninquiry.model.dto.SearchDurgInfoRep;
import com.kinglian.screeninquiry.service.DoctorOperationService;
import com.kinglian.screeninquiry.utils.GetAge;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HXC
 * @date 2019-1-17
 */
@Service
public class DoctorOperationServiceImpl implements DoctorOperationService {

    @Autowired
    private DoctorOperationMapper doctorOperationMapper;



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
            try {
                x.setAge(GetAge.getAge(x.getBirthDay()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        //查询审核未通过订单
        List<DoctorPendingOrderRep> failOrder = doctorOperationMapper.selectFailOrder(doctorId);
        failOrder.stream().forEach(x->{
            x.setType(2);
            try {
                x.setAge(GetAge.getAge(x.getBirthDay()));
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
        String url = "http://118.190.165.84:80/hrs/api/cloudHospital?data={data}";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class,reqJ.toString());
        JSONObject strBody = JSON.parseObject(entity.getBody());
        SearchDurgInfoRep searchDurgInfoRep = JSON.toJavaObject(strBody, SearchDurgInfoRep.class);
        List<DrugInfoRep> result = searchDurgInfoRep.getBody().getList();
        return result;
    }

}
