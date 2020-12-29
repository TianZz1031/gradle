package com.pzz.mybatis.Main;

import com.pzz.mybatis.utils.DaoUtiles;
import com.pzz.mybatis.utils.DaoUtilesImpl;

import java.io.File;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: grandle
 * @description: 主运行类
 * @author: pzz
 * @create: 2020-12-21 08:45
 **/
public class Main {
    private static DaoUtiles daoUtiles = new DaoUtilesImpl();
    public static void main(String[] args) {
        File file = new File("src/main/resources/log4j.properties");
        System.out.println(file.isDirectory());
    }

    private static void testThreadPool() {
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(2);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(123);

                int i = 12;
            }
        });
//        threadPoolExecutor.execute(thread);
        threadPoolExecutor.scheduleAtFixedRate(thread, 2000, 5000, TimeUnit.MILLISECONDS);
    }
}
