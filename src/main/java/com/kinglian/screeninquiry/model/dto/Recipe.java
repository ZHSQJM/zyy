/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: Recipe
 * Author:   weiyz
 * Date:     2019/1/17 17:38
 * Description: 处方笺
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * 〈处方笺〉
 * @author weiyz
 * @create 2019/1/17
 * @since 1.0.0
 */
@Data
public class Recipe {

    private String sheetid;
    private  String userName;
    private String auditNote;
    private Integer auditStatus;
    private Date auditDate;
}
