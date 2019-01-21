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
@Repository
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
            " u.user_name," +
            " mops.sheetid," +
            " mops.audit_note," +
            " mops.audit_status," +
            " mops.audit_date" +
            " FROM" +
            " med_patient_info mpi" +
            " INNER JOIN med_ov_prescription mop ON mpi.id = mop.patientid" +
            " INNER JOIN med_ov_pres_sheet mops ON mops.sheetid = mop.pre_sheet_id" +
            " INNER JOIN USER u ON u.user_id = mpi.portal_id" +
            " WHERE" +
            " mops.audit_status = #{auditStatus}")
    @Results({@Result(property = "sheetid", column = "sheetid"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "auditNote", column = "audit_note"),
            @Result(property = "auditStatus", column = "audit_status"),
            @Result(property = "auditDate", column = "audit_date")})
    List<Map> getPresOrder(Query<Map> query, Map<String,Object> condition);
}