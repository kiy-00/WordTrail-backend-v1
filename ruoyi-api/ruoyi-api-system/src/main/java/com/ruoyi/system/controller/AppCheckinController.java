// AppCheckinController.java
package com.ruoyi.system.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.AppSecurityUtils; // 从common-core模块引入
import com.ruoyi.system.service.AppCheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/app/checkin")
public class AppCheckinController {

    @Autowired
    private AppCheckinService checkinService;

    /**
     * 用户签到
     */
    @PostMapping
    public R<Map<String, Object>> checkin() {
        Long userId = AppSecurityUtils.getUserId(); // 使用AppSecurityUtils
        return R.ok(checkinService.checkin(userId));
    }

    /**
     * 获取签到状态
     */
    @GetMapping("/status")
    public R<Map<String, Object>> getCheckinStatus() {
        Long userId = AppSecurityUtils.getUserId(); // 使用AppSecurityUtils
        return R.ok(checkinService.getCheckinStatus(userId));
    }

    /**
     * 获取签到历史
     */
    @GetMapping("/history/{days}")
    public R<List<Map<String, Object>>> getCheckinHistory(@PathVariable("days") int days) {
        if (days <= 0 || days > 30) {
            days = 7; // 默认7天
        }
        Long userId = AppSecurityUtils.getUserId(); // 使用AppSecurityUtils
        return R.ok(checkinService.getCheckinHistory(userId, days));
    }
}