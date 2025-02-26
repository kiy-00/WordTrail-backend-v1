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
 * 放行白名单配置
 *
 * @author ruoyi
 */
// 标识这是一个Spring配置类
@Configuration
// 启用配置的动态刷新功能，当配置中心的配置更改时，会自动更新bean的属性值
@RefreshScope
// 指定从配置文件中读取前缀为"security.ignore"的配置项绑定到该类的属性
@ConfigurationProperties(prefix = "security.ignore")
public class IgnoreWhiteProperties
{
    /**
     * 放行白名单配置，网关不校验此处的白名单
     */
    // 定义一个字符串列表，用于存储不需要网关校验的URL路径
    // 初始化为空的ArrayList，避免空指针异常
    private List<String> whites = new ArrayList<>();

    // 获取白名单列表的getter方法
    public List<String> getWhites()
    {
        return whites;
    }

    // 设置白名单列表的setter方法
    public void setWhites(List<String> whites)
    {
        this.whites = whites;
    }
}