/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: BaseMessage
 * Author:   weiyz
 * Date:     2019/1/30 17:59
 * Description: 消息实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.model.dto;

import lombok.Data;

/**
 * 〈消息实体类〉
 * @author weiyz
 * @create 2019/1/30
 * @since 1.0.0
 */
@Data
public class BaseMessage {

    /**
     * 开发者微信号
     */
    public String ToUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    public String FromUserName;
    /**
     * 消息创建时间 （整型）
     */
    public long CreateTime;
    /**
     * text
     */
    public String MsgType ;

}
