/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: ComplainServiceImpl
 * Author:   weiyz
 * Date:     2019/3/29 15:46
 * Description: ComplainServiceImpl
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kinglian.screeninquiry.dao.ComplainMapper;
import com.kinglian.screeninquiry.model.entity.Complain;
import com.kinglian.screeninquiry.service.ComplainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 〈ComplainServiceImpl〉
 * @author weiyz
 * @create 2019/3/29
 * @since 1.0.0
 */
@Service
@Transactional
public class ComplainServiceImpl extends ServiceImpl<ComplainMapper,Complain> implements ComplainService {

}
