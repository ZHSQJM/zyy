package com.kinglian.screeninquiry.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kinglian.screeninquiry.model.entity.Complain;
import org.springframework.stereotype.Repository;

/**
 * ComplainMapper继承基类
 */
@Repository
public interface ComplainMapper extends BaseMapper<Complain> {
}