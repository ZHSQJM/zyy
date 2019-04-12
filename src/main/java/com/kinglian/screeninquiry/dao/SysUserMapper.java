/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: SysUserMapper
 * Author:   weiyz
 * Date:     2019/4/3 14:11
 * Description: SysUserMapper
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kinglian.screeninquiry.model.Vo.UserVO;
import com.kinglian.screeninquiry.model.Vo.UserVo1;
import com.kinglian.screeninquiry.model.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 〈SysUserMapper〉
 * @author weiyz
 * @create 2019/4/3
 * @since 1.0.0
 */
@Repository
public interface SysUserMapper extends BaseMapper<User> {

    @Select("select * from sys_user where username = #{userAccount}")
    UserVO selectUserVoByUsername(String userAccount);

    @Select("select * from user where open_id = #{openId}")
    UserVo1 getUserByOpenId( String openId);
}
