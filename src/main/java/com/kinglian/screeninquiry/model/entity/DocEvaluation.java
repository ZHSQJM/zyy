package com.kinglian.screeninquiry.model.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * doc_evaluation
 * @author
 */
@Data
public class DocEvaluation implements Serializable {

    /*@TableField(value = "id",type = IdType.UUID)
    private String id;*/

    /**
     * 问诊id
     */
    @TableId
    private String visitId;

    /**
     * 医生id
     */
    private String doctorId;

    /**
     * 评价（0:好评，1:中评，2:差评）
     */
    private String evaluate;

    /**
     * 评价内容
     */
    private String evaluationContent;

    /**
     * 评价人id
     */
    private String appraiserId;

    private static final long serialVersionUID = 1L;

}
