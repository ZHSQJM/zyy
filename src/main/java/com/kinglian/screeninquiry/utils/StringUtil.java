/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: StringUtil
 * Author:   weiyz
 * Date:     2019/1/23 17:04
 * Description: StringUtil
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.utils;

/**
 * 〈StringUtil〉
 * @author weiyz
 * @create 2019/1/23
 * @since 1.0.0
 * StringUtil
 * @description: 字符串工具类
 **/
public class StringUtil {

    /**
     * 判断是否为空字符串最优代码
     * @param str
     * @return 如果为空，则返回true
     */
    public static boolean isEmptyString(String str){
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断字符串是否非空
     * @param str 如果不为空，则返回true
     * @return
     */
    public static boolean isNotEmpty(String str){
        return !isEmptyString(str);
    }
}
