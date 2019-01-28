package com.kinglian.screeninquiry.service.impl;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kinglian.screeninquiry.dao.MedOvMedicalRecordMapper;
import com.kinglian.screeninquiry.dao.MedPatientInfoMapper;
import com.kinglian.screeninquiry.model.dto.SaveCase;
import com.kinglian.screeninquiry.model.entity.MedOvMedicalRecord;
import com.kinglian.screeninquiry.model.entity.MedPatientInfo;
import com.kinglian.screeninquiry.service.MedOvMedicalRecordService;
import com.kinglian.screeninquiry.utils.GetAge;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MedPatientInfoMapper medPatientInfoMapper;

    @Override
    public boolean saveCaseHistory(SaveCase saveCase) {
        MedPatientInfo queryEntity = new MedPatientInfo();
        queryEntity.setOpId(saveCase.getOpId());
        queryEntity.setDeleted(false);
        MedPatientInfo medPatientInfo = medPatientInfoMapper.selectOne(queryEntity);
        if (medPatientInfo.getMemberName() == null || medPatientInfo.getMemberName() == "") {
            MedPatientInfo saveEntity = new MedPatientInfo();
            saveEntity.setMemberName(saveCase.getPatientName());
            saveEntity.setSex(saveCase.getSex());
            saveEntity.setBirthday(GetAge.getBirthDay(saveCase.getAge()));
            saveEntity.setDeleted(false);
            medPatientInfoMapper.update(saveEntity,new EntityWrapper<MedPatientInfo>().eq("op_id",saveCase.getOpId()));
        }
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
           throw new RuntimeException("无数据");
        }
        return query.setRecords(medicalRecordDetails);
    }
}
