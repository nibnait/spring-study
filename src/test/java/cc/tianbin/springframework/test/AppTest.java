package cc.tianbin.springframework.test;

import cc.tianbin.springframework.beans.factory.support.registry.impl.DefaultListableBeanFactoryBean;
import cc.tianbin.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import cc.tianbin.springframework.context.support.ClassPathXmlApplicationContext;
import cc.tianbin.springframework.test.bean.UserService;
import cc.tianbin.springframework.test.bean.UserServiceImplAware;
import cc.tianbin.springframework.test.bean.UserServiceImplInitAndDestroy;
import cc.tianbin.springframework.test.common.CommonConstants;
import cc.tianbin.springframework.test.common.MyBeanFactoryPostProcessor;
import cc.tianbin.springframework.test.common.MyBeanPostProcessor;
import org.junit.Test;

/**
 * Created by nibnait on 2022/09/18
 */
public class AppTest {

    @Test
    public void test_BeanFactory() {
        // 初始化 BeanFactory
        DefaultListableBeanFactoryBean beanFactory = new DefaultListableBeanFactoryBean();

        // 读取配置文件 & 注册 bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 获取 bean
        UserService userService = beanFactory.getBean(CommonConstants.USER_SERVICE, UserService.class);
        userService.queryUserInfo();

        // 第2次 直接从单例池中拿bean
        UserService userServiceFromSingleton = (UserService) beanFactory.getBean(CommonConstants.USER_SERVICE);
//        userServiceFromSingleton.queryUserInfo();
    }

    @Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor() {
        // 初始化 BeanFactory
        DefaultListableBeanFactoryBean beanFactory = new DefaultListableBeanFactoryBean();

        // 读取配置文件 & 注册 bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // BeanDefinition 加载完成 & Bean实例化之前，修改 BeanDefinition 的属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // bean实例化之后，修改 bean 属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 获取bean对象调用方法
        UserService userService = beanFactory.getBean(CommonConstants.USER_SERVICE, UserService.class);
        String result = userService.queryUserInfo();
        System.out.println(result);
    }

    @Test
    public void test_xml() {
        // 初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 获取 Bean 对象调用方法
        UserService userService = applicationContext.getBean(CommonConstants.USER_SERVICE, UserService.class);
        String result = userService.queryUserInfo();
        System.out.println(result);
    }

    @Test
    public void test_xml_ImplInitAndDestroy() {
        // 初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springImplInitAndDestroy.xml");
        applicationContext.registerShutdownHook();

        // 获取 Bean 对象调用方法
        UserServiceImplInitAndDestroy userService = applicationContext.getBean(CommonConstants.USER_SERVICE_IMPL_INIT_AND_DESTROY, UserServiceImplInitAndDestroy.class);
        String result = userService.queryUserInfo();
        System.out.println(result);
    }

    @Test
    public void test_xml_ImplAware() {
        // 初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springImplAware.xml.xml");
        applicationContext.registerShutdownHook();

        // 获取 Bean 对象调用方法
        UserServiceImplAware userService = applicationContext.getBean(CommonConstants.USER_SERVICE_IMPL_AWARE, UserServiceImplAware.class);
        String result = userService.queryUserInfo();
        System.out.println("result: " + result);
        System.out.println("ApplicationContextAware：" + userService.getApplicationContext());
        System.out.println("BeanFactoryAware：" + userService.getBeanFactory());
    }

}
