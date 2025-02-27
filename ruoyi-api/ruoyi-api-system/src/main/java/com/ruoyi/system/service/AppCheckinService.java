// AppCheckinService.java
package com.ruoyi.system.service;

import java.util.List;
import java.util.Map;

public interface AppCheckinService {
    /**
     * 用户签到
     */
    Map<String, Object> checkin(Long userId);

    /**
     * 获取用户签到状态
     */
    Map<String, Object> getCheckinStatus(Long userId);

    /**
     * 获取用户签到历史记录
     */
    List<Map<String, Object>> getCheckinHistory(Long userId, int days);
}