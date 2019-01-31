package com.kinglian.screeninquiry.dao;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kinglian.screeninquiry.model.entity.MedOfficeVisit;
import org.apache.ibatis.annotations.*;

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
            " mov.Interrogation_type, " +
            " mops.audit_status" +
            " FROM" +
            " hospital_doctor_extension hd" +
            " INNER JOIN med_office_visit mov ON hd.id = mov.cdid" +
            " INNER JOIN med_patient_info u ON u.portal_id = mov.portalid " +
            " INNER JOIN med_ov_pres_sheet mops ON mops.visitid = mov.visitid" +
            " WHERE" +
            " u.open_id = = #{openid}")
    @Results({@Result(property = "pictureUrl", column = "picture_url"),@Result(property = "doctorName", column = "doctor_name"),
              @Result(property = "visitStatus", column = "visit_status"),@Result(property = "visitDate", column = "visit_date"),
              @Result(property = "InterrogationType", column = "Interrogation_type") ,
            @Result(property = "visitid", column = "visitid"),@Result(property = "auditStatus", column = "audit_status")})
    List<Map> getMedicalRecordByOpenId(Query<Map> query, Map<String,Object> condition);

    @Select("SELECT DISTINCT" +
            " momr.visitid," +
            " momr.diagnosis " +
            " FROM" +
            " med_ov_medical_record momr" +
            " INNER JOIN med_office_visit mov ON momr.visitid = mov.visitid" +
            " INNER JOIN med_ov_pres_sheet mops ON mops.visitid = mov.visitid" +
            " INNER JOIN med_ov_prescription mop ON mop.visitid = mops.visitid" +
            " INNER JOIN med_patient_info mpi ON mpi.id = momr.patientid " +
            " WHERE" +
            " momr.visitid = #{visitid}")
    @Results({@Result(property = "diagnosis", column = "diagnosis"),
            @Result(property = "visitid", column = "visitid")
           /* @Result(property = "medList",javaType=List.class,column = "visitid",many = @Many(
            select = "com.kinglian.screeninquiry.dao.MedOvPrescriptionMapper.findByVisitId"))*/
            })
    Map<String,String> SetWeChatTemplateInformation(@Param("visitid") String visitid);


    @Select("SELECT * from med_office_visit WHERE visitid =#{visitid}")
    MedOfficeVisit getByVisitId(String visitid);
}
