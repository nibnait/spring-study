package cc.tianbin.springframework.test.chapter.ch01_BeanFactory;

import cc.tianbin.springframework.beans.factory.support.registry.impl.DefaultListableBeanFactory;
import cc.tianbin.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import cc.tianbin.springframework.test.chapter.ch01_BeanFactory.bean.UserService;
import cc.tianbin.springframework.test.common.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * AbstractBeanFactory 使用模板方法实现 Bean 的定义、注册、获取
 * 基于Cglib实现含构造函数的类实例化策略
 * 为Bean对象注入属性和依赖Bean的功能实现
 * 资源加载器解析文件 注册对象
 * Created by nibnait on 2022/09/18
 */
@Slf4j
public class Ch01_AppTest {

    @Test
    public void test_BeanFactory() {
        // 初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 读取配置文件 & 注册 bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:ch01_BeanFactory/spring.xml");

        // 获取 bean
        UserService userService = beanFactory.getBean(CommonConstants.USER_SERVICE, UserService.class);
        log.info("获取 bean: {}", System.identityHashCode(userService));
        userService.queryUserInfo();

        // 第2次 直接从单例池中拿bean
        UserService userServiceFromSingleton = (UserService) beanFactory.getBean(CommonConstants.USER_SERVICE);
        log.info("直接从单例池中拿bean bean: {}", System.identityHashCode(userServiceFromSingleton));
        userServiceFromSingleton.queryUserInfo();
    }

}
