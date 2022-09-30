package cc.tianbin.springframework.factory.support;

import cc.tianbin.springframework.factory.config.BeanDefinition;
import cc.tianbin.springframework.factory.support.instantiation.InstantiationStrategy;
import cc.tianbin.springframework.factory.support.instantiation.impl.CglibSubclassingInstantiationStrategy;
import io.github.nibnait.common.exception.BeansException;

import java.lang.reflect.Constructor;

/**
 * 实例化 bean 工厂
 * Created by nibnait on 2022/09/19
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy;

    public AbstractAutowireCapableBeanFactory() {
        this.instantiationStrategy = new CglibSubclassingInstantiationStrategy();
    }

    /**
     * 实例化一个bean。然后加到单例对象池中
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean = null;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Constructor constructorToUse = null;
        Class beanClass = beanDefinition.getBeanClass();
        for (Constructor declaredConstructor : beanClass.getDeclaredConstructors()) {
            if (args != null && declaredConstructor.getParameterTypes().length == args.length) {
                constructorToUse = declaredConstructor;
                break;
            }
        }

        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
