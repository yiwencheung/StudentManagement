package com.ysir.custom.common;

import lombok.Data;

/**
 * 用户登录对象
 *
 */
@Data
public class LoginBody {


    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

}
