package cc.tianbin.springframework.test.chapter.ch04_event;

import cc.tianbin.springframework.context.support.ClassPathXmlApplicationContext;
import cc.tianbin.springframework.test.chapter.ch04_event.model.CustomEvent;
import org.junit.Test;

/**
 * 基于观察者实现，容器事件和事件监听器
 * Created by nibnait on 2022/10/09
 */
public class Ch04_AppTest {

    @Test
    public void testEvent() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:ch04_event/spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 111L, "成功了！"));

        applicationContext.registerShutdownHook();
    }
}
