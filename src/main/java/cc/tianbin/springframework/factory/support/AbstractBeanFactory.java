package cc.tianbin.springframework.factory.support;

import cc.tianbin.springframework.BeanFactory;
import cc.tianbin.springframework.factory.config.BeanDefinition;
import cc.tianbin.springframework.factory.support.registry.impl.DefaultSingletonRegistry;

/**
 * 模板方法
 * Created by nibnait on 2022/09/19
 */
public abstract class AbstractBeanFactory extends DefaultSingletonRegistry implements BeanFactory {

    /**
     * getBean
     */
    @Override
    public Object getBean(String beanName) {
        return doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName, args);
    }

    protected Object doGetBean(String beanName, Object[] args) {
        Object bean = getSingletonBean(beanName);
        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition, args);
    }

    /**
     * getBeanDefinition
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);

    /**
     * createBean
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);

}
