package cc.tianbin.springframework.beans.factory.support.instantiation.impl;

import cc.tianbin.springframework.beans.exception.BeansException;
import cc.tianbin.springframework.beans.factory.config.BeanDefinition;
import cc.tianbin.springframework.beans.factory.support.instantiation.InstantiationStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 使用 Java 自带的 DeclaredConstructor 方法，实例化一个 bean
 * Created by nibnait on 2022/09/30
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException {
        Class beanClass = beanDefinition.getBeanClass();
        try {
            if (constructor == null) {
                return beanClass.getDeclaredConstructor().newInstance();
            }

            return beanClass.getDeclaredConstructor(constructor.getParameterTypes()).newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new BeansException(e, "Failed to instantiate [{}]", beanClass.getName());
        }
    }

}
