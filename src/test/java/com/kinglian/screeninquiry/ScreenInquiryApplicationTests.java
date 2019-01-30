package com.kinglian.screeninquiry;

import com.kinglian.screeninquiry.utils.Constant;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScreenInquiryApplicationTests {


    @Autowired
    StringEncryptor stringEncryptor;
    @Autowired
    Constant constant;

    @Test
    public void contextLoads() {
        String result = stringEncryptor.encrypt("Jinglian12345");
        System.out.println("==================");
        System.out.println(result);
        System.out.println("==================");

    }

    @Test
    public void asstoken() {
        System.out.println("==================");
        System.out.println(constant.getAccessToken());
        System.out.println("==================");

    }

}

