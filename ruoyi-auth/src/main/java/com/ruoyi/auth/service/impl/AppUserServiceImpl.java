// AppUserServiceImpl.java
package com.ruoyi.auth.service.impl;

import com.ruoyi.auth.form.AppLoginBody;
import com.ruoyi.auth.form.AppRegisterBody;
import com.ruoyi.auth.service.AppUserService;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.RemoteAppUserService;
import com.ruoyi.system.api.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private TokenServiceExtend tokenService;  // 使用扩展的TokenService

    @Autowired
    private RemoteAppUserService remoteAppUserService;

    @Override
    public Map<String, Object> login(AppLoginBody loginBody) {
        // 根据登录类型查询用户
        AppUser appUser = null;
        String loginType = loginBody.getLoginType();
        String loginId = loginBody.getLoginId();

        if (StringUtils.isEmpty(loginType) || StringUtils.isEmpty(loginId)) {
            throw new ServiceException("登录参数不能为空");
        }

        switch (loginType) {
            case "account":
                appUser = remoteAppUserService.getUserByUsername(loginId).getData();
                break;
            case "phone":
                appUser = remoteAppUserService.getUserByPhone(loginId).getData();
                break;
            case "email":
                appUser = remoteAppUserService.getUserByEmail(loginId).getData();
                break;
            default:
                throw new ServiceException("不支持的登录类型");
        }

        if (appUser == null) {
            throw new ServiceException("用户不存在");
        }

        if (!"0".equals(appUser.getStatus())) {
            throw new ServiceException("账号已被停用");
        }

        // 验证密码
        if (!SecurityUtils.matchesPassword(loginBody.getPassword(), appUser.getPassword())) {
            throw new ServiceException("用户名或密码错误");
        }

        // 生成token
        Map<String, Object> tokenInfo = tokenService.createAppToken(appUser);

        // 记录登录日志
        remoteAppUserService.recordLoginInfo(appUser.getUserId(), loginBody.getDeviceId());

        return tokenInfo;
    }

    @Override
    public Map<String, Object> register(AppRegisterBody registerBody) {
        // 验证用户名是否已存在
        if (remoteAppUserService.checkUsernameUnique(registerBody.getUsername()) != null) {
            throw new ServiceException("用户名已存在");
        }

        // 验证手机号是否已存在
        if (StringUtils.isNotEmpty(registerBody.getPhoneNumber())
                && remoteAppUserService.checkPhoneUnique(registerBody.getPhoneNumber()) != null) {
            throw new ServiceException("手机号已存在");
        }

        // 验证邮箱是否已存在
        if (StringUtils.isNotEmpty(registerBody.getEmail())
                && remoteAppUserService.checkEmailUnique(registerBody.getEmail()) != null) {
            throw new ServiceException("邮箱已存在");
        }

        // 构建用户对象
        AppUser appUser = new AppUser();
        appUser.setUsername(registerBody.getUsername());
        appUser.setPassword(SecurityUtils.encryptPassword(registerBody.getPassword()));
        appUser.setPhoneNumber(registerBody.getPhoneNumber());
        appUser.setEmail(registerBody.getEmail());
        appUser.setStatus("0"); // 正常状态

        // 注册用户
        boolean regFlag = remoteAppUserService.registerUser(appUser).getData();
        if (!regFlag) {
            throw new ServiceException("注册失败，请联系管理员");
        }

        // 获取新注册的用户信息
        appUser = remoteAppUserService.getUserByUsername(registerBody.getUsername()).getData();

        // 生成token
        return tokenService.createAppToken(appUser);
    }

    @Override
    public void logout(String token) {
        tokenService.removeAppToken(token);
    }

    @Override
    public Map<String, Object> refreshToken(String refreshToken) {
        return tokenService.refreshAppToken(refreshToken);
    }
}