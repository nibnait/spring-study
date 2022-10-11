package cc.tianbin.springframework.test;

import cc.tianbin.springframework.beans.factory.support.registry.impl.DefaultListableBeanFactoryBean;
import cc.tianbin.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import cc.tianbin.springframework.context.support.ClassPathXmlApplicationContext;
import cc.tianbin.springframework.test.bean.*;
import cc.tianbin.springframework.test.common.CommonConstants;
import cc.tianbin.springframework.test.common.MyBeanFactoryPostProcessor;
import cc.tianbin.springframework.test.common.MyBeanPostProcessor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by nibnait on 2022/09/18
 */
@Slf4j
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
        reader.loadBeanDefinitions("classpath:spring_PostProcessor.xml");

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
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_ImplInitAndDestroy.xml");
        applicationContext.registerShutdownHook();

        // 获取 Bean 对象调用方法
        UserServiceImplInitAndDestroy userService = applicationContext.getBean(CommonConstants.USER_SERVICE_IMPL_INIT_AND_DESTROY, UserServiceImplInitAndDestroy.class);
        String result = userService.queryUserInfo();
        System.out.println(result);
    }

    @Test
    public void test_xml_ImplAware() {
        // 初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_ImplAware.xml");
        applicationContext.registerShutdownHook();

        // 获取 Bean 对象调用方法
        UserServiceImplAware userService = applicationContext.getBean(CommonConstants.USER_SERVICE_IMPL_AWARE, UserServiceImplAware.class);
        String result = userService.queryUserInfo();
        System.out.println("result: " + result);
        System.out.println("ApplicationContextAware：" + userService.getApplicationContext());
        System.out.println("BeanFactoryAware：" + userService.getBeanFactory());
    }

    @Test
    public void test_xml_ImplScope() {
        // 初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_Scope.xml");
        applicationContext.registerShutdownHook();

        // 获取 Bean 对象调用方法
        UserServiceImplScope userService01 = applicationContext.getBean(CommonConstants.USER_SERVICE_IMPL_SCOPE, UserServiceImplScope.class);
        String result01 = userService01.queryUserInfo();
        System.out.println("result01: " + result01);

        UserServiceImplScope userService02 = applicationContext.getBean(CommonConstants.USER_SERVICE_IMPL_SCOPE, UserServiceImplScope.class);
        String result02 = userService01.queryUserInfo();
        System.out.println("result02: " + result02);

        // 3. 配置 scope="prototype/singleton"
        System.out.println(System.identityHashCode(userService01));
        System.out.println(System.identityHashCode(userService02));
    }

    @Test
    public void testProperty() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-property.xml");
        UserServiceImpl userService = applicationContext.getBean("userService", UserServiceImpl.class);
        String result = userService.queryUserInfo();
        log.info("result: {} token: {}", result, userService.getToken());
    }

    @Test
    public void testScan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");
        IUserService userService = applicationContext.getBean("userService", UserServiceImpl.class);
        String result = userService.queryUserInfo();
        System.out.println("result: " + result);
    }
}
