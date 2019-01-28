/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: RegisterData
 * Author:   weiyz
 * Date:     2019/1/25 11:09
 * Description: RegisterData
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.model.entity;

/**
 * 〈RegisterData 〉
 * @author weiyz
 * @create 2019/1/25
 * @since 1.0.0
 */
@lombok.Data
public class RegisterData {

    private WeChatMessageTemplate first;//模板开始前描述
    private WeChatMessageTemplate keyword1;//关键词1
    private WeChatMessageTemplate keyword2;//关键词2
    private WeChatMessageTemplate keyword3;//关键词3
    private WeChatMessageTemplate remark;//模板结束描述

}
