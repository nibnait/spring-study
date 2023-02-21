package cc.tianbin.springframework.context.support;

import cc.tianbin.springframework.beans.factory.support.registry.impl.DefaultListableBeanFactory;
import cc.tianbin.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 上下文中 对配置信息的加载
 * Created by nibnait on 2022/10/07
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
