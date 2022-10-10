package cc.tianbin.springframework.beans.factory.config;

import cc.tianbin.springframework.beans.exception.BeansException;

/**
 * Created by nibnait on 2022/10/10
 */
public interface InstantiationAwareBeanPostProcess extends BeanPostProcessor {

    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

}
