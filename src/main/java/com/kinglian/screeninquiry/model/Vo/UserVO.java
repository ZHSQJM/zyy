package com.kinglian.screeninquiry.model.Vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author
 * @desc
 **/
@Data
public class UserVO {

    private Integer userId;
    /**
     * 主键ID
     */
    private String token;

    /**
     * 用户名
     */
    private String username;

    private String password;

    private Date createTime;

    private Date updateTime;
    /**
     * 0-正常，1-删除
     */
    private String delFlag;

    /**
     * 简介
     */
    private String phone;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 角色列表
     */
    private List<RoleVo> roleList;

    private Integer roleId;

    /**
     * 0 普通用户 1 超级管理员
     */
    private String userFlag;

    private String nickName;
}
