package com.kinglian.screeninquiry.model.Vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @project: zblog
 * @author: zhs
 * @date: 2019/4/9 11:27
 * @package: com.kinglian.screeninquiry.model.Vo
 * @description: 商家列表页
 */
@Data
@Accessors(chain = true)
public class GroupVo {

    /**
     * 主键id
     */
    private String id;

    /**
     * 群名
     */
    private String groupName;


    /**
     * 简介
     */
    private String briefIntroduction;

    /**openId*/
    private String openId;

}
