// RemoteAppUserFallbackFactory.java
package com.ruoyi.system.api.factory;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.RemoteAppUserService;
import com.ruoyi.system.api.domain.AppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * APP用户服务降级处理
 */
@Component
public class RemoteAppUserFallbackFactory implements FallbackFactory<RemoteAppUserService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteAppUserFallbackFactory.class);

    @Override
    public RemoteAppUserService create(Throwable throwable) {
        log.error("APP用户服务调用失败:{}", throwable.getMessage());
        return new RemoteAppUserService() {
            @Override
            public R<AppUser> getUserByUsername(String username) {
                return R.fail("获取APP用户信息失败:" + throwable.getMessage());
            }

            @Override
            public R<AppUser> getUserByPhone(String phone) {
                return R.fail("通过手机号获取APP用户信息失败:" + throwable.getMessage());
            }

            @Override
            public R<AppUser> getUserByEmail(String email) {
                return R.fail("通过邮箱获取APP用户信息失败:" + throwable.getMessage());
            }

            @Override
            public R<AppUser> checkUsernameUnique(String username) {
                return R.fail("检查APP用户名唯一性失败:" + throwable.getMessage());
            }

            @Override
            public R<AppUser> checkPhoneUnique(String phone) {
                return R.fail("检查APP手机号唯一性失败:" + throwable.getMessage());
            }

            @Override
            public R<AppUser> checkEmailUnique(String email) {
                return R.fail("检查APP邮箱唯一性失败:" + throwable.getMessage());
            }

            @Override
            public R<Boolean> registerUser(AppUser user) {
                return R.fail("注册APP用户失败:" + throwable.getMessage());
            }

            @Override
            public R<Boolean> recordLoginInfo(Long userId, String deviceId) {
                return R.fail("记录APP用户登录信息失败:" + throwable.getMessage());
            }
        };
    }
}