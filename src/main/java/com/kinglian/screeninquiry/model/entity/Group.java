package com.kinglian.screeninquiry.model.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * group
 * @author 
 */
@Data
public class Group implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id",type = IdType.UUID)
    private String id;

    /**
     * 群名
     */
    private String groupName;

    /** 一分钟价格*/
    private String oneMin;

    /** 5分钟价格*/
    private String fiveMin;

    /** 10分钟价格*/
    private String tenMin;

    /** 15分钟价格*/
    private String fifteenMin;

    /** 20分钟价格*/
    private String twentyMin;

    /** 25分钟价格*/
    private String twentyFiveMin;

    /**
     * 简介
     */
    private String briefIntroduction;

    /**openId*/
    private String openId;

    private String photo;

    private static final long serialVersionUID = 1L;

}