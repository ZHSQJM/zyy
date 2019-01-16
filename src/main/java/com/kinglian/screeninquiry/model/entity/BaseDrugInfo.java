package com.kinglian.screeninquiry.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author chenjj
 */
@Data

public class BaseDrugInfo implements Serializable {

    private String id;

    /**
     * 来源id--从外面导入时保留原来的id
     */
    private String sourceId;

    /**
     * 药品代码
     */
    private String code;

    /**
     * 药品名称
     */
    private String name;

    /**
     * 药品别名
     */
    private String nameAlias;

    /**
     * 药品简写代码
     */
    private String spellCode;

    /**
     * 批准文号
     */
    private String approvalNumber;

    /**
     * 药品包装（0.25g*10粒*2板）
     */
    private String pack;

    /**
     * 用法
     */
    private String usage;

    /**
     * 用量
     */
    private String dosage;

    /**
     * 包装单位
     */
    private String packageUnit;

    /**
     * 价格
     */
    private BigDecimal retailPrice;

    /**
     * 基本数量
     */
    private Integer baseDosage;

    /**
     * 药房id
     */
    private String pharmacyId;

    /**
     * 最大库存
     */
    private Long stockUpLimit;

    /**
     * 预警库存
     */
    private Long stockLowLimit;

    /**
     * 创建时间
     */
    private String createdDate;

    private String createdBy;

    private String updatedDate;

    private String updatedBy;

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 是否必须院内取药，0:不是，1:是
     */
    private Integer mustInHospital;

    /**
     * 有效期
     */
    private String usefulLife;

    /**
     * 是否为处方药
     */
    private Integer isRecipe;

    /**
     * 药品类型代码，p:西药，c:中草药，z:中成药，y:疫苗s:材料，x:其他，必填
     */
    private String drugTypeCode;

    /**
     * 医保类别，1:甲类，2:乙类，3:自费
     */
    private Integer ybType;

    /**
     * 贮藏方法
     */
    private String storeMethod;

    /**
     * 注意事项
     */
    private String attention;

    /**
     * 商品条码
     */
    private String barCode;

    /**
     * 商品条码图片保存路径
     */
    private String barCodeImg;

    /**
     * 禁忌
     */
    private String taboo;

    /**
     * 使用说明
     */
    private String instruction;

    public  String manufacturer;
    private  String attendingFun;

    private  String reaction;

    private static final long serialVersionUID = 1L;
}
