/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: LoginException
 * Author:   weiyz
 * Date:     2019/4/3 14:21
 * Description: LoginException
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.config;

/**
 * 〈LoginException〉
 * @author weiyz
 * @create 2019/4/3
 * @since 1.0.0
 */
public class LoginException extends Exception{

    public LoginException(){

    }
    public LoginException(String msg){
        super(msg);
    }
}
