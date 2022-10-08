package cc.tianbin.springframework.context.support;

import cc.tianbin.springframework.beans.exception.BeansException;
import cc.tianbin.springframework.beans.factory.ConfigurableListableBeanFactory;
import cc.tianbin.springframework.beans.factory.support.registry.impl.DefaultListableBeanFactoryBean;

/**
 * 获取 Bean 工厂和加载资源
 * Created by nibnait on 2022/10/07
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactoryBean beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactoryBean tmpBeanFactory = createBeanFactory();
        loadBeanDefinitions(tmpBeanFactory);
        this.beanFactory = tmpBeanFactory;
    }

    private DefaultListableBeanFactoryBean createBeanFactory() {
        return new DefaultListableBeanFactoryBean();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactoryBean beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
