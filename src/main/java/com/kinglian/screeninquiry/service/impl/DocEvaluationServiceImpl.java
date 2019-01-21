/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: DocEvaluationServiceImpl
 * Author:   weiyz
 * Date:     2019/1/21 13:46
 * Description: DocEvaluationServiceImpl
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kinglian.screeninquiry.dao.DocEvaluationMapper;
import com.kinglian.screeninquiry.model.entity.DocEvaluation;
import com.kinglian.screeninquiry.service.DocEvaluationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 〈DocEvaluationServiceImpl〉
 * @author weiyz
 * @create 2019/1/21
 * @since 1.0.0
 */
@Service
@Transactional
public class DocEvaluationServiceImpl extends ServiceImpl<DocEvaluationMapper,DocEvaluation> implements DocEvaluationService {

}
