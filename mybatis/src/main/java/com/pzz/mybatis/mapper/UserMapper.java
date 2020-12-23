package com.pzz.mybatis.mapper;

import com.pzz.mybatis.beans.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @program: grandle
 * @description: user
 * @author: pzz
 * @create: 2020-12-15 17:10
 **/
public interface UserMapper {

    /**
     * 根据id查询用户
     * @param userId 查询的用户id
     * @return 返回查询的用户
     **/
    //@Select("select * from users where id = #{id}")
    public User getUserById(String userId);
    /**
     * 根据id获取一个包含家庭成员的用户
     * @param userId 查询的用户id
     * @return 返回查询的用户
     **/
    //@Select("select * from users where id = #{id}")
    public User getFullUserById(String userId);
    /**
     * 查询所有用户
     * @return 返回查询到的所有用户
     **/
    public List<User> getAllUser();
    /**
     * 查询最后一个
     * @return 返回最后一个用户
     **/
    public User getLastUser();

    /**
     * 插入用户
     * @param user 插入的用户
     * @return 返回插入影响的行数
     **/
    public int addUser(User user);

    /**
     * 删除用户
     * @return 返回删除影响的行数
     **/
    public int deleteUser(User user);

    /**
     * 删除用户
     * @return 返回删除影响的行数
     **/
    @Update({"<script>",
            "update users",
            "        <set>",
            "            <if test=\"uname != null\">uname=#{uname},</if>",
            "            <if test=\"age != null\">age=#{age},</if>",
            "        </set>",
            "        where id = #{id}",
            "</script>"})
    public int updateUser(User user);
}
