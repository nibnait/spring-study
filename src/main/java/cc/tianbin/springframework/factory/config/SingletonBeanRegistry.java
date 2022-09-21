package cc.tianbin.springframework.factory.config;

/**
 * Created by nibnait on 2022/09/18
 */
public interface SingletonBeanRegistry {

    Object getSingletonBean(String beanName);

}
