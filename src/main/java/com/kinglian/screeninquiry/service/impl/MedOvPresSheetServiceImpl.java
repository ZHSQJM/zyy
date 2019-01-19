/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: MedOvPresSheetServiceImpl
 * Author:   weiyz
 * Date:     2019/1/16 19:46
 * Description: MedOvPresSheetService实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kinglian.screeninquiry.dao.MedOvPresSheetMapper;
import com.kinglian.screeninquiry.model.entity.MedOvPresSheet;
import com.kinglian.screeninquiry.service.MedOvPresSheetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 〈MedOvPresSheetService实现类〉
 * @author weiyz
 * @create 2019/1/16
 * @since 1.0.0
 */
@Service
@Transactional
public class MedOvPresSheetServiceImpl extends ServiceImpl<MedOvPresSheetMapper,MedOvPresSheet> implements MedOvPresSheetService {

}
