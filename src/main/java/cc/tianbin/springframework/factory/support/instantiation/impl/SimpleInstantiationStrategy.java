package cc.tianbin.springframework.factory.support.instantiation.impl;

import cc.tianbin.springframework.factory.config.BeanDefinition;
import cc.tianbin.springframework.factory.support.instantiation.InstantiationStrategy;
import io.github.nibnait.common.exception.BeansException;
import io.github.nibnait.common.utils.DataUtils;

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
            throw new BeansException(DataUtils.format("Failed to instantiate [{}]", beanClass.getName()), e);
        }
    }

}
