package com.kinglian.screeninquiry.service.impl;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kinglian.screeninquiry.dao.MedOfficeVisitMapper;
import com.kinglian.screeninquiry.dao.MedOvMedicalRecordMapper;
import com.kinglian.screeninquiry.dao.MedPatientInfoMapper;
import com.kinglian.screeninquiry.model.dto.SaveCase;
import com.kinglian.screeninquiry.model.entity.MedOfficeVisit;
import com.kinglian.screeninquiry.model.entity.MedOvMedicalRecord;
import com.kinglian.screeninquiry.model.entity.MedPatientInfo;
import com.kinglian.screeninquiry.service.MedOvMedicalRecordService;
import com.kinglian.screeninquiry.utils.DateConvertUtils;
import com.kinglian.screeninquiry.utils.GetAge;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author HXC
 * @date 2019-1-21
 */
@Service
public class MedOvMedicalRecordServiceImpl extends ServiceImpl<MedOvMedicalRecordMapper, MedOvMedicalRecord> implements MedOvMedicalRecordService {

    @Autowired
    private MedOvMedicalRecordMapper medOvMedicalRecordMapper;

    @Autowired
    private MedPatientInfoMapper medPatientInfoMapper;


    @Autowired
    private MedOfficeVisitMapper medOfficeVisitMapper;

    @Override
    public boolean saveCaseHistory(SaveCase saveCase) {
        MedPatientInfo queryEntity = new MedPatientInfo();
        queryEntity.setOpId(saveCase.getOpId());
       // queryEntity.setDeleted(false);
        MedPatientInfo saveEntity = medPatientInfoMapper.selectOne(queryEntity);
        if (saveEntity.getMemberName() == null || "".equals(saveEntity.getMemberName())) {
           // MedPatientInfo saveEntity = new MedPatientInfo();
            saveEntity.setMemberName(saveCase.getPatientName());
            saveEntity.setSex(saveCase.getSex());
            saveEntity.setBirthday(GetAge.getBirthDay(saveCase.getAge()));
            saveEntity.setDeleted(false);
            medPatientInfoMapper.update(saveEntity, new EntityWrapper<MedPatientInfo>().eq("op_id", saveCase.getOpId()));
        } else {
            saveCase.setPatientId(saveEntity.getId());
        }

        EntityWrapper<MedOfficeVisit> entity=new EntityWrapper<>();
        entity.eq("visitid",saveCase.getOrderId());
        Map params1 = new HashMap();
        params1.put("page", 1);

        List<MedOfficeVisit> list=medOfficeVisitMapper.selectPage(new Query<>(params1),entity);
        if (list!=null&&list.size()>0) {
            MedOfficeVisit medOfficeVisit1 = list.get(0);
            medOfficeVisit1.setPatientName(saveEntity.getMemberName());
            medOfficeVisit1.setPatientSex(Integer.parseInt(saveEntity.getSex()));
            medOfficeVisit1.setPatientDob(GetAge.getBirthDay(saveCase.getAge()));
            medOfficeVisitMapper.update(medOfficeVisit1, new EntityWrapper<MedOfficeVisit>().eq("visitid", medOfficeVisit1.getVisitid()));

        }
        MedOvMedicalRecord medOvMedicalRecord = new MedOvMedicalRecord();
        medOvMedicalRecord.setPharmacyInfo(saveCase.getPharmacyInfo());
        medOvMedicalRecord.setVisitid(saveCase.getOrderId());
        medOvMedicalRecord.setPatientid(saveCase.getPatientId());
        medOvMedicalRecord.setChiefComplaint(saveCase.getChiefCmplaint());
        medOvMedicalRecord.setPresentIllness(saveCase.getPresentIllness());
        medOvMedicalRecord.setDrugAllergy(saveCase.getDrugAllergy());
        medOvMedicalRecord.setSpecialCrowd(saveCase.getSpecialCrowd());
        medOvMedicalRecord.setHighBloodPressure(saveCase.getHighBloodPressure());
        medOvMedicalRecord.setDiabetes(saveCase.getDiabetes());
        medOvMedicalRecord.setCardiopathy(saveCase.getCardiopathy());
        medOvMedicalRecord.setOtherIllness(saveCase.getOtherIllness());
        medOvMedicalRecord.setPharmacyInfo(saveCase.getPharmacyInfo());
        medOvMedicalRecord.setUntowardEffect(saveCase.getUntowardEffect());
        medOvMedicalRecord.setUncomfortableSymptom(saveCase.getUncomfortableSymptom());
        medOvMedicalRecord.setDiagnosis(saveCase.getDiagnosis());
        medOvMedicalRecord.setDefiniteCase(saveCase.getDefiniteCase());
        return medOvMedicalRecordMapper.insert(medOvMedicalRecord) > 0;
    }

    @Override
    public Page getMedicalRecordDetails(Query<Map> query) {
        List<Map> result = new ArrayList<>();
        List<Map> medicalRecordDetails = null;
        try {
            medicalRecordDetails = medOvMedicalRecordMapper.getMedicalRecordDetails(query, query.getCondition());
            if (medicalRecordDetails != null && medicalRecordDetails.size() != 0) {
                Map map = medicalRecordDetails.get(0);
                java.util.Date birthday = (java.util.Date) map.get("birthday");
                map.put("age", GetAge.getAge(birthday));

                java.util.Date visitDate = (java.util.Date) map.get("visitDate");
                String date = DateConvertUtils.dateToStrLong(visitDate);
                map.put("visitDate", date);

                String birth = DateConvertUtils.dateToStrLong(birthday);
                map.put("birthday", birth);
                result.add(map);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("无数据");
        }
        return query.setRecords(result);
    }
}
