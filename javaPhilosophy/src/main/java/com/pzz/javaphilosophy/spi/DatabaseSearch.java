package com.pzz.javaphilosophy.spi;

import java.util.List;

/**
 * @program: grandle
 * @description: 数据库实现搜索
 * @author: pzz
 * @create: 2020-12-24 09:57
 **/
public class DatabaseSearch implements Search{
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("数据搜索 "+keyword);
        return null;
    }
}
