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
}
