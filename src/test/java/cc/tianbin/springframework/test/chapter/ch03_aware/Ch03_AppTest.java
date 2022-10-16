package cc.tianbin.springframework.test.chapter.ch03_aware;

import cc.tianbin.springframework.context.support.ClassPathXmlApplicationContext;
import cc.tianbin.springframework.test.chapter.ch03_aware.bean.UserServiceImplAware;
import cc.tianbin.springframework.test.chapter.ch03_aware.bean.UserServiceImplScope;
import cc.tianbin.springframework.test.common.CommonConstants;
import org.junit.Test;

/**
 * 定义标记类型Aware接口，实现感知容器对象
 * 关于Bean对象作用域以及FactoryBean的实现和使用
 * Created by nibnait on 2022/10/xx
 */
public class Ch03_AppTest {

    @Test
    public void test_xml_ImplAware() {
        // 初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:ch03_aware/spring_ImplAware.xml");
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
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:ch03_aware/spring_Scope.xml");
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
}
