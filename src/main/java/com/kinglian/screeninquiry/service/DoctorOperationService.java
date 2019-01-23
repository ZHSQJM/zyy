package com.kinglian.screeninquiry.service;

import com.kinglian.screeninquiry.model.dto.SaveDrugInfoReq;
import com.kinglian.screeninquiry.utils.JsonEntity;

import java.math.BigDecimal;
import java.text.ParseException;
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
     * @return
     */
    List historyOrder(JsonEntity jsonEntity) throws ParseException;

    /**
     * 保存处方接口
     * @param saveDrugInfoReq
     * @return
     */
    boolean saveDrugInfo(SaveDrugInfoReq saveDrugInfoReq, BigDecimal totalCost);
}
