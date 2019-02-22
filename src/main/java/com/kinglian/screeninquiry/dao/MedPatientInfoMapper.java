package com.kinglian.screeninquiry.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kinglian.screeninquiry.model.dto.UserInfoDto;
import com.kinglian.screeninquiry.model.entity.MedPatientInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * MedPatientInfoMapper继承基类
 */
public interface MedPatientInfoMapper extends BaseMapper<MedPatientInfo> {

    @Update("UPDATE med_patient_info set open_id = #{openid} where id = #{portalid}")
    void updateByPatientId(String openid,String portalid);

    @Select("SELECT id,member_name,sex,birthday,new_user FROM med_patient_info WHERE id = #{id}")
    @Results({@Result(property = "id",column = "id"),@Result(property = "name",column = "member_name"),
            @Result(property = "sex",column = "sex"),@Result(property = "birthDay",column = "birthday"),
            @Result(property = "newUser",column = "new_user")})
    @ResultType(UserInfoDto.class)
    UserInfoDto selectInfoById(String id);
}
