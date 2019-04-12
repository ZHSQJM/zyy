/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: VendorServiceImpl
 * Author:   weiyz
 * Date:     2019/3/29 14:51
 * Description: VendorServiceImpl
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.service.impl;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kinglian.screeninquiry.dao.VendorMapper;
import com.kinglian.screeninquiry.model.entity.Vendor;
import com.kinglian.screeninquiry.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 〈VendorServiceImpl〉
 * @author weiyz
 * @create 2019/3/29
 * @since 1.0.0
 */
@Service
@Transactional
public class VendorServiceImpl extends ServiceImpl<VendorMapper,Vendor> implements VendorService {

    @Autowired
    private VendorMapper vendorMapper;
    @Override
    public Page getVendors(Query<Map> mapQuery) {
        mapQuery.getPages();
        return mapQuery.setRecords(vendorMapper.getVendors(mapQuery,mapQuery.getCondition()));
    }
}
