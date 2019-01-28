/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: TemplateMessage
 * Author:   weiyz
 * Date:     2019/1/25 11:45
 * Description: TemplateMessage
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 〈TemplateMessage〉
 * @author weiyz
 * @create 2019/1/25
 * @since 1.0.0
 */
@lombok.Data
@NoArgsConstructor  //空参构造函数
@AllArgsConstructor //全参构造函数
public class TemplateMessage {

    private String touser; //用户openid
    private String template_id; //模板消息ID
    private String url; //详情跳转页面
    private RegisterData data;//模板数据封装实体
}
