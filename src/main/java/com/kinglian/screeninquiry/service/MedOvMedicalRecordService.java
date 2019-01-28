package com.kinglian.screeninquiry.service;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.kinglian.screeninquiry.model.dto.SaveCase;
import com.kinglian.screeninquiry.model.entity.MedOvMedicalRecord;

import java.util.Map;

/**
 * @author HXC
 * @date 2019-1-21
 */
public interface MedOvMedicalRecordService extends IService<MedOvMedicalRecord> {

    boolean saveCaseHistory(SaveCase saveCase);

    Page getMedicalRecordDetails(Query<Map> mapQuery);
}
