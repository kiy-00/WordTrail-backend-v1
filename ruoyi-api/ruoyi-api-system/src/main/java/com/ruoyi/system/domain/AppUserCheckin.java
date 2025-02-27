// AppUserCheckin.java
package com.ruoyi.system.domain;

import java.util.Date;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * APP用户签到实体类
 */
public class AppUserCheckin extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 签到ID */
    private Long id;

    /** 用户ID */
    private Long userId;

    /** 签到时间 */
    private Date checkinTime;

    /** 连续签到天数 */
    private Integer checkinDays;

    /** 获得积分 */
    private Integer points;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
    }

    public Integer getCheckinDays() {
        return checkinDays;
    }

    public void setCheckinDays(Integer checkinDays) {
        this.checkinDays = checkinDays;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}