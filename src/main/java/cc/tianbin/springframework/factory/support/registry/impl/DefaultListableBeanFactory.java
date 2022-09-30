package cc.tianbin.springframework.factory.support.registry.impl;

import cc.tianbin.springframework.factory.config.BeanDefinition;
import cc.tianbin.springframework.factory.support.AbstractAutowireCapableBeanFactory;
import cc.tianbin.springframework.factory.support.registry.BeanDefinitionRegistry;
import io.github.nibnait.common.exception.BeansException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 核心实现类
 * Created by nibnait on 2022/09/19
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No bean named {} is defined", beanName);
        }
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

}
