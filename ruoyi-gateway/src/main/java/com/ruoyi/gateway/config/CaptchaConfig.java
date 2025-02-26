package com.ruoyi.gateway.config;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import static com.google.code.kaptcha.Constants.*;

/**
 * 验证码配置
 *
 * @author ruoyi
 */
// @Configuration 注解表示这是一个配置类，Spring会在启动时加载这个类来创建Bean
@Configuration
public class CaptchaConfig
{
    /**
     * 配置普通文本验证码生成器
     * @return 返回配置好的DefaultKaptcha实例
     */
    @Bean(name = "captchaProducer")
    public DefaultKaptcha getKaptchaBean()
    {
        // 创建DefaultKaptcha实例，这是Google Kaptcha库的主要验证码生成类
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        // 创建Properties对象，用于设置验证码的各种参数
        Properties properties = new Properties();
        // 是否有边框 默认为true 我们可以自己设置yes，no
        properties.setProperty(KAPTCHA_BORDER, "yes");
        // 验证码文本字符颜色 默认为Color.BLACK
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_COLOR, "black");
        // 验证码图片宽度 默认为200
        properties.setProperty(KAPTCHA_IMAGE_WIDTH, "160");
        // 验证码图片高度 默认为50
        properties.setProperty(KAPTCHA_IMAGE_HEIGHT, "60");
        // 验证码文本字符大小 默认为40
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_SIZE, "38");
        // 设置Session中验证码的key
        properties.setProperty(KAPTCHA_SESSION_CONFIG_KEY, "kaptchaCode");
        // 验证码文本字符长度 默认为5，这里设为4个字符
        properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        // 验证码文本字体样式 默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial,Courier");
        // 图片样式 - 使用阴影效果(ShadowGimpy)，其他选项包括水纹(WaterRipple)和鱼眼(FishEyeGimpy)
        properties.setProperty(KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
        // 使用配置属性创建Config对象
        Config config = new Config(properties);
        // 将配置设置到defaultKaptcha实例
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    /**
     * 配置数学运算类型的验证码生成器
     * 用于生成算术表达式验证码，如"1+2=?"
     * @return 返回配置好的DefaultKaptcha实例
     */
    @Bean(name = "captchaProducerMath")
    public DefaultKaptcha getKaptchaBeanMath()
    {
        // 创建DefaultKaptcha实例
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 是否有边框 默认为true 我们可以自己设置yes，no
        properties.setProperty(KAPTCHA_BORDER, "yes");
        // 边框颜色 默认为Color.BLACK，这里设置为绿色
        properties.setProperty(KAPTCHA_BORDER_COLOR, "105,179,90");
        // 验证码文本字符颜色 默认为Color.BLACK，这里设置为蓝色
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
        // 验证码图片宽度 默认为200
        properties.setProperty(KAPTCHA_IMAGE_WIDTH, "160");
        // 验证码图片高度 默认为50
        properties.setProperty(KAPTCHA_IMAGE_HEIGHT, "60");
        // 验证码文本字符大小 默认为40
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_SIZE, "35");
        // 设置Session中数学验证码的key
        properties.setProperty(KAPTCHA_SESSION_CONFIG_KEY, "kaptchaCodeMath");
        // 设置自定义的验证码文本生成器，用于生成数学算式
        properties.setProperty(KAPTCHA_TEXTPRODUCER_IMPL, "com.ruoyi.gateway.config.KaptchaTextCreator");
        // 验证码文本字符间距 默认为2
        properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "3");
        // 验证码文本字符长度 默认为5，这里设为6
        properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "6");
        // 验证码文本字体样式 默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial,Courier");
        // 验证码噪点颜色 默认为Color.BLACK，这里设置为白色
        properties.setProperty(KAPTCHA_NOISE_COLOR, "white");
        // 干扰实现类 - 使用NoNoise，表示不添加噪点干扰
        properties.setProperty(KAPTCHA_NOISE_IMPL, "com.google.code.kaptcha.impl.NoNoise");
        // 图片样式 - 使用阴影效果，与普通验证码相同
        properties.setProperty(KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
        // 使用配置属性创建Config对象
        Config config = new Config(properties);
        // 将配置设置到defaultKaptcha实例
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}