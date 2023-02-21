package cc.tianbin.springframework.test.chapter.ch07_circular_references;

import cc.tianbin.springframework.context.support.ClassPathXmlApplicationContext;
import cc.tianbin.springframework.test.chapter.ch07_circular_references.bean.Husband;
import cc.tianbin.springframework.test.chapter.ch07_circular_references.bean.Wife;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by nibnait on 2022/10/15
 */
@Slf4j
public class Ch07_AppTest {

    @Test
    public void test_circular() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:circular_references/spring.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        Wife wife = applicationContext.getBean("wife", Wife.class);
        log.info("老公的媳妇：{}", husband.queryWife());
        log.info("媳妇的老公：{}", wife.queryHusband());
    }

}
