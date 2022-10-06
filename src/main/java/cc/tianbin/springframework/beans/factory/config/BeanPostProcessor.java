package cc.tianbin.springframework.beans.factory.config;

import cc.tianbin.springframework.beans.exception.BeansException;

/**
 * Created by nibnait on 2022/10/06
 */
public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}
