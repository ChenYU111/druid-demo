package com.example.druid.service;

import com.example.druid.constant.Constant;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class SlowSqlService {
    /**
     * 慢查詢sql列表
     * @return
     */
    public List getSlowSql(String sloSqlLogFilename) {
        FileReader fileReader = null;
        BufferedReader br = null;
        String str = "";
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
                    if (null != str && !str.equals("") && str.contains("ERROR com.alibaba.druid.filter.stat.StatFilter")) {
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
        List<String> list = sqlList.stream().map(s -> s.split("millis.")[1]).collect(Collectors.toList());
        return list;
    }
}
