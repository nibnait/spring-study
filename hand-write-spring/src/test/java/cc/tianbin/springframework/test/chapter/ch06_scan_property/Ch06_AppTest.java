package cc.tianbin.springframework.test.chapter.ch06_scan_property;

import cc.tianbin.springframework.context.support.ClassPathXmlApplicationContext;
import cc.tianbin.springframework.test.chapter.ch05_aop.bean.IUserService;
import cc.tianbin.springframework.test.chapter.ch06_scan_property.bean.UserServiceImpl;
import cc.tianbin.springframework.test.chapter.ch06_scan_property.bean.UserServiceImplAutoProxy;
import cc.tianbin.springframework.test.common.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 通过注解给属性注入配置和Bean对象
 * 自动扫描Bean对象注册
 * Created by nibnait on 2022/10/xx
 */
@Slf4j
public class Ch06_AppTest {

    @Test
    public void testProperty() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:ch06_scan_property/spring-property.xml");
        UserServiceImpl userService = applicationContext.getBean(CommonConstants.USER_SERVICE, UserServiceImpl.class);
        String result = userService.queryUserInfo();
        log.info("result: {} token: {}", result, userService.getToken());
    }

    @Test
    public void testScan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:ch06_scan_property/spring-scan.xml");
        IUserService userService = applicationContext.getBean(CommonConstants.USER_SERVICE, UserServiceImpl.class);
        String result = userService.queryUserInfo();
        System.out.println("result: " + result);
    }

    @Test
    public void testScan2() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:ch06_scan_property/spring-annotation.xml");
        IUserService userService = applicationContext.getBean(CommonConstants.USER_SERVICE, UserServiceImpl.class);
        String result = userService.queryUserInfo("1001");
        System.out.println("result: " + result);
    }

    @Test
    public void testAutoProxy() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:ch06_scan_property/spring-autoproxy.xml");
        IUserService userService = applicationContext.getBean(CommonConstants.USER_SERVICE, UserServiceImplAutoProxy.class);
        String result = userService.queryUserInfo();
        System.out.println("result: " + result);
    }
}
