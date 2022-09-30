package cc.tianbin.springframework.factory.support.instantiation;

import cc.tianbin.springframework.factory.config.BeanDefinition;
import io.github.nibnait.common.exception.BeansException;

import java.lang.reflect.Constructor;

/**
 * 实例化一个 bean
 * Created by nibnait on 2022/09/30
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException;

}
