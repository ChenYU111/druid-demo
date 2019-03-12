package com.example.druid.service;

import com.example.druid.constant.Constant;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SlowSqlService {
    /**
     * 慢查詢sql列表,只展示当天的，如果，今天有慢查询且改掉了，明天不会展示出来。
     *
     * @return
     */
    public List getSlowSql(String sloSqlLogFilename) {
        FileReader fileReader = null;
        BufferedReader br = null;
        String str = "";
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH)+1;
        int d = calendar.get(Calendar.DATE);
        String month = m + "";
        String day = d + "";
        if (m < 10) {
            month = "0" + m;
        }
        if (d < 10) {
            day = "0" + d;
        }
        /**
         * 今天的日期
         */

        StringBuffer date = new StringBuffer();
        date.append(year + "-" + month + "-" + day);
        LinkedList<String> sqlList = new LinkedList();
        try {
            /**
             * slowlogFilename 文件地址
             */
            fileReader = new FileReader(sloSqlLogFilename);
            br = new BufferedReader(fileReader);
            while (br.readLine() != null) {
                if (null != br.readLine() && !br.readLine().equals("")) {
                    str = br.readLine();
                    /**
                     * 是慢查询且是当天的日期才放到集合
                     */
                    if (null != str && !str.equals("") && str.contains("ERROR com.alibaba.druid.filter.stat.StatFilter") && str.contains(date.toString())) {
                        sqlList.add(str);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /**
         *  "millis."前的是相关日志其后是sql语句，过滤出sql语句
         */
        List<String> list = sqlList.stream().map(s -> s.split("millis.")[1]).collect(Collectors.toList());
        return list;
    }
}
