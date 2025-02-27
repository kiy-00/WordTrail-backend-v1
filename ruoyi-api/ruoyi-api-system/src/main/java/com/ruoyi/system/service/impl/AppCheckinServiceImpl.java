// AppCheckinServiceImpl.java
package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.AppUserCheckin;
import com.ruoyi.system.mapper.AppUserCheckinMapper;
import com.ruoyi.system.service.AppCheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.time.*;

@Service
public class AppCheckinServiceImpl implements AppCheckinService {

    @Autowired
    private AppUserCheckinMapper checkinMapper;

    @Override
    public Map<String, Object> checkin(Long userId) {
        // 判断今天是否已签到
        if (checkinMapper.isTodayChecked(userId)) {
            throw new RuntimeException("今天已签到");
        }

        // 获取昨天签到记录，计算连续签到天数
        int consecutiveDays = 1;
        LocalDate yesterday = LocalDate.now().minusDays(1);
        AppUserCheckin lastCheckin = checkinMapper.getLastCheckin(userId);

        if (lastCheckin != null) {
            LocalDate lastCheckinDate = lastCheckin.getCheckinTime().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            // 如果昨天签到了，连续签到天数加1
            if (lastCheckinDate.isEqual(yesterday)) {
                consecutiveDays = lastCheckin.getCheckinDays() + 1;
            }
        }

        // 计算获得的积分 (基础分 + 连续签到奖励)
        int points = 5; // 基础积分
        if (consecutiveDays > 1) {
            // 连续签到奖励，可以根据需求定制
            points += Math.min(consecutiveDays, 7); // 最多奖励7分
        }

        // 创建签到记录
        AppUserCheckin checkin = new AppUserCheckin();
        checkin.setUserId(userId);
        checkin.setCheckinTime(new Date());
        checkin.setCheckinDays(consecutiveDays);
        checkin.setPoints(points);
        checkin.setCreateTime(new Date());

        // 保存签到记录
        checkinMapper.insertCheckin(checkin);

        // 更新用户积分 (需要额外实现)
        // userPointsService.addPoints(userId, points);

        Map<String, Object> result = new HashMap<>();
        result.put("checkinTime", checkin.getCheckinTime());
        result.put("consecutiveDays", consecutiveDays);
        result.put("points", points);

        return result;
    }

    @Override
    public Map<String, Object> getCheckinStatus(Long userId) {
        Map<String, Object> result = new HashMap<>();

        // 今天是否已签到
        boolean todayChecked = checkinMapper.isTodayChecked(userId);
        result.put("todayChecked", todayChecked);

        // 获取连续签到天数
        AppUserCheckin lastCheckin = checkinMapper.getLastCheckin(userId);
        int consecutiveDays = 0;

        if (lastCheckin != null) {
            LocalDate lastCheckinDate = lastCheckin.getCheckinTime().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            // 如果今天已签到或最后签到日期是昨天
            if (todayChecked) {
                consecutiveDays = lastCheckin.getCheckinDays();
            } else if (lastCheckinDate.isEqual(LocalDate.now().minusDays(1))) {
                consecutiveDays = lastCheckin.getCheckinDays();
            }
        }

        result.put("consecutiveDays", consecutiveDays);

        // 本月签到次数
        int monthCheckins = checkinMapper.getMonthCheckins(userId);
        result.put("monthCheckins", monthCheckins);

        return result;
    }

    @Override
    public List<Map<String, Object>> getCheckinHistory(Long userId, int days) {
        // 获取指定天数的签到历史
        List<AppUserCheckin> checkins = checkinMapper.getCheckinHistory(userId, days);
        List<Map<String, Object>> result = new ArrayList<>();

        // 生成每天的签到状态
        LocalDate today = LocalDate.now();
        for (int i = 0; i < days; i++) {
            LocalDate date = today.minusDays(i);
            Map<String, Object> dayInfo = new HashMap<>();
            dayInfo.put("date", Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            dayInfo.put("checked", false);
            dayInfo.put("points", 0);

            // 在历史记录中查找当天签到信息
            for (AppUserCheckin checkin : checkins) {
                LocalDate checkinDate = checkin.getCheckinTime().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                if (checkinDate.isEqual(date)) {
                    dayInfo.put("checked", true);
                    dayInfo.put("points", checkin.getPoints());
                    break;
                }
            }

            result.add(dayInfo);
        }

        return result;
    }
}