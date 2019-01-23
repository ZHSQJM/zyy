package com.kinglian.screeninquiry.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kinglian.screeninquiry.model.entity.User;

/**
 * UserMapper继承基类
 */
public interface UserMapper extends BaseMapper<User> {
    void selectCount();
}
