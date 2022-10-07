package cc.tianbin.springframework.test.common;

import cc.tianbin.springframework.beans.PropertyValue;
import cc.tianbin.springframework.beans.PropertyValues;
import cc.tianbin.springframework.beans.exception.BeansException;
import cc.tianbin.springframework.beans.factory.ConfigurableListableBeanFactory;
import cc.tianbin.springframework.beans.factory.config.BeanDefinition;
import cc.tianbin.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * Created by nibnait on 2022/10/07
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition(CommonConstants.USER_SERVICE);
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }
}
