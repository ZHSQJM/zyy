package com.kinglian.screeninquiry.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.kinglian.screeninquiry.service.DoctorOperationService;
import com.kinglian.screeninquiry.service.MedOfficeVisitService;
import com.kinglian.screeninquiry.service.UserService;
import com.kinglian.screeninquiry.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;


/**
 * @author HXC
 * @date 2019-1-16
 */
@RestController
@RequestMapping("/screenInquiry/doctor")
public class DoctorOperationController {

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorOperationService doctorOperationService;

    @Autowired
    private MedOfficeVisitService medOfficeVisitService;

    /**
     * 医生端登录接口
     * @param user
     * @param password
     * @return
     */
    @PostMapping("/login")
    public R<Boolean> login(String user, String password) {
        return new R<>(userService.login(user, password));
    }


    /**
     * 医生端待处理端口
     * @param request
     * @param doctorId
     * @return
     */
    @GetMapping("/pendingOrder")
    public R<List> pendingOrder(HttpServletRequest request, String doctorId) {
        String time = request.getHeader("timeStamp");
        return new R<>(doctorOperationService.pendingOrder(time, doctorId));
    }

    /**
     * 医生端接单操作
     * @param orderId
     * @return
     */
    @GetMapping("/clinicalReception")
    public R<Boolean> clinicalReception(@RequestParam String orderId) {
        return new R<>(medOfficeVisitService.clinicalReception(orderId));
    }

    /**
     * 医生端已完成问诊接口
     * @return
     */
    @GetMapping("/completeProfile")
    public R<List> completeProfile(String doctorId) {
        return new R<>(doctorOperationService.completeProfile(doctorId));
    }

    /**
     * 医生端药品查询接口
     * @param searchKey
     * @return
     */
    @GetMapping("/searchKey")
    public R<List> searchDrugInfo(String searchKey) {
        return new R<>(doctorOperationService.searchDrugInfo(searchKey));
    }
}
