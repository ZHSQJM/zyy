package com.kinglian.screeninquiry.service;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.kinglian.screeninquiry.model.entity.MedOfficeVisit;

import java.util.Map;

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

    Page getMedicalRecordByOpenId(Query<Map> mapQuery);

    Map<String,String> SetWeChatTemplateInformation(String visitid);
}
