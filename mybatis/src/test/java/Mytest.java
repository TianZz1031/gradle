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
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: grandle
 * @description: test
 * @author: pzz
 * @create: 2020-12-15 16:43
 **/
public class Mytest {
    //xml构建
    @Test
    public void testStart() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectbyid("1");
            System.out.println(user);
        }
    }
    //java实例构建
    @Test
    public void testStart2() throws IOException {
        DataSource dataSource = new PooledDataSource("com.mysql.cj.jdbc.Driver","jdbc:mysql://192.168.10.12:3306/test","root","123456");
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);

        configuration.addMapper(UserMapper.class);
        configuration.addMappers("mybatis\\mappers\\UserMapper.xml",UserMapper.class);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectbyid("1");
            System.out.println(user);
        }
    }
}
