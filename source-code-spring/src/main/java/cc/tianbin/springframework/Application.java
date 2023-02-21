package cc.tianbin.springframework;

import cc.tianbin.springframework.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by nibnait on 2023/02/21
 */
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        TestBean bean = (TestBean) applicationContext.getBean("testBean");

        System.out.println(bean.getName());
    }

}
