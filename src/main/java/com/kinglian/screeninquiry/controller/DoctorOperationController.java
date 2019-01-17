package com.kinglian.screeninquiry.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.kinglian.screeninquiry.service.UserService;
import com.kinglian.screeninquiry.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author HXC
 * @date 2019-1-16
 */
@RestController
@RequestMapping("/screenInquiry/doctor")
public class DoctorOperationController {

    @Autowired
    private UserService userService;

    /**
     * 医生端登录接口
     * @param user
     * @param password
     * @return
     */
    @PostMapping("/login")
    public R<Boolean> login(String user, String password) {
        return new R<Boolean>(userService.login(user, password));
    }
}
