package com.kinglian.screeninquiry.dao;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kinglian.screeninquiry.model.entity.MedOvMedicalRecord;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author HXC
 * @date 2019-1-21
 */
public interface MedOvMedicalRecordMapper extends BaseMapper<MedOvMedicalRecord> {


    @Select("SELECT " +
            " DISTINCT momr.visitid," +
            " mpi.member_name," +
            " mpi.sex," +
            " mpi.birthday," +
            " mop.pre_sheet_id," +
            " mops.department," +
            " mops.advice," +
            " momr.recordid," +
            " momr.chief_complaint," +
            " momr.present_illness," +
            " momr.drug_allergy," +
            " momr.special_crowd," +
            " momr.high_blood_pressure," +
            " momr.diabetes," +
            " momr.cardiopathy," +
            " momr.other_illness," +
            " momr.pharmacy_info," +
            " momr.untoward_effect," +
            " momr.uncomfortable_symptom," +
            " momr.diagnosis" +
            " FROM" +
            " med_ov_medical_record momr" +
            " INNER JOIN med_office_visit mov ON momr.visitid = mov.visitid" +
            " INNER JOIN med_ov_pres_sheet mops ON mops.visitid = mov.visitid" +
            " INNER JOIN med_ov_prescription mop ON mop.visitid = mops.visitid" +
            " INNER JOIN med_patient_info mpi ON mpi.id = momr.patientid " +
            " WHERE" +
            " momr.visitid = #{visitid}")
    @Results({@Result(property = "visitid", column = "visitid",id=true),
            @Result(property = "memberName", column = "member_name"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "preSheetId", column = "pre_sheet_id"),
            @Result(property = "department", column = "department"),
            @Result(property = "advice", column = "advice"),
            @Result(property = "recordid", column = "recordid"),
            @Result(property = "chiefComplaint", column = "chief_complaint"),
            @Result(property = "presentIllness", column = "present_illness"),
            @Result(property = "drugAllergy", column = "drug_allergy"),
            @Result(property = "specialCrowd", column = "special_crowd"),
            @Result(property = "highBloodPressure", column = "high_blood_pressure"),
            @Result(property = "diabetes", column = "diabetes"),
            @Result(property = "cardiopathy", column = "cardiopathy"),
            @Result(property = "otherIllness", column = "other_illness"),
            @Result(property = "pharmacyInfo", column = "pharmacy_info"),
            @Result(property = "untowardEffect", column = "untoward_effect"),
            @Result(property = "uncomfortableSymptom", column = "uncomfortable_symptom"),
            @Result(property = "diagnosis", column = "diagnosis"),
            @Result(property = "medList",javaType=List.class,column = "visitid",many = @Many(
                    select = "com.kinglian.screeninquiry.dao.MedOvPrescriptionMapper.findByVisitId"
            ))
    })
    List<Map> getMedicalRecordDetails(Query<Map> query, Map<String,Object> condition);
}
