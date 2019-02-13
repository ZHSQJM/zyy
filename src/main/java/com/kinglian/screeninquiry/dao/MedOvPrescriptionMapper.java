package com.kinglian.screeninquiry.dao;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kinglian.screeninquiry.model.dto.Prescription;
import com.kinglian.screeninquiry.model.dto.Recipe;
import com.kinglian.screeninquiry.model.entity.MedOvPrescription;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * MedOvPrescriptionMapper继承基类
 */
public interface MedOvPrescriptionMapper extends BaseMapper<MedOvPrescription> {

    @Select("SELECT" +
            " u.user_name," +
            " u.sex," +
            " u.mobile," +
            " u.address," +
            " u.birthday," +
            " mops.audit_note," +
            " mops.department," +
            " mops.judgeMent," +
            " mops.audit_doctor_name," +
            " mop.stuff_name," +
            " mop.specs," +
            " mop.pres_usage," +
            " mop.pres_dosage," +
            " mop.pres_qty" +
            " FROM" +
            " med_patient_info mpi" +
            " INNER JOIN med_ov_prescription mop ON mpi.id = mop.patientid" +
            " INNER JOIN med_ov_pres_sheet mops ON mops.sheetid = mop.pre_sheet_id" +
            " INNER JOIN user u ON u.user_id = mpi.portal_id" +
            " WHERE" +
            " u.user_type = '1'" +
            " AND" +
            " mops.sheetid = #{presid}")
    @Results({@Result(property = "userName", column = "user_name"), @Result(property = "sex", column = "sex"),
            @Result(property = "birthday", column = "birthday"), @Result(property = "mobile", column = "mobile"),
            @Result(property = "address", column = "address"), @Result(property = "auditNote", column = "audit_note"),
            @Result(property = "stuffName", column = "stuff_name"), @Result(property = "specs", column = "specs"),
            @Result(property = "presUsageName", column = "pres_usage"), @Result(property = "presDosage", column = "pres_dosage"),
            @Result(property = "judgeMent", column = "judgeMent"), @Result(property = "department", column = "department"),
            @Result(property = "presQty", column = "pres_qty"),@Result(property = "auditDoctorName", column = "audit_doctor_name")})
    List<Map> getPreByPreId(Query<Map> objectQuery, Map<String, Object> objectEntityWrapper);

    @Select("SELECT" +
            " mpi.member_name," +
            " mops.sheetid," +
            " mops.audit_note," +
            " mops.audit_status," +
            " mops.audit_date" +
            " FROM" +
            " med_patient_info mpi" +
            " INNER JOIN med_ov_prescription mop ON mpi.id = mop.patientid" +
            " INNER JOIN med_ov_pres_sheet mops ON mops.sheetid = mop.pre_sheet_id" +
            " INNER JOIN med_office_visit mov ON mov.visitid = mops.visitid" +
            " WHERE" +
            " mops.audit_status = #{auditStatus}" +
            " AND mov.cdid = '1000910'")
    @Results({@Result(property = "sheetid", column = "sheetid"),
            @Result(property = "userName", column = "member_name"),
            @Result(property = "auditNote", column = "audit_note"),
            @Result(property = "auditStatus", column = "audit_status"),
            @Result(property = "auditDate", column = "audit_date")})
    List<Map> getPresOrder(Query<Map> query, Map<String,Object> condition);

    @Select("SELECT " +
            " stuff_name," +
            " specs," +
            " pres_usage," +
            " dosage," +
            " pres_freq" +
            " FROM" +
            " med_ov_prescription" +
            " WHERE" +
            " med_ov_prescription.visitid = #{visitid}")
    @Results({@Result(property = "stuffName", column = "stuff_name"),
              @Result(property = "specs", column = "specs"),
              @Result(property = "presUsage", column = "pres_usage"),
              @Result(property = "dosage", column = "dosage"),
              @Result(property = "presFreq", column = "pres_freq")})
    List<MedOvPrescription> findByVisitId(String visitid);


    @Select("SELECT " +
            "  mpi.member_name, " +
            "  mpi.sex, " +
            "  mpi.birthday, " +
            "  mpi.id, " +
            "  mov.visit_date, " +
            "  mov.cdid, " +
            "  mop.visitid, " +
            "  momr.diagnosis, " +
            "  mops.audit_doctor_name, " +
            "  mops.audit_by, " +
            "  mops.department, " +
            "  mops.advice " +
            "  FROM " +
            "  med_office_visit mov " +
            "  inner JOIN med_ov_pres_sheet mops ON mov.visitid = mops.visitid " +
            "  inner JOIN med_ov_prescription mop ON mop.visitid = mops.visitid " +
            "  inner JOIN med_ov_medical_record momr ON momr.visitid = mov.visitid " +
            "  inner JOIN med_patient_info mpi ON mpi.id = mov.patientid " +
            "  WHERE  " +
            "  mov.visitid =  #{visitid}")
    @Results({@Result(property = "visitid", column = "visitid",id=true),
            @Result(property = "memberName", column = "member_name"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "memberId", column = "member_id"),
            @Result(property = "regDepartmentName", column = "department"),
            @Result(property = "visitDate", column = "visit_date"),
            @Result(property = "cdid", column = "cdid"),
            @Result(property = "judgeMent", column = "judgeMent"),
            @Result(property = "auditDoctorName", column = "audit_doctor_name"),
            @Result(property = "audit_by", column = "audit_by"),
            @Result(property = "advice", column = "advice"),
            @Result(property = "address", column = "address"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "medList",javaType=List.class,column = "visitid",many = @Many(
                    select = "com.kinglian.screeninquiry.dao.MedOvPrescriptionMapper.findByVisitId"
            ))
    })
    List<Map> ObtainPrescriptionPad(Query<Map> mapQuery, Map<String,Object> condition);
}
