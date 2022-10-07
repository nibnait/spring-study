package cc.tianbin.springframework.beans.factory;

import cc.tianbin.springframework.beans.exception.BeansException;

/**
 * Created by nibnait on 2022/09/17
 */
public interface BeanFactory {

    Object getBean(String name);

    Object getBean(String name, Object... args);

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

}
