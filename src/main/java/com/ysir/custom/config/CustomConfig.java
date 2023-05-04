package com.ysir.custom.config;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 */
@Component
public class CustomConfig {

    /**
     * 上传路径
     */
    public static String profile = "D:/custom/uploadPath";

    @Value("${file.upload-url}")
    public static void setProfile(String profile) {
        if (StrUtil.isNotBlank(profile)){
            CustomConfig.profile = profile;
        }else {
            String os = System.getProperty("os.name");
            if (os.startsWith("Win")){
                CustomConfig.profile = "D:/custom/upload";
            }else {
                CustomConfig.profile = "/home/custom/upload";
            }
        }
    }

    /**
     * 获取导入上传路径
     */
    public static String getImportPath() {
        return profile + "/import";
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath() {
        return profile + "/avatar";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath() {
        return profile + "/download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath() {
        return profile + "/upload";
    }
}
