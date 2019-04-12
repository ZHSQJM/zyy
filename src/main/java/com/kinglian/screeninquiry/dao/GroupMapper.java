package com.kinglian.screeninquiry.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kinglian.screeninquiry.model.entity.Group;

/**
 * GroupMapper继承基类
 */
public interface GroupMapper extends BaseMapper<Group> {


    int updateByPrimaryKey(Group group);

}