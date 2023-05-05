package com.ysir.custom.controller;


import cn.hutool.core.util.ObjectUtil;
import com.ysir.custom.common.AjaxResult;
import com.ysir.custom.common.LoginUser;
import com.ysir.custom.common.PageDataInfo;
import com.ysir.custom.entity.TRole;
import com.ysir.custom.entity.TUser;
import com.ysir.custom.entity.TUserRole;
import com.ysir.custom.jwt.TokenService;
import com.ysir.custom.service.ITRoleService;
import com.ysir.custom.service.ITUserService;
import com.ysir.custom.service.impl.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色信息
 *
 */
@RestController
@RequestMapping("/custom/role")
public class TRoleController extends BaseController {

    @Autowired
    private ITRoleService roleService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private ITUserService userService;


    @GetMapping("/list")
    public PageDataInfo list(TRole role) {
        startPage();
        List<TRole> list = roleService.selectRoleList(role);
        return getPageData(list);
    }



    /**
     * 根据角色编号获取详细信息
     */
    @GetMapping(value = "/{roleId}")
    public AjaxResult getInfo(@PathVariable Long roleId) {
        return success(roleService.selectRoleById(roleId));
    }

    /**
     * 新增角色
     */
    @PostMapping
    public AjaxResult add(@Validated @RequestBody TRole role) {
        if (!roleService.checkRoleNameUnique(role)) {
            return error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (!roleService.checkRoleKeyUnique(role)) {
            return error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setCreateBy(getUsername());
        return toAjax(roleService.insertRole(role));

    }

    /**
     * 修改保存角色
     */
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody TRole role) {
        if (!roleService.checkRoleNameUnique(role)) {
            return error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (!roleService.checkRoleKeyUnique(role)) {
            return error("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setUpdateBy(getUsername());

        if (roleService.updateRole(role) > 0) {
            // 更新缓存用户权限
            LoginUser loginUser = getLoginUser();
            if (ObjectUtil.isNotNull(loginUser.getUser()) && !loginUser.getUser().isAdmin()) {
                loginUser.setPermissions(permissionService.getMenuPermission(loginUser.getUser()));
                loginUser.setUser(userService.selectUserByUserName(loginUser.getUser().getUserName()));
                tokenService.setLoginUser(loginUser);
            }
            return success();
        }
        return error("修改角色'" + role.getRoleName() + "'失败，请联系管理员");
    }

    /**
     * 修改保存数据权限
     */
    @PutMapping("/dataScope")
    public AjaxResult dataScope(@RequestBody TRole role) {
        return toAjax(roleService.authDataScope(role));
    }

    /**
     * 状态修改
     */
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody TRole role) {
        role.setUpdateBy(getUsername());
        return toAjax(roleService.updateRoleStatus(role));
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/{roleIds}")
    public AjaxResult remove(@PathVariable Long[] roleIds) {
        return toAjax(roleService.deleteRoleByIds(roleIds));
    }

    /**
     * 获取角色选择框列表
     */
    @GetMapping("/optionselect")
    public AjaxResult optionselect() {
        return success(roleService.selectRoleAll());
    }

    /**
     * 查询已分配用户角色列表
     */
    @GetMapping("/authUser/allocatedList")
    public PageDataInfo allocatedList(TUser user) {
        startPage();
        List<TUser> list = userService.selectAllocatedList(user);
        return getPageData(list);
    }

    /**
     * 查询未分配用户角色列表
     */
    @GetMapping("/authUser/unallocatedList")
    public PageDataInfo unallocatedList(TUser user) {
        startPage();
        List<TUser> list = userService.selectUnallocatedList(user);
        return getPageData(list);
    }

    /**
     * 取消授权用户
     */
    @PutMapping("/authUser/cancel")
    public AjaxResult cancelAuthUser(@RequestBody TUserRole userRole) {
        return toAjax(roleService.deleteAuthUser(userRole));
    }

    /**
     * 批量取消授权用户
     */
    @PutMapping("/authUser/cancelAll")
    public AjaxResult cancelAuthUserAll(Long roleId, Long[] userIds) {
        return toAjax(roleService.deleteAuthUsers(roleId, userIds));
    }

    /**
     * 批量选择用户授权
     */
    @PutMapping("/authUser/selectAll")
    public AjaxResult selectAuthUserAll(Long roleId, Long[] userIds) {
        return toAjax(roleService.insertAuthUsers(roleId, userIds));
    }

}
