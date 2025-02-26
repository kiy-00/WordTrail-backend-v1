// 声明包名，指定该类所在的包路径
package com.ruoyi.gateway.config.properties;

// 导入Java标准库中的ArrayList类，用于创建动态数组实现
import java.util.ArrayList;
// 导入Java标准库中的List接口，用于声明列表类型
import java.util.List;
// 导入Spring Boot的ConfigurationProperties注解，用于从配置文件中绑定属性
import org.springframework.boot.context.properties.ConfigurationProperties;
// 导入Spring Cloud的RefreshScope注解，用于支持配置动态刷新
import org.springframework.cloud.context.config.annotation.RefreshScope;
// 导入Spring的Configuration注解，标识这是一个配置类
import org.springframework.context.annotation.Configuration;

/**
 * XSS跨站脚本配置
 *
 * @author ruoyi
 */
// 标识这是一个Spring配置类
@Configuration
// 启用配置的动态刷新功能，当配置中心的配置更改时，会自动更新bean的属性值
@RefreshScope
// 指定从配置文件中读取前缀为"security.xss"的配置项绑定到该类的属性
@ConfigurationProperties(prefix = "security.xss")
public class XssProperties
{
    /**
     * Xss开关
     */
    // XSS防护功能是否启用的标志，布尔类型
    private Boolean enabled;

    /**
     * 排除路径
     */
    // 定义一个字符串列表，用于存储不需要XSS过滤的URL路径
    // 初始化为空的ArrayList，避免空指针异常
    private List<String> excludeUrls = new ArrayList<>();

    // 获取XSS防护功能是否启用的getter方法
    public Boolean getEnabled()
    {
        return enabled;
    }

    // 设置XSS防护功能是否启用的setter方法
    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
    }

    // 获取XSS过滤排除URL列表的getter方法
    public List<String> getExcludeUrls()
    {
        return excludeUrls;
    }

    // 设置XSS过滤排除URL列表的setter方法
    public void setExcludeUrls(List<String> excludeUrls)
    {
        this.excludeUrls = excludeUrls;
    }
}