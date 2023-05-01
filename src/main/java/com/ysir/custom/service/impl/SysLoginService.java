package com.ysir.custom.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ysir.custom.common.LoginBody;
import com.ysir.custom.common.LoginUser;
import com.ysir.custom.entity.SysRoleMenu;
import com.ysir.custom.entity.SysUser;
import com.ysir.custom.entity.SysUserRole;
import com.ysir.custom.jwt.TokenService;
import com.ysir.custom.mapper.SysRoleMenuMapper;
import com.ysir.custom.mapper.SysUserMapper;
import com.ysir.custom.mapper.SysUserRoleMapper;
import com.ysir.custom.util.ServerConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sun.misc.MessageUtils;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 登录校验方法
 */
@Component
public class SysLoginService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;



    @Resource
    private AuthenticationManager authenticationManager;


    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */
    public String login(String username, String password) {
        // 用户验证
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            throw new RuntimeException("用户名和密码不匹配");
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }


    /**
     * 注册
     */
    public String register(LoginBody registerBody) {
        String msg = "", username = registerBody.getUsername(), password = registerBody.getPassword();
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);

        if (StrUtil.isBlank(username)) {
            msg = "用户名不能为空";
        } else if (StrUtil.isBlank(password)) {
            msg = "用户密码不能为空";
        } else if (ObjectUtil.isNotEmpty(sysUserMapper.selectUserByUserName(username))) {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        }
        sysUser.setNickName(username);
        sysUser.setPassword(ServerConfigUtil.encryptPassword(password));
        int res = sysUserMapper.insertUser(sysUser);
        if (res <= 0) {
            msg = "注册失败,请联系系统管理人员";
        }
        //设置默认角色，角色ID为3
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(3L);
        sysUserRole.setUserId(sysUser.getUserId());
        sysUserRoleMapper.batchUserRole(Arrays.asList(sysUserRole));
        return msg;
    }
}