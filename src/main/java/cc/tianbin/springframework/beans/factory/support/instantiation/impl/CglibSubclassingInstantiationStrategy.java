package cc.tianbin.springframework.beans.factory.support.instantiation.impl;

import cc.tianbin.springframework.beans.exception.BeansException;
import cc.tianbin.springframework.beans.factory.config.BeanDefinition;
import cc.tianbin.springframework.beans.factory.support.instantiation.InstantiationStrategy;
import com.alibaba.excel.support.cglib.proxy.Enhancer;
import com.alibaba.excel.support.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * 使用 Cglib 来动态创建 Bean 对象
 * Created by nibnait on 2022/09/30
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });

        if (constructor == null) {
            return enhancer.create();
        }

        return enhancer.create(constructor.getParameterTypes(), args);
    }
}
