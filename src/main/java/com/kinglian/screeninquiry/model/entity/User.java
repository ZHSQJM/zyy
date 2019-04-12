package com.kinglian.screeninquiry.model.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {

    /**
     * ID
     */
    @TableId(value = "id",type = IdType.UUID)
    private String id;

    /**
     * 组别ID
     */
    private Integer groupId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 等级
     */
    private Boolean level;

    /**
     * 性别
     */
    private String gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 格言
     */
    private String bio;

    /**
     * 余额
     */
    private BigDecimal money;

    /**
     * 积分
     */
    private Integer score;

    /**
     * 连续登录天数
     */
    private Integer successions;

    /**
     * 最大连续登录天数
     */
    private Integer maxsuccessions;

    /**
     * 上次登录时间
     */
    private Integer prevtime;

    /**
     * 登录时间
     */
    private Integer logintime;

    /**
     * 登录IP
     */
    private String loginip;

    /**
     * 失败次数
     */
    private Boolean loginfailure;

    /**
     * 加入IP
     */
    private String joinip;

    /**
     * 加入时间
     */
    private Integer jointime;

    /**
     * 创建时间
     */
    private Integer createtime;

    /**
     * 更新时间
     */
    private Integer updatetime;

    /**
     * Token
     */
    private String token;

    /**
     * 状态
     */
    private String status;

    /**
     * 验证
     */
    private String verification;

    private String openId;

    private static final long serialVersionUID = 1L;
}