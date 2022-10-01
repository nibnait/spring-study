package cc.tianbin.springframework.beans.factory.support;

import cc.tianbin.springframework.beans.PropertyValue;
import cc.tianbin.springframework.beans.PropertyValues;
import cc.tianbin.springframework.beans.exception.BeansException;
import cc.tianbin.springframework.beans.factory.config.BeanDefinition;
import cc.tianbin.springframework.beans.factory.config.BeanReference;
import cc.tianbin.springframework.beans.factory.support.instantiation.InstantiationStrategy;
import cc.tianbin.springframework.beans.factory.support.instantiation.impl.CglibSubclassingInstantiationStrategy;
import cn.hutool.core.bean.BeanUtil;

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
            // 实例化 bean
            bean = createBeanInstance(beanName, beanDefinition, args);
            // 填充 bean 属性
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException(e, "Instantiation of bean failed");
        }

        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 实例化 bean
     */
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

    /**
     * 填充 bean 属性
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        if (propertyValues == null || propertyValues.isEmpty()) {
            return;
        }

        try {
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // A 依赖 B，获取 B 的实例化
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }

                // 属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException(e, "error setting {} property values", beanName);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
