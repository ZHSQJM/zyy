package com.kinglian.screeninquiry.dao;

import com.kinglian.screeninquiry.model.dto.CompleteOrderRep;
import com.kinglian.screeninquiry.model.dto.DoctorPendingOrderRep;
import com.kinglian.screeninquiry.model.dto.HistoryOrderRep;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author HXC
 * @date 2019-1-17
 */
public interface DoctorOperationMapper {

    /**
     * 查询待处理的订单
     * @param doctorId
     * @return
     */
    @Results({@Result(property = "patientName",column = "member_name"),
            @Result(property = "patinetId",column = "patientid"),@Result(property = "sex",column = "sex"),
            @Result(property = "birthDay",column = "birthday"),@Result(property = "orderId",column = "visitid"),
            @Result(property = "creatDay",column = "created_date"),@Result(property = "newUser",column = "new_user"),
            @Result(property = "opId",column = "op_id")})
    @ResultType(DoctorPendingOrderRep.class)
    @Select("SELECT\n" +
            "\tmov.patientid,\n" +
            "\tmov.visitid,\n" +
            "\tmov.created_date,\n" +
            "\tmpi.birthday,\n" +
            "\tmpi.new_user,\n" +
            "\tmpi.sex,\n" +
            "\tmpi.op_id,\n" +
            "\tmpi.member_name \n" +
            "FROM\n" +
            "\tmed_office_visit mov\n" +
            "\tLEFT JOIN med_patient_info mpi ON mov.patientid = mpi.id \n" +
            "WHERE\n" +
            "\tmov.cdid = #{doctorId} \n" +
            "\tAND mov.visit_status = '0' \n" +
            "\tAND mov.deleted = '0' ")
    List<DoctorPendingOrderRep> selectPengdingOrder(@Param("doctorId") String doctorId);

    /**
     * 查询已完成订单
     * @param doctorId
     * @return
     */
    @Select("SELECT\n" +
            "\tmpi.birthday,\n" +
            "\tmpi.member_name,\n" +
            "\tmpi.sex,\n" +
            "\to.patientid,\n" +
            "\to.visit_date,\n" +
            "\to.visitid \n" +
            "FROM\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\tmov.visitid,\n" +
            "\t\tmov.patientid,\n" +
            "\t\tmov.visit_date \n" +
            "\tFROM\n" +
            "\t\t( SELECT visitid, patientid, visit_date FROM med_office_visit WHERE cdid = #{doctorId} AND visit_status = '1' ) mov\n" +
            "\t\tLEFT JOIN med_ov_pres_sheet mops ON mops.visitid = mov.visitid \n" +
            "\tWHERE\n" +
            "\t\tmops.audit_status = 1 \n" +
            "\t) o\n" +
            "\tLEFT JOIN med_patient_info mpi ON mpi.id = o.patientid")
    @Results({@Result(property = "patientName",column = "member_name"),@Result(property = "patinetId",column = "patinetid"),
            @Result(property = "sex",column = "sex"), @Result(property = "birthDay",column = "birthday"),
            @Result(property = "orderId",column = "visitid"), @Result(property = "creatDay",column = "visit_date"),
            @Result(property = "opId",column = "op_id")})
    @ResultType(CompleteOrderRep.class)
    List<CompleteOrderRep> selectCompleteOrder(@Param("doctorId") String doctorId);

    /**
     * 查询审核失败订单
     * @param doctorId
     * @return
     */
    @Results({@Result(property = "patientName",column = "member_name"),@Result(property = "patinetId",column = "patinetid"),@Result(property = "sex",column = "sex"),
            @Result(property = "birthDay",column = "birthday"),@Result(property = "orderId",column = "visitid"),@Result(property = "remark",column = "audit_note")})
    @ResultType(DoctorPendingOrderRep.class)
    @Select("SELECT\n" +
            "\ta.*,\n" +
            "\tmpi.member_name,\n" +
            "\tmpi.sex,\n" +
            "\tmpi.birthday \n" +
            "FROM\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\tpatientid,\n" +
            "\t\tcdid,\n" +
            "\t\tmov.visitid,\n" +
            "\t\taudit_note \n" +
            "\tFROM\n" +
            "\t( SELECT visitid, audit_note FROM med_ov_pres_sheet WHERE audit_status = '-1' AND deleted = '0' ) mops LEFT JOIN\n" +
            "\t\tmed_office_visit mov\n" +
            "\t\tON\n" +
            "\t\tmov.visitid = mops.visitid\n" +
            "\tWHERE\n" +
            "\t\tcdid = #{doctorId} \n" +
            "\t) a LEFT JOIN\n" +
            "\tmed_patient_info mpi \n" +
            "ON\n" +
            "\ta.patientid = mpi.id")
    List<DoctorPendingOrderRep> selectFailOrder(@Param("doctorId") String doctorId);

    /**
     * 根据医生id查询所有历史订单
     * @param doctorId
     * @param beginTime
     * @param endTime
     * @param patientName
     * @param patientType
     * @param type
     * @return
     */
    List<HistoryOrderRep> selectHistoryOrder(@Param("doctorId") String doctorId,@Param("beginTime") String beginTime,
                                             @Param("endTime") String endTime, @Param("patientName") String patientName,
                                             @Param("patientType") Integer patientType, @Param("type") Integer type,
                                             @Param("drugstoreName") String drugstoreName);
}
