package cc.tianbin.springframework.test;

import cc.tianbin.springframework.beans.PropertyValue;
import cc.tianbin.springframework.beans.PropertyValues;
import cc.tianbin.springframework.beans.factory.config.BeanDefinition;
import cc.tianbin.springframework.beans.factory.config.BeanReference;
import cc.tianbin.springframework.beans.factory.support.registry.impl.DefaultListableBeanFactory;
import cc.tianbin.springframework.test.bean.UserDao;
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

        // 注册 userDao
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // UserService 设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userId", "1001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // 注册 UserService
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class, propertyValues));

        // 获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 第2次 直接从单例池中拿bean
        UserService userServiceFromSingleton = (UserService) beanFactory.getBean("userService");
//        userServiceFromSingleton.queryUserInfo();
    }

}
