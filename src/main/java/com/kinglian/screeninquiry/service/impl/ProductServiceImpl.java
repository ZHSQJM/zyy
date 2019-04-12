/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: ProductServiceImpl
 * Author:   weiyz
 * Date:     2019/3/29 15:03
 * Description: ProductServiceImpl
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.service.impl;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kinglian.screeninquiry.dao.ProductMapper;
import com.kinglian.screeninquiry.model.entity.Product;
import com.kinglian.screeninquiry.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 〈ProductServiceImpl〉
 * @author weiyz
 * @create 2019/3/29
 * @since 1.0.0
 */
@Service
@Transactional
public class ProductServiceImpl extends ServiceImpl<ProductMapper,Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Override
    public Page getProductByVendorId(Query<Map> mapQuery) {
        return mapQuery.setRecords(productMapper.getProductByVendorId(mapQuery,mapQuery.getCondition()));
    }
}
