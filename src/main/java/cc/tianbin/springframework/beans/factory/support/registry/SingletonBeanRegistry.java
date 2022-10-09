package cc.tianbin.springframework.beans.factory.support.registry;

/**
 * Created by nibnait on 2022/09/18
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}
