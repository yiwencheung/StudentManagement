//package com.ysir.custom.service.impl;
//
//
//import com.ysir.custom.entity.SysUser;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
///**
// * 登录密码方法
// *
// */
//@Component
//public class SysPasswordService {
//
//    public boolean validate(SysUser user) {
//        Authentication usernamePasswordAuthenticationToken =  SecurityContextHolder.getContext().getAuthentication();
//        String password = usernamePasswordAuthenticationToken.getCredentials().toString();
//
//        if (!matches(user, password)) {
//            return false;
//        }
//        return true;
//    }
//
//    public boolean matches(SysUser user, String rawPassword) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder.matches(rawPassword, user.getPassword());
//    }
//
//}
