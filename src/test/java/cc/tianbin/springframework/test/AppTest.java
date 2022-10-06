package cc.tianbin.springframework.test;

import cc.tianbin.springframework.beans.factory.support.registry.impl.DefaultListableBeanFactory;
import cc.tianbin.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import cc.tianbin.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * Created by nibnait on 2022/09/18
 */
public class AppTest {

    @Test
    public void testBeanFactory() {
        // 初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 读取配置文件 & 注册 Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 获取 bean
        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();

        // 第2次 直接从单例池中拿bean
        UserService userServiceFromSingleton = (UserService) beanFactory.getBean("userService");
//        userServiceFromSingleton.queryUserInfo();
    }

}
