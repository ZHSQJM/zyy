package com.kinglian.screeninquiry.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author HXC
 * @date 2019-2-22
 */
@Data
public class UserInfoDto {

    /**
     * 用户id
     */
    private String id = "";

    /**
     *用户姓名
     */
    private String name = "";

    /**
     * 用户性别
     */
    private int sex = 0;

    /**
     * 生日日期
     */
    private Date birthDay;

    /**
     * 用户年龄
     */
    private int age = 0;

    /**
     * 是否为新用户
     */
    private int newUser = 0;
}
