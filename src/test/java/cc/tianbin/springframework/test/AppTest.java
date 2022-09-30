package cc.tianbin.springframework.test;

import cc.tianbin.springframework.factory.config.BeanDefinition;
import cc.tianbin.springframework.factory.support.registry.impl.DefaultListableBeanFactory;
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

        // 注册 bean
        String USER_SERVICE_BEAN_NAME = "userService";
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition(USER_SERVICE_BEAN_NAME, beanDefinition);

        // 获取 bean
        UserService userService = (UserService) beanFactory.getBean(USER_SERVICE_BEAN_NAME, "tianbin");
        userService.queryUserInfo();

        // 第2次 直接从单例池中拿bean
        UserService userServiceFromSingleton = (UserService) beanFactory.getBean(USER_SERVICE_BEAN_NAME);
        userServiceFromSingleton.queryUserInfo();
    }

}
