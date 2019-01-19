package com.kinglian.screeninquiry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan({"com.kinglian.screeninquiry.dao"})
@SpringBootApplication
public class ScreenInquiryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScreenInquiryApplication.class, args);
    }

}

