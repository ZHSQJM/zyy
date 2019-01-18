package com.kinglian.screeninquiry.service.impl;

import com.kinglian.screeninquiry.dao.DoctorOperationMapper;
import com.kinglian.screeninquiry.dao.MedOfficeVisitMapper;
import com.kinglian.screeninquiry.model.dto.DoctorPendingOrderRep;
import com.kinglian.screeninquiry.model.entity.MedOfficeVisit;
import com.kinglian.screeninquiry.service.DoctorOperationService;
import com.kinglian.screeninquiry.utils.GetAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HXC
 * @date 2019-1-17
 */
@Service
public class DoctorOperationServiceImpl implements DoctorOperationService {

    @Autowired
    private DoctorOperationMapper doctorOperationMapper;



    /**
     * 获取医生端待处理列表
     *
     * @param time
     * @param doctorId
     * @return
     */
    @Override
    public List pendingOrder(String time, String doctorId) {
        //查询待接诊订单
        List<DoctorPendingOrderRep> newOrder = doctorOperationMapper.selectPengdingOrder(doctorId);
        newOrder.stream().forEach(x-> {
            x.setType(1);
            try {
                x.setAge(GetAge.getAge(x.getBirthDay()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        //查询审核未通过订单
        List<DoctorPendingOrderRep> failOrder = doctorOperationMapper.selectFailOrder(doctorId);
        failOrder.stream().forEach(x->{
            x.setType(2);
            try {
                x.setAge(GetAge.getAge(x.getBirthDay()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        List<DoctorPendingOrderRep> result = new ArrayList<>();
        result.addAll(newOrder);
        result.addAll(failOrder);
        return result;
    }

}
