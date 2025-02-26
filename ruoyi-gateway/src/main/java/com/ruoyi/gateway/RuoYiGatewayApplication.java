package com.ruoyi.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 网关启动程序
 *
 * @author ruoyi
 */
// @SpringBootApplication 注解标识这是一个Spring Boot应用程序的入口类
// exclude = {DataSourceAutoConfiguration.class} 表示排除数据源自动配置
// 因为网关服务通常不需要直接连接数据库，所以排除了数据源配置
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RuoYiGatewayApplication
{
    public static void main(String[] args)
    {
        // 启动Spring Boot应用程序，传入当前类作为主配置类
        SpringApplication.run(RuoYiGatewayApplication.class, args);

        // 打印一个ASCII艺术字符图案，表示网关启动成功
        // 这是一个可爱的控制台输出，用于视觉上确认服务已成功启动
        System.out.println("(♥◠‿◠)ﾉﾞ  若依网关启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  * *  \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  *. /  '       \n" +
                " |(* o *) /        *( )_ .'         \n" +
                " | (_,_).' **  **_(_ o *)'          \n" +
                " |  |\\ \\  |  ||   |(*,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
}