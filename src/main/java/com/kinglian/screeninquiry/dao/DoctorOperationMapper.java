package com.kinglian.screeninquiry.dao;

import com.kinglian.screeninquiry.model.dto.DoctorPendingOrderRep;
import org.apache.ibatis.annotations.*;

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
    @Results({@Result(property = "patientName",column = "member_name"),@Result(property = "patinetId",column = "patinetid"),@Result(property = "sex",column = "sex"),
            @Result(property = "birthDay",column = "birthday"),@Result(property = "orderId",column = "visitid")})
    @ResultType(DoctorPendingOrderRep.class)
    @Select("SELECT\n" +
            "\tmov.patientid,\n" +
            "\tmov.visitid,\n" +
            "\tmpi.birthday,\n" +
            "\tmpi.sex,\n" +
            "\tmpi.member_name \n" +
            "FROM\n" +
            "\tmed_office_visit mov,\n" +
            "\tmed_patient_info mpi \n" +
            "WHERE\n" +
            "\tmov.cdid = #{doctorId} \n" +
            "\tAND mov.visit_status = '0' \n" +
            "\tAND mov.deleted = '0' \n" +
            "\tAND mov.patientid = mpi.id")
    List<DoctorPendingOrderRep> selectPengdingOrder(@Param("doctorId") String doctorId);

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
            "\t\tmed_office_visit mov\n" +
            "\t\t, ( SELECT visitid, audit_note FROM med_ov_pres_sheet WHERE audit_status = '-1' AND deleted = '0' ) mops \n" +
            "\tWHERE\n" +
            "\t\tcdid = #{doctorId} AND mov.visitid = mops.visitid \n" +
            "\t) a,\n" +
            "\tmed_patient_info mpi \n" +
            "WHERE\n" +
            "\ta.patientid = mpi.id")
    List<DoctorPendingOrderRep> selectFailOrder(@Param("doctorId") String doctorId);
}
