/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: TextMessage
 * Author:   weiyz
 * Date:     2019/1/30 18:01
 * Description: 文本消息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.model.dto;

import lombok.Data;

/**
 * 〈文本消息〉
 * @author weiyz
 * @create 2019/1/30
 * @since 1.0.0
 */
@Data
public class TextMessage extends BaseMessage {

    /**
     * 文本消息内容
     */
    public String Content;
    /**
     * 消息id，64位整型
     */
    public long MsgId ;
}
