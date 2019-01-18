package com.kinglian.screeninquiry.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.kinglian.screeninquiry.service.DoctorOperationService;
import com.kinglian.screeninquiry.service.UserService;
import com.kinglian.screeninquiry.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
}
