/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: SysUserServiceImpl
 * Author:   weiyz
 * Date:     2019/4/3 14:10
 * Description: SysUserServiceImpl
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.service.impl;

import cn.kinglian.spring.config.LoginException;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kinglian.screeninquiry.dao.SysUserMapper;
import com.kinglian.screeninquiry.model.Vo.UserVO;
import com.kinglian.screeninquiry.model.Vo.UserVo1;
import com.kinglian.screeninquiry.model.entity.User;
import com.kinglian.screeninquiry.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 〈SysUserServiceImpl〉
 * @author weiyz
 * @create 2019/4/3
 * @since 1.0.0
 */
@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,User> implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public UserVO selectByUserAccountAndPassword(String userAccount, String password) throws Exception{
        UserVO userVo = userMapper.selectUserVoByUsername(userAccount);

        if (userVo == null ) {
            throw new LoginException("用户账号不存在");
        }
        if (!userVo.getPassword().equals(password)) {

            throw new LoginException("用户名或者密码错误");
        }
//        String token = UUID.randomUUID().toString();
//        userVo.setToken(token);
//        redisUtil.setKeyAndValue(token, userVo.getUserId());

        return userVo;
    }

    @Override
    public UserVo1 getUserByOpenId(String openId) {
        return userMapper.getUserByOpenId(openId);
    }
}
