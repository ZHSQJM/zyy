package com.kinglian.screeninquiry.service;

import com.baomidou.mybatisplus.service.IService;
import com.kinglian.screeninquiry.model.entity.MedOfficeVisit;

/**
 * @author HXC
 * @date 2019-1-18
 */
public interface MedOfficeVisitService extends IService<MedOfficeVisit> {

    /**
     * 医生端接诊操作
     * @param orderId
     * @return
     */
    Boolean clinicalReception(String orderId);
}
