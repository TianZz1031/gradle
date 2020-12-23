package com.pzz.mybatis.utils;

import com.pzz.mybatis.beans.User;
import com.pzz.mybatis.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
import java.util.Random;

/**
 * @program: grandle
 * @description: mybatis工具实现类
 * @author: pzz
 * @create: 2020-12-19 17:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DaoUtilesImpl implements DaoUtiles{
    private SqlSession sqlSession;


    //xml构建
    private SqlSessionFactory getSqlSessionFactoryByXML(){
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new SqlSessionFactoryBuilder().build(inputStream);
    }
    //java实例构建
    private SqlSessionFactory getSqlSessionFactoryByJava() {
        DataSource dataSource = new PooledDataSource(
                "com.mysql.cj.jdbc.Driver", "jdbc:mysql://192.168.10.12:3306/test", "root", "123456");

        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.getTypeAliasRegistry().registerAliases("com.pzz.mybatis.beans");


        configuration.addMapper(UserMapper.class);

        return new SqlSessionFactoryBuilder().build(configuration);
    }



    @Override
    public SqlSession getSqlSession() {
//        SqlSessionFactory sqlSessionFactory = "java".equals(xmlOrJava) ? getSqlSessionFactoryByJava() : getSqlSessionFactoryByXML();
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactoryByXML();
        this.sqlSession = sqlSessionFactory.openSession();
        return this.sqlSession;
    }


    @Override
    public void close() {
        this.sqlSession.commit();
        this.sqlSession.close();
    }

    //随机生成字符串
    private String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
    //随机生成数字
    private int getRandomNumber(int size){
        return new Random().nextInt(size);
    }

    @Override
    public User getRandowUsers() {
        User user = new User();
        user.setUname(getRandomString(10));
        user.setAge(getRandomNumber(70));
        return user;
    }
}
