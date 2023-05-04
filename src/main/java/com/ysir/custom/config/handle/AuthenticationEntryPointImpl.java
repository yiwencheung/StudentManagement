package com.ysir.custom.config.handle;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.ysir.custom.common.AjaxResult;
import com.ysir.custom.constant.HttpStatus;
import com.ysir.custom.util.ServerConfigUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 认证失败处理类 返回未授权
 *
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        String msg = StrUtil.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        ServerConfigUtil.renderString(response, JSONUtil.toJsonStr(AjaxResult.error(HttpStatus.UNAUTHORIZED, msg)));
    }
}
