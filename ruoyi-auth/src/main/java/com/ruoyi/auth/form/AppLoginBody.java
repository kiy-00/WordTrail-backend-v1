// AppLoginBody.java
package com.ruoyi.auth.form;

public class AppLoginBody {
    private String loginId;       // 登录标识（用户名/手机号/邮箱）
    private String loginType;     // 登录类型：account/phone/email
    private String password;
    private String code;          // 验证码
    private String uuid;
    private String deviceId;      // 设备标识

    // getter 和 setter 方法
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    // ... 其他 getter/setter

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}