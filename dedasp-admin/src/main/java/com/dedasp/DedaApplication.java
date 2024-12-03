package com.dedasp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动程序
 *
 */
@EnableScheduling
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class DedaApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(DedaApplication.class, args);
        System.out.println("-------- 德达招标代理管理系统启动成功 ---------- \n");
    }
}
