package cc.tianbin.springframework.test;

import cc.tianbin.springframework.context.support.ClassPathXmlApplicationContext;
import cc.tianbin.springframework.test.event.CustomEvent;
import org.junit.Test;

/**
 * Created by nibnait on 2022/10/09
 */
public class AppEventTest {

    @Test
    public void testEvent() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_event.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 111L, "成功了！"));

        applicationContext.registerShutdownHook();
    }
}
