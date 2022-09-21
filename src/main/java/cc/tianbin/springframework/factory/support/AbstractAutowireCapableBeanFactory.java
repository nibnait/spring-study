package cc.tianbin.springframework.factory.support;

import cc.tianbin.springframework.factory.config.BeanDefinition;
import io.github.nibnait.common.exception.BeansException;

/**
 * 实例化 bean 工厂
 * Created by nibnait on 2022/09/19
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 实例化一个bean。然后加到单例对象池中
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        return bean;
    }

}
