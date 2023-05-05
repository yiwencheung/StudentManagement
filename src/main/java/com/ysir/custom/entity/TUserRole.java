package com.ysir.custom.entity;


import lombok.Data;

/**
 * 用户和角色关联 sys_user_role
 */
@Data
public class TUserRole {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;


}
