package com.kinglian.screeninquiry.service.impl;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kinglian.screeninquiry.dao.MedOvMedicalRecordMapper;
import com.kinglian.screeninquiry.model.dto.SaveCase;
import com.kinglian.screeninquiry.model.entity.MedOvMedicalRecord;
import com.kinglian.screeninquiry.service.MedOvMedicalRecordService;
import com.kinglian.screeninquiry.utils.GetAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean saveCaseHistory(SaveCase saveCase) {
        MedOvMedicalRecord medOvMedicalRecord = new MedOvMedicalRecord();
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
        return medOvMedicalRecordMapper.insert(medOvMedicalRecord)>0;
    }

    @Override
    public Page getMedicalRecordDetails(Query<Map> query) {
        List<Map> medicalRecordDetails = null;
        try {
            medicalRecordDetails = medOvMedicalRecordMapper.getMedicalRecordDetails(query, query.getCondition());
            if (medicalRecordDetails != null || medicalRecordDetails.size() != 0){
                Map map = medicalRecordDetails.get(0);
                java.util.Date birthday = (java.util.Date)map.get("birthday");
                map.put("birthday", GetAge.getAge(birthday));
                medicalRecordDetails.add(map);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return query.setRecords(medicalRecordDetails);
    }
}
