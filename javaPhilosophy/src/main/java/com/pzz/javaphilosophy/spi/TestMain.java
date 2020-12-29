package com.pzz.javaphilosophy.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @program: grandle
 * @description: 测试类
 * @author: pzz
 * @create: 2020-12-24 09:58
 **/
public class TestMain {
    public static void main(String[] args) {
        ServiceLoader<Search> s = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = s.iterator();

        while (iterator.hasNext()) {
            Search search =  iterator.next();
            search.searchDoc("hello world");
        }
    }
}
