package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by LuoboGan
 * Date:2023-04-15
 */
@SpringBootApplication()
// 扫描 mybatis 通用 mapper 所在的包
@MapperScan(basePackages = "org.example.mapper")
// 扫描所有包以及相关组件包
//@ComponentScan(basePackages = {"org.example", "org.n3r.idworker"})
//@EnableTransactionManagement
@EnableScheduling       // 开启定时任务
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
