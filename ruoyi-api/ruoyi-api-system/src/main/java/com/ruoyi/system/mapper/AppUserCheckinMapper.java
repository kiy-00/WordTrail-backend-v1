// AppUserCheckinMapper.java
package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.AppUserCheckin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * APP用户签到Mapper接口
 */
@Mapper
public interface AppUserCheckinMapper {

    /**
     * 新增签到记录
     *
     * @param checkin 签到记录
     * @return 结果
     */
    public int insertCheckin(AppUserCheckin checkin);

    /**
     * 获取用户最后一次签到记录
     *
     * @param userId 用户ID
     * @return 签到记录
     */
    public AppUserCheckin getLastCheckin(Long userId);

    /**
     * 检查今天是否已签到
     *
     * @param userId 用户ID
     * @return 是否已签到
     */
    public boolean isTodayChecked(Long userId);

    /**
     * 获取用户本月签到次数
     *
     * @param userId 用户ID
     * @return 签到次数
     */
    public int getMonthCheckins(Long userId);

    /**
     * 获取用户签到历史
     *
     * @param userId 用户ID
     * @param days 天数
     * @return 签到历史
     */
    public List<AppUserCheckin> getCheckinHistory(@Param("userId") Long userId, @Param("days") int days);
}