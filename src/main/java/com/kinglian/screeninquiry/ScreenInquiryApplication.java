package com.kinglian.screeninquiry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.kinglian.screeninquiry.dao")
public class ScreenInquiryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScreenInquiryApplication.class, args);
    }

}

