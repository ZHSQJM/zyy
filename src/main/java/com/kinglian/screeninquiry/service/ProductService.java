/**
 * Copyright (C), 2018-2019, kinglian
 * FileName: ProductService
 * Author:   weiyz
 * Date:     2019/3/29 15:03
 * Description: ProductService
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.service;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.kinglian.screeninquiry.model.entity.Product;

import java.util.Map;

/**
 * 〈ProductService〉
 *
 * @author weiyz
 * @create 2019/3/29
 * @since 1.0.0
 */
public interface ProductService extends IService<Product> {

    Page getProductByVendorId(Query<Map> mapQuery);
}