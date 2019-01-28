/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: WeChatMessageTemplate
 * Author:   weiyz
 * Date:     2019/1/23 17:22
 * Description: WeChat message template
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈WeChat message template〉
 * @author weiyz
 * @create 2019/1/23
 * @since 1.0.0
 */
@Data
@NoArgsConstructor  //空参构造函数
@AllArgsConstructor //全参构造函数
public class WeChatMessageTemplate {

    private String value;	//内容
    private String color;	//字体颜色


}
