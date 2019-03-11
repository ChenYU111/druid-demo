package com.example.druid.controller;

import com.alibaba.druid.pool.DruidDataSourceStatLoggerImpl;
import com.alibaba.druid.pool.DruidDataSourceStatValue;
import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.alibaba.druid.stat.DruidStatService;
import com.alibaba.druid.stat.JdbcSqlStatValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DruidStatController {
    @GetMapping("/druidStat")
    public Object druidStat(){
        DruidStatService instance1 = DruidStatService.getInstance();
        // DruidStatManagerFacade#getDataSourceStatDataList 该方法可以获取所有数据源的监控数据，除此之外 DruidStatManagerFacade 还提供了一些其他方法，你可以按需选择使用。
        DruidStatManagerFacade instance = DruidStatManagerFacade.getInstance();
        Object sqlStatById = instance.getSqlStatById(1);
//        DruidDataSourceStatLoggerImpl dataSourceStatLogger = new DruidDataSourceStatLoggerImpl();
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }

}
