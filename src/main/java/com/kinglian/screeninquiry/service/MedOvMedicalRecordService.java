package com.kinglian.screeninquiry.service;

import com.baomidou.mybatisplus.service.IService;
import com.kinglian.screeninquiry.model.dto.SaveCase;
import com.kinglian.screeninquiry.model.entity.MedOvMedicalRecord;

/**
 * @author HXC
 * @date 2019-1-21
 */
public interface MedOvMedicalRecordService extends IService<MedOvMedicalRecord> {

    boolean saveCaseHistory(SaveCase saveCase);
}
