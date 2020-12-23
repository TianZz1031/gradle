import com.pzz.mybatis.beans.User;
import com.pzz.mybatis.mapper.UserMapper;
import com.pzz.mybatis.utils.DaoUtiles;
import com.pzz.mybatis.utils.DaoUtilesImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * 测试增删查改
 * @program: grandle
 * @description: 测试增删查改
 * @author: pzz
 * @create: 2020-12-19 17:27
 **/
public class CRUDtest {
    private DaoUtiles daoUtiles = new DaoUtilesImpl();

    @Test
    public void getFullUser() throws IOException {
        SqlSession session = daoUtiles.getSqlSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.getFullUserById("1");

        System.out.println(Arrays.toString(user.getSons().toArray()));

        daoUtiles.close();
    }

    @Test
    public void insert() throws IOException {
        SqlSession session = daoUtiles.getSqlSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        int i = mapper.addUser(daoUtiles.getRandowUsers());

        System.out.println(i>0?"===============================================================插入成功":
                "===============================================================插入失败");
        List<User> users = mapper.getAllUser();
        System.out.println(Arrays.toString(users.toArray()));

        daoUtiles.close();
    }

    @Test
    public void update() throws IOException {
        SqlSession session = daoUtiles.getSqlSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = daoUtiles.getRandowUsers();
        user.setId("1");

        int i = mapper.updateUser(user);
        System.out.println(i>0?"===============================================================插入成功":
                "===============================================================插入失败");
        List<User> users = mapper.getAllUser();
        System.out.println(Arrays.toString(users.toArray()));

        daoUtiles.close();
    }

    @Test
    public void delete(){
        SqlSession session = daoUtiles.getSqlSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        int i = mapper.deleteUser(mapper.getLastUser());

        System.out.println(i>0?"===============================================================删除成功":
                "===============================================================删除失败");
        List<User> users = mapper.getAllUser();
        System.out.println(Arrays.toString(users.toArray()));
//            System.out.println(mapper.getUserById("1"));
        daoUtiles.close();
    }

    @Test
    public void test(){
        User user = new User();
        user.setAge(12);

        System.out.println(user.getAge());
    }

}
