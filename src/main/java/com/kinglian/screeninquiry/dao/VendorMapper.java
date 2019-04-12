package com.kinglian.screeninquiry.dao;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kinglian.screeninquiry.model.entity.Vendor;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VendorMapper extends BaseMapper<Vendor> {

    @Select("select * from `vendor`")
    List<Map> getVendors(Query<Map> mapQuery, Map<String,Object> condition);
}