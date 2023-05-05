package com.ysir.custom.controller;


import cn.hutool.core.util.ObjectUtil;
import com.ysir.custom.common.AjaxResult;
import com.ysir.custom.common.LoginUser;
import com.ysir.custom.common.PageDataInfo;
import com.ysir.custom.config.CustomConfig;
import com.ysir.custom.entity.TRole;
import com.ysir.custom.entity.TUser;
import com.ysir.custom.jwt.TokenService;
import com.ysir.custom.service.ITRoleService;
import com.ysir.custom.service.ITUserService;
import com.ysir.custom.util.ServerConfigUtil;
import com.ysir.custom.util.file.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息
 */
@RestController
@RequestMapping("/custom/user")
public class TUserController extends BaseController {
    @Autowired
    private ITUserService userService;

    @Autowired
    private ITRoleService roleService;

    @Autowired
    private TokenService tokenService;


    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    public PageDataInfo list(TUser user) {
        startPage();
        List<TUser> list = userService.selectUserList(user);
        return getPageData(list);
    }


    /**
     * 根据用户编号获取详细信息
     */
    @GetMapping(value = {"/", "/{userId}"})
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId) {
        AjaxResult ajax = AjaxResult.success();
        List<TRole> roles = roleService.selectRoleAll();
        ajax.put("roles", roles);
        if (ObjectUtil.isNotNull(userId)) {
            TUser tUser = userService.selectUserById(userId);
            ajax.put(AjaxResult.DATA_TAG, tUser);
            ajax.put("roleIds", tUser.getRoles().stream().map(TRole::getRoleId).collect(Collectors.toList()));
        }
        return ajax;
    }

    /**
     * 新增用户
     */
    @PostMapping
    public AjaxResult add(@Validated @RequestBody TUser user) {
        TUser tUser = userService.selectUserByUserName(user.getUserName());
        if (ObjectUtil.isNotEmpty(tUser)) {
            return error("用户账号已存在，添加失败！");
        }
        user.setCreateBy(getUsername());
        user.setPassword(ServerConfigUtil.encryptPassword(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody TUser user) {
        user.setUpdateBy(getUsername());
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds) {
        return toAjax(userService.deleteUserByIds(userIds));
    }

    /**
     * 重置密码
     */
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody TUser user) {
        user.setPassword(ServerConfigUtil.encryptPassword(user.getPassword()));
        user.setUpdateBy(getUsername());
        return toAjax(userService.resetPwd(user));
    }


    /**
     * 根据用户编号获取授权角色
     */
    @GetMapping("/authRole/{userId}")
    public AjaxResult authRole(@PathVariable("userId") Long userId) {
        AjaxResult ajax = AjaxResult.success();
        TUser user = userService.selectUserById(userId);
        List<TRole> roles = roleService.selectRolesByUserId(userId);
        ajax.put("user", user);
        ajax.put("roles", roles);
        return ajax;
    }

    /**
     * 用户授权角色
     */
    @PutMapping("/authRole")
    public AjaxResult insertAuthRole(Long userId, Long[] roleIds) {
        userService.insertUserAuth(userId, roleIds);
        return success();
    }

    /**
     * 个人信息
     */
    @GetMapping("/profile")
    public AjaxResult profile() {
        LoginUser loginUser = getLoginUser();
        TUser user = loginUser.getUser();
        AjaxResult ajax = AjaxResult.success(user);
        ajax.put("roleGroup", userService.selectUserRoleGroup(loginUser.getUsername()));
        return ajax;
    }

    /**
     * 修改用户
     */
    @PutMapping("/profile")
    public AjaxResult updateProfile(@RequestBody TUser user) {
        LoginUser loginUser = getLoginUser();
        TUser tUser = loginUser.getUser();
        user.setUserName(tUser.getUserName());


        user.setUserId(tUser.getUserId());
        user.setPassword(null);
        user.setAvatar(null);
        if (userService.updateUserProfile(user) > 0) {
            // 更新缓存用户信息
            tUser.setNickName(user.getNickName());
            tUser.setPhonenumber(user.getPhonenumber());
            tUser.setEmail(user.getEmail());
            tUser.setSex(user.getSex());
            tokenService.setLoginUser(loginUser);
            return success();
        }
        return error("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     */
    @PutMapping("/profile/updatePwd")
    public AjaxResult updatePwd(String oldPassword, String newPassword) {
        LoginUser loginUser = getLoginUser();
        String userName = loginUser.getUsername();
        String password = loginUser.getPassword();

        if (userService.resetUserPwd(userName, ServerConfigUtil.encryptPassword(newPassword)) > 0) {
            // 更新缓存用户密码
            loginUser.getUser().setPassword(ServerConfigUtil.encryptPassword(newPassword));
            tokenService.setLoginUser(loginUser);
            return success();
        }
        return error("修改密码异常，请联系管理员");
    }

    /**
     * 头像上传
     */
    @PostMapping("/profile/avatar")
    public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            LoginUser loginUser = getLoginUser();
            String avatar = FileUploadUtils.upload(CustomConfig.getAvatarPath(), file);
            if (userService.updateUserAvatar(loginUser.getUsername(), avatar)) {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("imgUrl", avatar);
                // 更新缓存用户头像
                loginUser.getUser().setAvatar(avatar);
                tokenService.setLoginUser(loginUser);
                return ajax;
            }
        }
        return error("上传图片异常，请联系管理员");
    }
}
