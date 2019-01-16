package com.kinglian.screeninquiry.controller;

import cn.kinglian.spring.util.R;
import com.kinglian.screeninquiry.model.entity.BaseDrugInfo;
import com.kinglian.screeninquiry.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HXC
 * @date 2019-1-16
 */
@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/aaa")
    public R<Map> Test() {
        BaseDrugInfo baseDrugInfo = testService.selectById("120");
        System.out.println(baseDrugInfo);
        Map result = new HashMap();
        result.put("a", "sss");
        return new R<>(result);
    }
}
