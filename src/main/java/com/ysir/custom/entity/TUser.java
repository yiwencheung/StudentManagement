package com.ysir.custom.entity;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ysir.custom.common.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    private Long id;


    /**
     * 用户账号
     */
    private String userName;

    private String name;
    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phonenumber;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private Date loginDate;

    /**
     * 角色对象
     */
    private List<TRole> roles;

    /**
     * 角色组
     */
    private Long[] roleIds;

    /**
     * 角色ID
     */
    private Long roleId;

    public TUser(Long id) {
        userId = id;
    }
    public TUser() {
    }
    /**
     * 是否为管理员
     *
     * @return 结果
     */
    public boolean isAdmin() {
        return (ObjectUtil.isNotEmpty(userId) && userId == 1 || (StrUtil.isNotBlank(userName) && userName.equals("admin")) );
    }

    public Long getId() {
        return userId;
    }

    public String getName() {
        return nickName;
    }
}
