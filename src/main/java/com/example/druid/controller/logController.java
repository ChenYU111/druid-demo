package com.example.druid.controller;

import com.example.druid.ScheduledTask.ScheduledTask;
import com.example.druid.constant.Constant;
import com.example.druid.service.SlowSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class logController {
    @Autowired
    SlowSqlService slowSqlService;

    @GetMapping("/getSlowSql")
    public List getSlowSql() {
        List slowSql = slowSqlService.getSlowSql(Constant.TEST_SLOW_LOG);
        return slowSql;
    }
}
