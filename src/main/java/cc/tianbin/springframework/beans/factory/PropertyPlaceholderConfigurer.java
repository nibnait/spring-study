package cc.tianbin.springframework.beans.factory;

import cc.tianbin.springframework.beans.PropertyValue;
import cc.tianbin.springframework.beans.PropertyValues;
import cc.tianbin.springframework.beans.exception.BeansException;
import cc.tianbin.springframework.beans.factory.config.BeanDefinition;
import cc.tianbin.springframework.beans.factory.config.BeanFactoryPostProcessor;
import cc.tianbin.springframework.core.io.resource.Resource;
import cc.tianbin.springframework.core.io.resourceloader.impl.DefaultResourceLoader;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by nibnait on 2022/10/11
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 加载属性文件
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);

                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) {
                        continue;
                    }

                    String strVal = (String) value;
                    StringBuilder buffer = new StringBuilder(strVal);
                    int startIndex = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int stopIndex = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                    if (startIndex != -1 && stopIndex != -1 && startIndex < stopIndex) {
                        String propKey = strVal.substring(startIndex + 2, stopIndex);
                        String propVal = properties.getProperty(propKey);
                        buffer.replace(startIndex, stopIndex + 1, propVal);
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), buffer.toString()));
                    }
                }
            }
        } catch (IOException e) {
            throw new BeansException(e, "Could not load properties");
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
