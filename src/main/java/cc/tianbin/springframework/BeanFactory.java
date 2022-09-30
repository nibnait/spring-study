package cc.tianbin.springframework;

/**
 * Created by nibnait on 2022/09/17
 */
public interface BeanFactory {

    Object getBean(String beanName);

    Object getBean(String beanName, Object... args);

}
