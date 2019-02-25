package com.kinglian.screeninquiry;

import com.kinglian.screeninquiry.controller.WeChatController;
import com.kinglian.screeninquiry.utils.Constant;
import com.kinglian.screeninquiry.utils.CreateParmsCode;
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
    CreateParmsCode createParmsCode;

    @Autowired
    Constant constant;

    @Autowired
    WeChatController weChatController;

    @Test
    public void contextLoads() {
        String result = stringEncryptor.encrypt("Jinglian12345");
        System.out.println("==================");
        System.out.println(result);
        System.out.println("==================");

    }

    @Test
    public void test1() {
        System.out.println("========");
        System.out.println(createParmsCode.getParmsCode("154892505913f509b4afa4394c2dadf9"));
    }

    @Test
    public void test2() {
        String accessToken = createParmsCode.getAccessToken();
        System.out.println(accessToken);
        System.out.println("========");
//        System.out.println(createParmsCode.getOpenId());
    }

/*    @Test
    public void test3() {
        weChatController.getAuthUrl("yun.kinglian.cn/officialWeChat/myCase");
    }*/

}

