/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: UserServiceImpl
 * Author:   weiyz
 * Date:     2019/1/16 19:48
 * Description: User实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kinglian.screeninquiry.dao.UserMapper;
import com.kinglian.screeninquiry.model.entity.User;
import com.kinglian.screeninquiry.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 〈User实现类〉
 * @author weiyz
 * @create 2019/1/16
 * @since 1.0.0
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

}
