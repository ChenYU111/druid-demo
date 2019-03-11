package com.example.druid.ScheduledTask;

import com.example.druid.constant.Constant;
import com.example.druid.service.SlowSqlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTask {
    Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
    @Autowired
    SlowSqlService slowSqlService;

    @Scheduled(cron = "0/10 * * * * ?")
    public void getSlowSqlTest() throws Exception {
        List slowSql = slowSqlService.getSlowSql(Constant.TEST_SLOW_LOG);
        logger.info(slowSql.toString());
    }
}
