package com.kinglian.screeninquiry.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kinglian.screeninquiry.dao.MedOvMedicalRecordMapper;
import com.kinglian.screeninquiry.model.dto.SaveCase;
import com.kinglian.screeninquiry.model.entity.MedOvMedicalRecord;
import com.kinglian.screeninquiry.service.MedOvMedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
