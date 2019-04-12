package com.kinglian.screeninquiry.service;

import com.kinglian.screeninquiry.service.impl.SysUserServiceImpl;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @project: zblog
 * @author: zhs
 * @date: 2019/4/11 14:54
 * @package: com.kinglian.screeninquiry.service
 * @description:
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class SysUserServiceTest {


    @Autowired
    private SysUserServiceImpl userService;


}