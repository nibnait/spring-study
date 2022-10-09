package cc.tianbin.springframework.beans.factory.support.registry.impl;

import cc.tianbin.springframework.beans.exception.BeansException;
import cc.tianbin.springframework.beans.factory.DisposableBean;
import cc.tianbin.springframework.beans.factory.support.registry.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例对象池
 * Created by nibnait on 2022/09/18
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected static final Object NULL_OBJECT = new Object();

    private Map<String, Object> singletonObjects = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        Object[] disposableBeanNames = this.disposableBeans.keySet().toArray();
        for (Object beanName : disposableBeanNames) {
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException(e, "Destroy method on bean with name '{}' threw an exception", beanName);
            }
        }

    }
}
