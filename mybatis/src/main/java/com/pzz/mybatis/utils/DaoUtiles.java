package com.pzz.mybatis.utils;

import com.pzz.mybatis.beans.User;
import com.pzz.mybatis.mapper.UserMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * mybatis封装工具类
 *
 * @program: grandle
 * @description: mybatis工具接口
 * @author: pzz
 * @create: 2020-12-19 17:05
 **/
public interface DaoUtiles {
    /**
     * 获取SqlSession连接
     * @return SqlSession
     **/
    public SqlSession getSqlSession();

    /**
     * 提交事务，关闭所有资源连接
     **/
    public void close();

    /**
     * @return 随机生成用户
     **/
    public User getRandowUsers();

}
