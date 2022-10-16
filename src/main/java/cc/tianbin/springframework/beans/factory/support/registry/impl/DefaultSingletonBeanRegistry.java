package cc.tianbin.springframework.beans.factory.support.registry.impl;

import cc.tianbin.springframework.beans.exception.BeansException;
import cc.tianbin.springframework.beans.factory.DisposableBean;
import cc.tianbin.springframework.beans.factory.config.ObjectFactory;
import cc.tianbin.springframework.beans.factory.support.registry.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例对象池
 * Created by nibnait on 2022/09/18
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected static final Object NULL_OBJECT = new Object();

    // 一级缓存，普通（成品）对象
    protected Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    // 二级缓存，提前暴露对象，没有完全实例化的（半成品）对象
    private final Map<String, Object> earlySingletonObjects = new HashMap<String, Object>();

    // 三级缓存，存放代理对象（单例工厂对象）
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<String, ObjectFactory<?>>();

    // 待销毁的对象
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        Object singletonObject = singletonObjects.get(beanName);
        if (singletonObject == null) {
            // 从二级缓存中获取
            singletonObject = earlySingletonObjects.get(beanName);
            if (singletonObject == null) {
                // 从三级缓存中获取
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if (singletonFactory != null) {
                    // 此时拿出来的是一个代理对象
                    singletonObject = singletonFactory.getObject();
                    earlySingletonObjects.put(beanName, singletonObject);
                    singletonFactories.remove(beanName);
                }
            }
        }

        return singletonObject;
    }

    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
        if (!this.singletonObjects.containsKey(beanName)) {
            this.singletonFactories.put(beanName, singletonFactory);
            this.earlySingletonObjects.remove(beanName);
        }
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
