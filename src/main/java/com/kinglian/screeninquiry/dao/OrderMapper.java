package com.kinglian.screeninquiry.dao;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kinglian.screeninquiry.model.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * OrderMapper继承基类
 */
public interface OrderMapper extends BaseMapper<Order> {

    @Select("select * from `order` WHERE open_id= #{openId}")
    List<Map> getOrders(Query<Map> mapQuery, Map<String,Object> condition);

    @Select("select * from `order` WHERE open_id= #{openId} and state = #{state}")
    List<Map> getOrderUnFinished(Query<Map> mapQuery, Map<String,Object> condition);

    @Update("update `order` set open_id = #{openId} where product_id = #{productId}")
    void updateOpenId( @Param("openId") String openId,@Param("productId") String productId);

    @Select("select * from order where order_id = #{orderId}")
    Order getOrderById(@Param("orderId")String orderId);
}