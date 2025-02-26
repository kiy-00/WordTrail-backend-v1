// 声明包名，指定该类所在的包路径
package com.ruoyi.gateway.config.properties;

// 导入Spring Boot的ConfigurationProperties注解，用于从配置文件中绑定属性
import org.springframework.boot.context.properties.ConfigurationProperties;
// 导入Spring Cloud的RefreshScope注解，用于支持配置动态刷新
import org.springframework.cloud.context.config.annotation.RefreshScope;
// 导入Spring的Configuration注解，标识这是一个配置类
import org.springframework.context.annotation.Configuration;

/**
 * 验证码配置
 *
 * @author ruoyi
 */
// 标识这是一个Spring配置类
@Configuration
// 启用配置的动态刷新功能，当配置中心的配置更改时，会自动更新bean的属性值
@RefreshScope
// 指定从配置文件中读取前缀为"security.captcha"的配置项绑定到该类的属性
//kiy：我并没有找到在哪个配置文件里面有"security.captcha"
@ConfigurationProperties(prefix = "security.captcha")
public class CaptchaProperties
{
    /**
     * 验证码开关
     */
    // 验证码功能是否启用的标志，布尔类型
    private Boolean enabled;

    /**
     * 验证码类型（math 数组计算 char 字符）
     */
    // 验证码类型，可以是"math"(数学计算)或"char"(字符验证码)
    private String type;

    // 获取验证码是否启用的getter方法
    public Boolean getEnabled()
    {
        return enabled;
    }

    // 设置验证码是否启用的setter方法
    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
    }

    // 获取验证码类型的getter方法
    public String getType()
    {
        return type;
    }

    // 设置验证码类型的setter方法
    public void setType(String type)
    {
        this.type = type;
    }
}