package com.ysir.custom.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ysir.custom.common.LoginUser;
import com.ysir.custom.entity.TUser;
import com.ysir.custom.service.ITUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ITUserService userService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TUser user = userService.selectUserByUserName(username);
        if (ObjectUtil.isNull(user)) {
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(TUser user) {
        return new LoginUser(user.getUserId(), user, permissionService.getMenuPermission(user));
    }
}
