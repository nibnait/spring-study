package cc.tianbin.springframework.test.chapter.ch02_context;

import cc.tianbin.springframework.beans.factory.support.registry.impl.DefaultListableBeanFactory;
import cc.tianbin.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import cc.tianbin.springframework.context.support.ClassPathXmlApplicationContext;
import cc.tianbin.springframework.test.chapter.ch02_context.bean.UserService;
import cc.tianbin.springframework.test.chapter.ch02_context.bean.UserServiceImplInitAndDestroy;
import cc.tianbin.springframework.test.chapter.ch02_context.processor.MyBeanFactoryPostProcessor;
import cc.tianbin.springframework.test.chapter.ch02_context.processor.MyBeanPostProcessor;
import cc.tianbin.springframework.test.common.CommonConstants;
import org.junit.Test;

/**
 * 实现应用上下文
 * 向虚拟机注册钩子，实现Bean对象的初始化和销毁方法
 * Created by nibnait on 2022/10/xx
 */
public class Ch02_AppTest {

    @Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor() {
        // 初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 读取配置文件 & 注册 bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:ch02_context/spring_PostProcessor.xml");

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
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:ch02_context/spring.xml");
        applicationContext.registerShutdownHook();

        // 获取 Bean 对象调用方法
        UserService userService = applicationContext.getBean(CommonConstants.USER_SERVICE, UserService.class);
        String result = userService.queryUserInfo();
        System.out.println(result);
    }


    @Test
    public void test_xml_ImplInitAndDestroy() {
        // 初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:ch02_context/spring_ImplInitAndDestroy.xml");
        applicationContext.registerShutdownHook();

        // 获取 Bean 对象调用方法
        UserServiceImplInitAndDestroy userService = applicationContext.getBean(CommonConstants.USER_SERVICE_IMPL_INIT_AND_DESTROY, UserServiceImplInitAndDestroy.class);
        String result = userService.queryUserInfo();
        System.out.println(result);
    }

}
