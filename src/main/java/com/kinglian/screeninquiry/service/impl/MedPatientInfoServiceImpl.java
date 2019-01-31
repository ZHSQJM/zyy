/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: MedPatientInfoServiceImpl
 * Author:   weiyz
 * Date:     2019/1/16 19:47
 * Description: MedPatientInfoService实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kinglian.screeninquiry.dao.MedPatientInfoMapper;
import com.kinglian.screeninquiry.model.entity.MedPatientInfo;
import com.kinglian.screeninquiry.service.MedPatientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 〈MedPatientInfoService实现类〉
 * @author weiyz
 * @create 2019/1/16
 * @since 1.0.0
 */
@Service
@Transactional
public class MedPatientInfoServiceImpl extends ServiceImpl<MedPatientInfoMapper,MedPatientInfo> implements MedPatientInfoService {

    @Autowired
    MedPatientInfoMapper medPatientInfoMapper;

    @Override
    public void updateById(String portalid) {
        medPatientInfoMapper.updateById(portalid);
    }
}
