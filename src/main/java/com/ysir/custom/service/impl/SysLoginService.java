package com.ysir.custom.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ysir.custom.common.LoginBody;
import com.ysir.custom.common.LoginUser;
import com.ysir.custom.entity.TRole;
import com.ysir.custom.entity.TUser;
import com.ysir.custom.entity.TUserRole;
import com.ysir.custom.jwt.TokenService;
import com.ysir.custom.mapper.SysRoleMapper;
import com.ysir.custom.mapper.SysUserMapper;
import com.ysir.custom.mapper.SysUserRoleMapper;
import com.ysir.custom.util.ServerConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

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

    @Autowired
    private SysRoleMapper sysRoleMapper;

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
        TUser tUser = new TUser();
        tUser.setUserName(username);

        if (StrUtil.isBlank(username)) {
            msg = "用户名不能为空";
        } else if (StrUtil.isBlank(password)) {
            msg = "用户密码不能为空";
        } else if (ObjectUtil.isNotEmpty(sysUserMapper.selectUserByUserName(username))) {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        }
        tUser.setNickName(username);
        tUser.setPassword(ServerConfigUtil.encryptPassword(password));
        int res = sysUserMapper.insertUser(tUser);
        if (res <= 0) {
            msg = "注册失败,请联系系统管理人员";
        }
        //设置默认角色
        TRole tRole = new TRole();
        tRole.setDefault(true);
        List<TRole> tRoles = sysRoleMapper.selectRoleList(tRole);
        if (CollUtil.isNotEmpty(tRoles)){
            TUserRole tUserRole = new TUserRole();
            tUserRole.setRoleId(tRoles.get(0).getRoleId());
            tUserRole.setUserId(tUser.getUserId());
            sysUserRoleMapper.batchUserRole(Arrays.asList(tUserRole));
        }
        return msg;
    }
}