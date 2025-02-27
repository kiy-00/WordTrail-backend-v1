// AppUserService.java
package com.ruoyi.auth.service;

import com.ruoyi.auth.form.AppLoginBody;
import com.ruoyi.auth.form.AppRegisterBody;
import java.util.Map;

public interface AppUserService {
    /**
     * 用户登录
     */
    Map<String, Object> login(AppLoginBody appLoginBody);

    /**
     * 用户注册
     */
    Map<String, Object> register(AppRegisterBody appRegisterBody);

    /**
     * 用户登出
     */
    void logout(String token);

    /**
     * 刷新token
     */
    Map<String, Object> refreshToken(String refreshToken);
}