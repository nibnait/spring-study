package cc.tianbin.springframework.context.support;

import cc.tianbin.springframework.beans.exception.BeansException;
import cc.tianbin.springframework.beans.factory.ConfigurableListableBeanFactory;
import cc.tianbin.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 获取 Bean 工厂和加载资源
 * Created by nibnait on 2022/10/07
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory tmpBeanFactory = createBeanFactory();
        loadBeanDefinitions(tmpBeanFactory);
        this.beanFactory = tmpBeanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
