package com.kinglian.screeninquiry;

import com.kinglian.screeninquiry.model.entity.BaseDrugInfo;
import com.kinglian.screeninquiry.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScreenInquiryApplicationTests {

    @Autowired
    private TestService testService;

    @Test
    public void contextLoads() {
        BaseDrugInfo baseDrugInfo = testService.selectById("120");
        System.out.println(baseDrugInfo);
    }

}

