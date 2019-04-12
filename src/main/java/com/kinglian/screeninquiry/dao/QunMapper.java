package com.kinglian.screeninquiry.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kinglian.screeninquiry.model.entity.Qun;

/**
 * GroupMapper继承基类
 */
public interface QunMapper extends BaseMapper<Qun> {


    int updateByPrimaryKey(Qun qun);

}