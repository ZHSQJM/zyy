package com.kinglian.screeninquiry.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kinglian.screeninquiry.dao.TestMapper;
import com.kinglian.screeninquiry.model.entity.BaseDrugInfo;
import com.kinglian.screeninquiry.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HXC
 * @date 2019-1-16
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, BaseDrugInfo> implements TestService {

}
