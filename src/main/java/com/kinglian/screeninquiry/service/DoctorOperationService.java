package com.kinglian.screeninquiry.service;

import java.util.Date;
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

    /**
     * 获取医生端历史订单
     * @param doctorId 医生id
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @param patientName   患者姓名
     * @param patientType   患者类别
     * @param type  订单状态
     * @return
     */
    List historyOrder(String doctorId, Date beginTime, Date endTime, String patientName, boolean patientType, int type);

}
