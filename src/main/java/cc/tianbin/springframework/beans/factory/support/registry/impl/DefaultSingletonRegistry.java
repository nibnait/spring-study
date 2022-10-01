package cc.tianbin.springframework.beans.factory.support.registry.impl;

import cc.tianbin.springframework.beans.factory.support.registry.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例对象池
 * Created by nibnait on 2022/09/18
 */
public class DefaultSingletonRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingletonBean(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
