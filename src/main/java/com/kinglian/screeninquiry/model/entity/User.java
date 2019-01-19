package com.kinglian.screeninquiry.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    private String userId;

    /**
     * 用户类型（1、病人  2、系统超级管理员  3、普通管理员  4、医生  5、主任医生  6、护士  7、护士长 8、院长与副院长9、平台管理员10、医院管理员  11专科医生，12全科医生，13，健康管理师）
     */
    private String userType;

    /**
     * 客户的名称
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 客户的帐号
     */
    private String userAccount;

    /**
     * 身份证号
     */
    private String sfzh;

    /**
     * 客户的密码
     */
    private String password;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 图像地址
     */
    private String imagePath;

    /**
     * 地址
     */
    private String address;

    /**
     * 帐号的状态
 0 表示帐号失效
 1 表示帐号可用
            
     */
    private String accountStatus;

    /**
     * 记录创建时间
     */
    private Date createTime;

    /**
     * 社区服务站ID
     */
    private String communityServiceStationId;

    /**
     * 上级的ID
     */
    private String parentId;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 级别编号(系统维护 超级管理员默认为000，每多一级加三位)
     */
    private String levelNo;

    /**
     * 健康档案ID
     */
    private String grjkdaId;

    /**
     * 医护人员ID
     */
    private String hcwId;

    /**
     * 所属医院ID（可为空）
     */
    private String hospitalId;

    /**
     * 所属平台ID（可为空）
     */
    private String platformId;

    /**
     * 性别：M男 F女
     */
    private String sex;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 推荐码
     */
    private String recommendCode;

    /**
     * 来源客户端客户端编码
     */
    private String sourceClientCode;

    /**
     * 来源客户端客户端名称
     */
    private String sourceClientName;

    /**
     * 用户会员卡号
     */
    private String memberCard;

    /**
     * 暗文密码（可逆）
     */
    private String cipherPassword;

    /**
     * 密码是否已经改过（1，已改过，2未改过）
     */
    private String editPassword;

    /**
     * 最后一次登录的app的所属系统：1:ios 2:android 3:wp
     */
    private String lastLoginOs;

    /**
     * 最后一次登录的app客户端的资源号，如lxj_u
     */
    private String lastLoginClient;

    /**
     * 最后一次登录的app客户端标志手机app的token，如IOS的deviceToken
     */
    private String lastLoginDeviceToken;

    /**
     * 用于安全校验的手机号
     */
    private String safePhone;

    /**
     * 是否要求强制安全校验，0否 1是
     */
    private Short safeVerify;

    /**
     * 个性签名
     */
    private String signture;

    /**
     * 地址坐标-经度,标准谷哥坐标 180-0-180
     */
    private Double mapLng;

    /**
     * 地址坐标-纬度,标准谷哥坐标 90-0-90
     */
    private Double mapLat;

    /**
     * 用户禁言状态：0否 1是
     */
    private Short talkForbidStatus;

    /**
     * 禁言时间
     */
    private Date talkForbidTime;

    /**
     * 记录用户最后一次登录的设备唯一id
     */
    private String deviceUniqueId;

    /**
     * 微信授权登录的UnionID(兼容其他第三方登录的唯一id)
     */
    private String wxUnionId;

    /**
     * 微信app授权openId
     */
    private String wxOpenIdApp;

    /**
     * 微信公众号授权openId
     */
    private String wxOpenIdGzh;

    /**
     * 微信绑定时间
     */
    private Date wxBindTime;

    /**
     * 微信绑定后的微信昵称
     */
    private String wxNickName;

    /**
     * 是否是测试用户（1是，0否）
     */
    private String isTestUser;

    private Integer seeMyArea;

    private Integer source;

    private static final long serialVersionUID = 1L;

}