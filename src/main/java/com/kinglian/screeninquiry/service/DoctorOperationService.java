package com.kinglian.screeninquiry.service;

import java.util.List;

/**
 * @author HXC
 * @date 2019-1-17
 */
public interface DoctorOperationService {
    /**
     * 获取医生端待处理列表
     * @param time
     * @param doctorId
     * @return
     */
    List pendingOrder(String time, String doctorId);

    /**
     * 根据关键字获取药品信息
     * @param searchKey
     * @return
     */
    List searchDrugInfo(String searchKey);

    /**
     * 获取医生端已完成问诊订单
     * @param doctorId
     * @return
     */
    List completeProfile(String doctorId);
}
