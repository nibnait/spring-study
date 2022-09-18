package cc.tianbin.springframework.test;

import cc.tianbin.springframework.BeanDefinition;
import cc.tianbin.springframework.BeanFactory;
import cc.tianbin.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * Created by nibnait on 2022/09/18
 */
public class AppTest {

    @Test
    public void testBeanFactory() {
        // 初始化 BeanFactory
        BeanFactory beanFactory = new BeanFactory();

        // 注册 bean
        String userServiceBeanName = "userService";
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition(userServiceBeanName, beanDefinition);

        // 获取 bean
        UserService userService = (UserService) beanFactory.getBean(userServiceBeanName);
        userService.queryUserInfo();
    }

}
