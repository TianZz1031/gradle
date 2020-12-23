package com.pzz.mybatis.Main;

import com.pzz.mybatis.beans.User;
import com.pzz.mybatis.mapper.UserMapper;
import com.pzz.mybatis.utils.DaoUtiles;
import com.pzz.mybatis.utils.DaoUtilesImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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
