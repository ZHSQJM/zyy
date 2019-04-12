package com.kinglian.screeninquiry.dao;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kinglian.screeninquiry.model.entity.Product;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * ProductMapper继承基类
 */
@Repository
public interface ProductMapper extends BaseMapper<Product> {

    @Select("select * from product where vendor_id = #{vendorId}")
    List<Map> getProductByVendorId(Query<Map> mapQuery, Map<String,Object> condition);
}