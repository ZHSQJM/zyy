package com.kinglian.screeninquiry.model.Vo;


import com.kinglian.screeninquiry.model.entity.SysRole;
import lombok.Data;

/**
 * @author Zengfeng
 * @create 2018-06-04 14:06
 * @desc
 **/
@Data
public class RoleVo extends SysRole {

    /**
     * 角色部门Id
     */
    private Integer roleDeptId;

    /**
     * 部门名称
     */
    private String deptName;
}
