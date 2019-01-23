/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: MedOvPrescriptionServiceImpl
 * Author:   weiyz
 * Date:     2019/1/16 19:45
 * Description: MedOvPrescriptionService实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.service.impl;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kinglian.screeninquiry.dao.MedOvPrescriptionMapper;
import com.kinglian.screeninquiry.model.entity.MedOvPrescription;
import com.kinglian.screeninquiry.service.MedOvPrescriptionService;
import com.kinglian.screeninquiry.utils.GetAge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 〈MedOvPrescriptionService实现类〉
 * @author weiyz
 * @create 2019/1/16
 * @since 1.0.0
 */
@Service
@Transactional
public class MedOvPrescriptionServiceImpl extends ServiceImpl<MedOvPrescriptionMapper,MedOvPrescription> implements MedOvPrescriptionService {

    @Resource
    private MedOvPrescriptionMapper medOvPrescriptionMapper;


    @Override
    public Page getPreByPreId(Query<Map> query) {
        List<Map> maps = medOvPrescriptionMapper.getPreByPreId(query, query.getCondition());
        Map map = maps.get(0);
        java.util.Date birthday = (java.util.Date)map.get("birthday");
        try {
            map.put("birthday", GetAge.getAge(birthday));
            maps.add(map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return  query.setRecords(maps);
    }

    @Override
    public List<MedOvPrescription> findByVisitId(String visitid) {
        return medOvPrescriptionMapper.findByVisitId(visitid);
    }

    @Override
    public Page getPresOrder(Query<Map> query) {
        return query.setRecords(medOvPrescriptionMapper.getPresOrder(query,query.getCondition()));
    }
}
