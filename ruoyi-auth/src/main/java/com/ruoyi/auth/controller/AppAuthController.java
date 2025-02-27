// AppAuthController.java
package com.ruoyi.auth.controller;

import com.ruoyi.auth.form.AppLoginBody;
import com.ruoyi.auth.form.AppRegisterBody;
import com.ruoyi.auth.service.AppUserService;
import com.ruoyi.common.core.constant.TokenConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/app")
public class AppAuthController {

    @Autowired
    private AppUserService appUserService;

    /**
     * 登录方法
     */
    @PostMapping("/login")
    public R<Map<String, Object>> login(@RequestBody AppLoginBody appLoginBody) {
        return R.ok(appUserService.login(appLoginBody));
    }

    /**
     * 注册方法
     */
    @PostMapping("/register")
    public R<Map<String, Object>> register(@RequestBody AppRegisterBody appRegisterBody) {
        return R.ok(appUserService.register(appRegisterBody));
    }

    /**
     * 登出方法
     */
    @DeleteMapping("/logout")
    public R<Void> logout(@RequestHeader(value = "Authorization", required = false) String token) {
        if (StringUtils.isNotEmpty(token)) {
            String tokenValue = token.replace(TokenConstants.PREFIX, "");
            appUserService.logout(tokenValue);
        }
        return R.ok();
    }

    /**
     * 刷新令牌
     */
    @PostMapping("/refresh")
    public R<Map<String, Object>> refresh(@RequestBody Map<String, String> params) {
        String refreshToken = params.get("refreshToken");
        if (StringUtils.isEmpty(refreshToken)) {
            return R.fail("刷新令牌不能为空");
        }
        return R.ok(appUserService.refreshToken(refreshToken));
    }
}