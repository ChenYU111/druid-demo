package com.example.druid.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.example.druid.StatLogger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
public class DruidConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setStatLogger(new StatLogger());
        return dataSource;
    }

}
