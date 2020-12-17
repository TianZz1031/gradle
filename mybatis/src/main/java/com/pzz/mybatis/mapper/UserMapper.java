package com.pzz.mybatis.mapper;

import com.pzz.mybatis.beans.User;
import org.apache.ibatis.annotations.Select;

/**
 * @program: grandle
 * @description: user
 * @author: pzz
 * @create: 2020-12-15 17:10
 **/
public interface UserMapper {

//    @Select("select * from users where id = #{id}")
    public User selectbyid(String userId);
}
