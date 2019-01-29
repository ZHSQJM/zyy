package com.kinglian.screeninquiry.controller;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.plugins.Page;
import com.kinglian.screeninquiry.service.DoctorBusinessService;
import com.kinglian.screeninquiry.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kinglian.screeninquiry.model.dto.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈MedOvPrescription控制器〉
 * @author weiyz
 * @create 2019/1/17
 * @since 1.0.0
 */
@CrossOrigin
@RestController
@RequestMapping("/screenInquiry/screenUser")
public class ScreenUserController {

    @Autowired
    DoctorBusinessService doctorBusinessService;



    /**
     * 查询医生列表
     *
     * @param param 分页对象
     * @return 分页对象
     */
    @PostMapping(value = "/getDoctorInfoList")
    public R<Page> getDoctorInfoList(@RequestBody RequestBaseParam<DoctorDepartmentParam> param) {
        Map params = new HashMap();
        if (param.getBody().getPageNumber()!=null) {
            params.put("page", param.getBody().getPageNumber());
        }
        else
        {
            params.put("page", 1);
        }
        params.put("limit", 9);

        if (param.getBody().getDoctorDeptId()!=null&&!param.getBody().getDoctorDeptId().equals("0"))
        {

            return new R<>(doctorBusinessService.getDoctorListByDeptId(param,new Query<>(params)));


        }

        return new R<>(doctorBusinessService.getDoctorList(new Query<>(params)));
    }



    /**
     * 查询科室列表
     *
     * @param param 分页对象
     * @return 分页对象
     */
    @PostMapping(value = "/getDeptInfoList")
    public R<Page> getDeptInfoList(@RequestBody RequestBaseParam<RequestBodyParam> param) {
        Map params = new HashMap();

        if (param.getBody().getPageNumber()!=null) {
            params.put("page", param.getBody().getPageNumber());
        }
        else
        {
            params.put("page", 1);
        }
        params.put("limit", 9);
        return new R<>(doctorBusinessService.getDeptList(new Query<>(params)));
    }


    /**
     * 提交问诊
     *
     * @param param 提交问诊
     * @return 提交问诊
     */
    @PostMapping(value = "/submitVisitOrder")
    public R submitVisitOrder(@RequestBody RequestBaseParam<SubmitVisitBodyParam> param) {
        R<VisitResult> ret=new R();
        VisitResult r =doctorBusinessService.submitVisitOrder(param);
       if (r.equals(""))
       {
           ret.setResult("005");
       }
       ret.setData(r);
       return ret;
    }


    /**
     * 查询订单状态
     *
     * @param param 查询订单状态
     * @return 查询订单状态
     */
    @PostMapping(value = "/queryOrderPayment")
    public R queryOrderPayment(@RequestBody RequestBaseParam<SubmitVisitBodyParam> param) {
        return new R<>(doctorBusinessService.getOrderState(param));
    }



    /**
     * 查询
     *
     * @param param 查询处方
     * @return 查询订单状态
     */
    @PostMapping(value = "/queryVisitInfo")
    public R queryVisitInfo(@RequestBody RequestBaseParam<SubmitVisitBodyParam> param) {
        return new R<>(doctorBusinessService.queryVisitInfo(param));
    }



    /**
     * 查询
     *
     * @param param 查询处方
     * @return 查询订单状态
     */
    @PostMapping(value = "/queryVisitInfoByAudit")
    public R queryPresOrder(@RequestBody RequestBaseParam<SubmitVisitBodyParam> param) {
        return new R<>(doctorBusinessService.queryVisitInfo(param));
    }

}
