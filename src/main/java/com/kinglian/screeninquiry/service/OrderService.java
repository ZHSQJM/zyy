/**
 * Copyright (C), 2018-2019, kinglian
 * FileName: OrderService
 * Author:   weiyz
 * Date:     2019/3/25 13:56
 * Description: OrderService
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.service;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.kinglian.screeninquiry.model.entity.Order;

import java.util.Map;

/**
 * 〈OrderService〉
 *
 * @author weiyz
 * @create 2019/3/25
 * @since 1.0.0
 */
public interface OrderService extends IService<Order> {

    Page getOrders(Query<Map> mapQuery);

    Page getOrderUnFinished(Query<Map> mapQuery);

    void updateOpenId(String openId,String productId);

    Order getOrderById(String orderId);
}