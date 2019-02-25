package com.kinglian.screeninquiry.service;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.plugins.Page;
import com.kinglian.screeninquiry.model.dto.*;
import com.kinglian.screeninquiry.utils.R;

import java.util.List;
import java.util.Map;

public interface DoctorBusinessService  {

    Page getDoctorList(Query<Map> mapQuery);

    Page getDeptList(Query<Map> mapQuery);

    VisitResult submitVisitOrder (RequestBaseParam<SubmitVisitBodyParam> param) ;

    Page getDoctorListByDeptId(RequestBaseParam<DoctorDepartmentParam> param,Query<Map> mapQuery);

    OrderStateVto getOrderState(RequestBaseParam<SubmitVisitBodyParam> param);

    MedVisitInfo queryVisitInfo(RequestBaseParam<SubmitVisitBodyParam> param);

    MedVisitInfo queryVisitInfoByAudit(RequestBaseParam<SubmitVisitBodyParam> param);

    UserInfoDto queryUserInfo(RequestBaseParam<OpIdParam> param) throws IllegalAccessException;
}
