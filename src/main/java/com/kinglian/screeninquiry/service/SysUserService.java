/**
 * Copyright (C), 2018-2019, kinglian
 * FileName: SysUserService
 * Author:   weiyz
 * Date:     2019/4/3 14:09
 * Description: SysUserService
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.service;

import com.baomidou.mybatisplus.service.IService;
import com.kinglian.screeninquiry.model.Vo.UserVO;
import com.kinglian.screeninquiry.model.Vo.UserVo1;
import com.kinglian.screeninquiry.model.entity.User;

/**
 * 〈SysUserService〉
 *
 * @author weiyz
 * @create 2019/4/3
 * @since 1.0.0
 */
public interface SysUserService extends IService<User> {

    UserVO selectByUserAccountAndPassword(String userAccount, String pass) throws Exception;

    UserVo1 getUserByOpenId(String openId);




}