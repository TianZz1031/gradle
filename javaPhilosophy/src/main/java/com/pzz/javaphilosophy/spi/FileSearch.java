package com.pzz.javaphilosophy.spi;

import java.util.List;

/**
 * @program: grandle
 * @description:
 * @author: pzz
 * @create: 2020-12-24 09:57
 **/
public class FileSearch implements Search{
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("文件搜索 "+keyword);
        return null;
    }
}
