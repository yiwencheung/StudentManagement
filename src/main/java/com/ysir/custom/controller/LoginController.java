package com.ysir.custom.controller;


import cn.hutool.core.util.StrUtil;
import com.ysir.custom.common.AjaxResult;
import com.ysir.custom.common.LoginBody;
import com.ysir.custom.constant.GlobalConstants;
import com.ysir.custom.entity.TMenu;
import com.ysir.custom.entity.TUser;
import com.ysir.custom.service.ISysMenuService;
import com.ysir.custom.service.impl.SysLoginService;
import com.ysir.custom.service.impl.SysPermissionService;
import com.ysir.custom.util.ServerConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

import static com.ysir.custom.common.AjaxResult.error;
import static com.ysir.custom.common.AjaxResult.success;

/**
 * 登录验证
 */
@RestController
public class LoginController {

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword());
        ajax.put(GlobalConstants.TOKEN, token);
        return ajax;
    }

    @PostMapping("/register")
    public AjaxResult register(@RequestBody LoginBody user) {

        String msg = loginService.register(user);
        return StrUtil.isBlank(msg) ? success() : error(msg);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        TUser user = ServerConfigUtil.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters() {
        Long userId = ServerConfigUtil.getUserId();
        List<TMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return success(menuService.buildMenus(menus));
    }
}
