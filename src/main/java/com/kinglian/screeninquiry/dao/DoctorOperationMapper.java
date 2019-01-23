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
            "\tmed_patient_info mpi,\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\tmov.visitid,\n" +
            "\t\tmov.patientid,\n" +
            "\t\tmov.visit_date \n" +
            "\tFROM\n" +
            "\t\tmed_ov_pres_sheet mops,\n" +
            "\t\t( SELECT visitid, patientid, visit_date FROM med_office_visit WHERE cdid = #{doctorId} AND visit_status = '1' ) mov \n" +
            "\tWHERE\n" +
            "\t\tmops.visitid = mov.visitid \n" +
            "\t\tAND mops.audit_status = 1 \n" +
            "\t) o \n" +
            "WHERE\n" +
            "\tmpi.id = o.patientid")
    @Results({@Result(property = "patientName",column = "member_name"),@Result(property = "patinetId",column = "patinetid"),
            @Result(property = "sex",column = "sex"), @Result(property = "birthDay",column = "birthday"),
            @Result(property = "orderId",column = "visitid"), @Result(property = "creatDay",column = "visit_date")})
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
            "\t\tmed_office_visit mov\n" +
            "\t\t, ( SELECT visitid, audit_note FROM med_ov_pres_sheet WHERE audit_status = '-1' AND deleted = '0' ) mops \n" +
            "\tWHERE\n" +
            "\t\tcdid = #{doctorId} AND mov.visitid = mops.visitid \n" +
            "\t) a,\n" +
            "\tmed_patient_info mpi \n" +
            "WHERE\n" +
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
