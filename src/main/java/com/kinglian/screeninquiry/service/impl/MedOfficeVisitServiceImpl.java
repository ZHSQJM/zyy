package com.kinglian.screeninquiry.service.impl;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kinglian.screeninquiry.dao.MedOfficeVisitMapper;
import com.kinglian.screeninquiry.model.entity.MedOfficeVisit;
import com.kinglian.screeninquiry.service.MedOfficeVisitService;
import com.kinglian.screeninquiry.utils.DateConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;

/**
 * @author HXC
 * @date 2019-1-18
 */
@Service
@Transactional
public class MedOfficeVisitServiceImpl extends ServiceImpl<MedOfficeVisitMapper, MedOfficeVisit> implements MedOfficeVisitService {
    @Autowired
    private MedOfficeVisitMapper medOfficeVisitMapper;

    /**
     * 医生端接诊操作
     *
     * @param orderId
     * @return
     */
    @Override
    public Boolean clinicalReception(String orderId) {
        MedOfficeVisit medOfficeVisit = new MedOfficeVisit();
        medOfficeVisit.setVisitDate(new Date());
        medOfficeVisit.setVisitStatus("1");
        return medOfficeVisitMapper.update(medOfficeVisit, new EntityWrapper<MedOfficeVisit>().eq("visitid", orderId))>0;
    }

    @Override
    public Page getMedicalRecordByOpenId(Query<Map> query) {
        List<Map> result = new ArrayList<>();
        List<Map> records = medOfficeVisitMapper.getMedicalRecordByOpenId(query, query.getCondition());
            if (records != null && records.size() != 0){
                Map map = records.get(0);
                java.util.Date visitDate = (java.util.Date)map.get("visitDate");
                String date = DateConvertUtils.dateToStrLong(visitDate);
                map.put("visitDate", date);
                result.add(map);
            }
        return query.setRecords(result);
    }

    @Override
    public Map<String, String> SetWeChatTemplateInformation(String visitid) {
        return medOfficeVisitMapper.SetWeChatTemplateInformation(visitid);
    }
}
