/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: OrderServiceImpl
 * Author:   weiyz
 * Date:     2019/3/25 13:57
 * Description: OrderServiceImpl
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.service.impl;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kinglian.screeninquiry.dao.OrderMapper;
import com.kinglian.screeninquiry.model.entity.Order;
import com.kinglian.screeninquiry.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 〈OrderServiceImpl〉
 * @author weiyz
 * @create 2019/3/25
 * @since 1.0.0
 */
@Service
@Transactional
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public Page getOrders(Query<Map> mapQuery) {
        return mapQuery.setRecords(orderMapper.getOrders(mapQuery,mapQuery.getCondition()));
    }

    @Override
    public Page getOrderUnFinished(Query<Map> mapQuery) {
        return mapQuery.setRecords(orderMapper.getOrderUnFinished(mapQuery,mapQuery.getCondition()));
    }

    @Override
    public void updateOpenId(String openId,String productId) {
        orderMapper.updateOpenId(openId,productId);
    }

    @Override
    public Order getOrderById(String orderId) {
        return orderMapper.getOrderById(orderId);
    }
}
