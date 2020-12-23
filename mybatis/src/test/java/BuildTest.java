import com.pzz.mybatis.beans.User;
import com.pzz.mybatis.mapper.UserMapper;
import com.pzz.mybatis.utils.DaoUtiles;
import com.pzz.mybatis.utils.DaoUtilesImpl;
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
import java.util.Collection;

/**
 * 启动测试
 * @program: grandle
 * @description: 启动测试
 * @author: pzz
 * @create: 2020-12-15 16:43
 **/
public class BuildTest {
    DaoUtiles daoUtiles = new DaoUtilesImpl();
    //xml方式构建
    @Test
    public void testStartXML() throws IOException {
        SqlSession session = daoUtiles.getSqlSession();

        Collection<Class<?>> mappers = session.getConfiguration().getMapperRegistry().getMappers();
        mappers.forEach(e-> System.out.println(e.getName()));

        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.getUserById("1");
        System.out.println(user);
    }

    //java方式构建
    @Test
    public void testStartJava() throws IOException {
        //修改daoUtiles.getSqlSession()方法内部的构建方式
    }


}
