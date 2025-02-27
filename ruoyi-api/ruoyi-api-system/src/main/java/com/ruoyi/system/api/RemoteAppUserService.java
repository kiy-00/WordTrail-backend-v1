// RemoteAppUserService.java
package com.ruoyi.system.api;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.domain.AppUser;
import com.ruoyi.system.api.factory.RemoteAppUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(contextId = "remoteAppUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteAppUserFallbackFactory.class)
public interface RemoteAppUserService {

    /**
     * 通过用户名查询用户信息
     */
    @GetMapping("/system/app/user/username/{username}")
    public R<AppUser> getUserByUsername(@PathVariable("username") String username);

    /**
     * 通过手机号查询用户信息
     */
    @GetMapping("/system/app/user/phone/{phone}")
    public R<AppUser> getUserByPhone(@PathVariable("phone") String phone);

    /**
     * 通过邮箱查询用户信息
     */
    @GetMapping("/system/app/user/email/{email}")
    public R<AppUser> getUserByEmail(@PathVariable("email") String email);

    /**
     * 检查用户名是否唯一
     */
    @GetMapping("/system/app/user/check/username/{username}")
    public R<AppUser> checkUsernameUnique(@PathVariable("username") String username);

    /**
     * 检查手机号是否唯一
     */
    @GetMapping("/system/app/user/check/phone/{phone}")
    public R<AppUser> checkPhoneUnique(@PathVariable("phone") String phone);

    /**
     * 检查邮箱是否唯一
     */
    @GetMapping("/system/app/user/check/email/{email}")
    public R<AppUser> checkEmailUnique(@PathVariable("email") String email);

    /**
     * 注册用户信息
     */
    @PostMapping("/system/app/user/register")
    public R<Boolean> registerUser(@RequestBody AppUser user);

    /**
     * 记录登录信息
     */
    @PostMapping("/system/app/user/login/record")
    public R<Boolean> recordLoginInfo(@RequestParam("userId") Long userId, @RequestParam("deviceId") String deviceId);
}