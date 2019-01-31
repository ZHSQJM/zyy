package com.kinglian.screeninquiry.model.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * visit_open
 * @author 
 */
@Data
public class VisitOpen implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.UUID)
    private String id;

    private String openId;

    /**
     * 问诊id
     */
    private String visitId;

    private String userId;

    private static final long serialVersionUID = 1L;

}