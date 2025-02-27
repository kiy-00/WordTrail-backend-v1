// TokenServiceExtend.java
package com.ruoyi.auth.service.impl;

import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.utils.JwtUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.ip.IpUtils;
import com.ruoyi.common.core.utils.uuid.IdUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.system.api.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * APP令牌服务扩展
 */
@Component
// 在TokenServiceExtend类上
@Service("appTokenService")
public class TokenServiceExtend extends TokenService {

    @Autowired
    private RedisService redisService;

    // APP用户Token缓存前缀
    private final static String APP_ACCESS_TOKEN = CacheConstants.APP_LOGIN_TOKEN_KEY;

    // APP用户刷新Token缓存前缀
    private final static String APP_REFRESH_TOKEN = CacheConstants.APP_REFRESH_TOKEN_KEY;

    /**
     * 创建APP用户令牌
     */
    public Map<String, Object> createAppToken(AppUser appUser) {
        String token = IdUtils.fastUUID();
        String refreshToken = IdUtils.fastUUID();
        Long userId = appUser.getUserId();
        String userName = appUser.getUsername();

        // 保存APP用户信息到Redis
        saveAppUserToRedis(token, refreshToken, appUser);

        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(SecurityConstants.USER_KEY, token);
        claimsMap.put(SecurityConstants.DETAILS_USER_ID, userId);
        claimsMap.put(SecurityConstants.DETAILS_USERNAME, userName);

        // 接口返回信息
        Map<String, Object> rspMap = new HashMap<>();
        rspMap.put("access_token", JwtUtils.createToken(claimsMap));
        rspMap.put("refresh_token", refreshToken);
        rspMap.put("expires_in", CacheConstants.EXPIRATION * 60);
        return rspMap;
    }

    /**
     * 刷新APP用户令牌
     */
    public Map<String, Object> refreshAppToken(String refreshToken) {
        if (StringUtils.isEmpty(refreshToken)) {
            throw new RuntimeException("刷新令牌不能为空");
        }

        // 获取原令牌
        String oldTokenKey = getAppRefreshTokenKey(refreshToken);
        String oldToken = redisService.getCacheObject(oldTokenKey);
        if (StringUtils.isEmpty(oldToken)) {
            throw new RuntimeException("刷新令牌已过期");
        }

        // 获取APP用户信息
        String oldAppUserKey = getAppTokenKey(oldToken);
        AppUser appUser = redisService.getCacheObject(oldAppUserKey);
        if (appUser == null) {
            throw new RuntimeException("登录信息已过期");
        }

        // 删除旧令牌
        redisService.deleteObject(oldAppUserKey);
        redisService.deleteObject(oldTokenKey);

        // 创建新令牌
        return createAppToken(appUser);
    }

    /**
     * 删除APP用户令牌
     */
    public void removeAppToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return;
        }
        String appUserKey = getAppTokenKey(token);
        redisService.deleteObject(appUserKey);
    }

    /**
     * 保存APP用户信息到Redis
     */
    private void saveAppUserToRedis(String token, String refreshToken, AppUser appUser) {
        // 保存用户token
        String appUserKey = getAppTokenKey(token);
        redisService.setCacheObject(appUserKey, appUser, CacheConstants.EXPIRATION, TimeUnit.MINUTES);

        // 保存刷新token
        String refreshKey = getAppRefreshTokenKey(refreshToken);
        redisService.setCacheObject(refreshKey, token, CacheConstants.REFRESH_TIME, TimeUnit.MINUTES);

        // 记录登录信息
        // 记录登录信息
        appUser.setLastLoginIp(IpUtils.getIpAddr());
        appUser.setLastLoginTime(new Date(System.currentTimeMillis()));
    }

    /**
     * 获取APP用户Token缓存KEY
     */
    private String getAppTokenKey(String token) {
        return APP_ACCESS_TOKEN + token;
    }

    /**
     * 获取APP用户刷新Token缓存KEY
     */
    private String getAppRefreshTokenKey(String refreshToken) {
        return APP_REFRESH_TOKEN + refreshToken;
    }
}