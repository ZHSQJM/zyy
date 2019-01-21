/**
 * Copyright (C), 2018-2019, kinglian
 * FileName: UserService
 * Author:   weiyz
 * Date:     2019/1/16 19:47
 * Description: User业务接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.service;

import com.baomidou.mybatisplus.service.IService;
import com.kinglian.screeninquiry.model.entity.User;

/**
 * @author HXC
 * @date 2019-1-17
 */
public interface UserService extends IService<User> {
    /**
     * 判断该账号密码是否存在
     * @param user
     * @param password
     * @return
     */
    boolean login(String user, String password);
}
