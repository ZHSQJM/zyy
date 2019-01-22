package com.kinglian.screeninquiry.dao;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kinglian.screeninquiry.model.entity.MedOfficeVisit;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author HXC
 * @date 2019-1-18
 */
public interface MedOfficeVisitMapper extends BaseMapper<MedOfficeVisit> {

    @Select("SELECT" +
            " hd.picture_url," +
            " hd.doctor_name," +
            " mov.visitid," +
            " mov.visit_status," +
            " mov.visit_date," +
            " mov.Interrogation_type " +
            " FROM" +
            " hospital_doctor_extension hd" +
            " INNER JOIN med_office_visit mov ON hd.id = mov.cdid" +
            " INNER JOIN `user` u ON u.user_id = mov.portalid " +
            " WHERE" +
            " u.wx_open_id_gzh = #{openid}")
    @Results({@Result(property = "pictureUrl", column = "picture_url"),@Result(property = "doctorName", column = "doctor_name"),
              @Result(property = "visitStatus", column = "visit_status"),@Result(property = "visitDate", column = "visit_date"),
              @Result(property = "InterrogationType", column = "Interrogation_type") ,@Result(property = "visitid", column = "visitid")})
    List<Map> getMedicalRecordByOpenId(Query<Map> query, Map<String,Object> condition);
}
