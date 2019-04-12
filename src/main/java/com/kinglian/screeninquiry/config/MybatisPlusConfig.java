package com.kinglian.screeninquiry.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @project: zblog
 * @author: zhs
 * @date: 2019/4/9 11:36
 * @package: com.kinglian.screeninquiry.config
 * @description:
 */
// 声明配置类
@Configuration
// 扫描mapper
@MapperScan({"com.kinglian.screeninquiry.dao"})
public class MybatisPlusConfig {

    //覆盖分页组件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        //声明方言类型
        page.setDialectType("mysql");
        return page;
    }
}
