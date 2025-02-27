package com.ruoyi.common.core.utils;

import com.ruoyi.common.core.context.SecurityContextHolder;

public class AppSecurityUtils {
    public static Long getUserId() {
        return SecurityContextHolder.getUserId();
    }

    public static String getUsername() {
        return SecurityContextHolder.getUserName();
    }
}