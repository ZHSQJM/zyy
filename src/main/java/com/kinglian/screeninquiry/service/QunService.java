/**
 * Copyright (C), 2018-2019, kinglian
 * FileName: GroupService
 * Author:   weiyz
 * Date:     2019/3/29 16:16
 * Description: GroupService
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.service;

import com.baomidou.mybatisplus.service.IService;
import com.kinglian.screeninquiry.model.entity.Qun;

/**
 * 〈GroupService〉
 *
 * @author weiyz
 * @create 2019/3/29
 * @since 1.0.0
 */
public interface QunService extends IService<Qun> {


   boolean update(Qun qun);
}