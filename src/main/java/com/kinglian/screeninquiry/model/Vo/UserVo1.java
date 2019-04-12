/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: UserVo1
 * Author:   weiyz
 * Date:     2019/4/3 17:01
 * Description: UserVo1
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.model.Vo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 〈UserVo1〉
 * @author weiyz
 * @create 2019/4/3
 * @since 1.0.0
 */
@Data
public class UserVo1 implements Serializable {

    /**
     * ID
     */
    @TableId(value = "id",type = IdType.UUID)
    private String id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    private Boolean gender;


    @JsonProperty(value = "openId")
    private String open_id;

    //private String open_id;
}
