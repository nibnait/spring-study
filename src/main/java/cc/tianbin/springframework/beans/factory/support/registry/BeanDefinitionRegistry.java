package cc.tianbin.springframework.beans.factory.support.registry;

import cc.tianbin.springframework.beans.factory.config.BeanDefinition;

/**
 * Created by nibnait on 2022/09/21
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册 BeanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
