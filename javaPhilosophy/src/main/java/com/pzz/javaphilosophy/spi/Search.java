package com.pzz.javaphilosophy.spi;

import java.util.List;
/*
* SPI实现：利用jdk的ServiceLoader加载Search接口实现类
* ServiceLoader<Search> s = ServiceLoader.load(Search.class)
* 其他任何实现该Search接口的ServiceProvider的jar包，只需在META-INF/services添加该接口全限定名文件，在其中添加自己的接口实现类
* 然后ServiceLoader只管使用接口，不用考虑实现
*
* 总结：SPI思想在于，通过某种服务发现机制，加载所有指定接口的实现，ServiceLoader只是jdk的一种实现
*       spring的scan就是另一种实现，只要扫到spring制定的@service、@controller等注解，spring自动会把它注入容器。
*      注：这样对于那些开源框架，可以预留一些扩展点，便于其他第三方自定义实现接口
*
* */
public interface Search {
    public List<String> searchDoc(String keyword);
}
