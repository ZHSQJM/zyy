/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: VendorService
 * Author:   weiyz
 * Date:     2019/3/29 14:50
 * Description: VendorService
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.service;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.kinglian.screeninquiry.model.entity.Vendor;

import java.util.Map;

/**
 * 〈VendorService〉
 * @author weiyz
 * @create 2019/3/29
 * @since 1.0.0
 */
public interface VendorService extends IService<Vendor> {

    Page getVendors(Query<Map> mapQuery);
}
