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
