/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: GroupServiceImpl
 * Author:   weiyz
 * Date:     2019/3/29 16:17
 * Description: GroupServiceImpl
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kinglian.screeninquiry.dao.GroupMapper;
import com.kinglian.screeninquiry.model.entity.Group;
import com.kinglian.screeninquiry.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 〈GroupServiceImpl〉
 * @author weiyz
 * @create 2019/3/29
 * @since 1.0.0
 */
@Service
@Transactional
public class GroupServiceImpl extends ServiceImpl<GroupMapper,Group> implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public boolean update(Group group) {
        groupMapper.updateByPrimaryKey(group);
        return true;
    }
}
