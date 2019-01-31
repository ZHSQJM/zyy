package com.kinglian.screeninquiry.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kinglian.screeninquiry.model.entity.MedPatientInfo;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * MedPatientInfoMapper继承基类
 */
@Repository
public interface MedPatientInfoMapper extends BaseMapper<MedPatientInfo> {

    @Update("UPDATE med_patient_info set open_id = 1 where portal_id = #{portalid}")
    void updateByPortalid(String portalid);
}