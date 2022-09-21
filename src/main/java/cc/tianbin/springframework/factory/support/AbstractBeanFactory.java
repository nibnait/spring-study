package cc.tianbin.springframework.factory.support;

import cc.tianbin.springframework.BeanFactory;
import cc.tianbin.springframework.factory.config.BeanDefinition;

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
        Object bean = getSingletonBean(beanName);
        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }

    /**
     * getBeanDefinition
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);

    /**
     * createBean
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);

}
