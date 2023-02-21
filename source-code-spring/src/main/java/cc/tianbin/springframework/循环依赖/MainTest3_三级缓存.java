package cc.tianbin.springframework.循环依赖;

import cc.tianbin.springframework.循环依赖.proxy.JdkProxyBeanPostProcessor;
import org.junit.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by nibnait on 2023/02/21
 */
public class MainTest3_三级缓存 {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    // 一级缓存：完整的成品对象
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    // 二级缓存：不完整的半成品对象
    private Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>();

    // 三级缓存：
    private Map<String, ObjectFactory<?>> singletonFactories = new ConcurrentHashMap<>();

    // 当前循环依赖的 bean 是否正在创建中
    private Set<String> singletonCurrentlyCreation = new HashSet<>();

    private void loadBeanDefinitions() {
        RootBeanDefinition abd = new RootBeanDefinition(ClassA.class);
        beanDefinitionMap.put("classA", abd);

        RootBeanDefinition bbd = new RootBeanDefinition(ClassB.class);
        beanDefinitionMap.put("classB", bbd);
    }

    @Test
    public void main() throws Exception {
        loadBeanDefinitions();
        for (String beanName : beanDefinitionMap.keySet()) {
            getBean(beanName);
        }

        ClassA a = (ClassA) getBean("classA");
        a.execute();
    }

    private Object getBean(String beanName) throws Exception {
        Object singleton = getSingleton(beanName);
        if (singleton != null) {
            return singleton;
        }

        if (!singletonCurrentlyCreation.contains(beanName)) {
            singletonCurrentlyCreation.add(beanName);
        }

        // 1. bean 实例化
        RootBeanDefinition bd = (RootBeanDefinition) beanDefinitionMap.get(beanName);
        Class<?> clazz = bd.getBeanClass();
        Object instance = clazz.newInstance();
        earlySingletonObjects.put(beanName, instance);

        // 4. AOP 代理对象
        // 延迟添加三级缓存
        if (singletonCurrentlyCreation.contains(beanName)) {
            singletonFactories.put(beanName, () -> getEarlyBeanReference(beanName));
        }


        // 2. 属性填充
        for (Field field : clazz.getDeclaredFields()) {
            Autowired annotation = field.getAnnotation(Autowired.class);
            if (annotation != null) {
                field.setAccessible(true);
                String name = field.getName();
                Object dep = getBean(name);
                field.set(instance, dep);
            }
        }

        // 3. 初始化


        // 5. 添加到缓存
        singletonObjects.put(beanName, instance);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
        return instance;
    }

    /**
     * @see AbstractAutowireCapableBeanFactory#getEarlyBeanReference(java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Object)
     */
    private Object getEarlyBeanReference(String beanName) {
        return new JdkProxyBeanPostProcessor()
                .getEarlyBeanReference(earlySingletonObjects.get(beanName), beanName);
    }

    /**
     * @see DefaultSingletonBeanRegistry#getSingleton(java.lang.String, boolean)
     */
    private Object getSingleton(String beanName) {
        Object singletonObject = singletonObjects.get(beanName);
        // 一级缓存中没有 && 正在创建
        if (singletonObject == null && singletonCurrentlyCreation.contains(beanName)) {
            singletonObject = earlySingletonObjects.get(beanName);
            if (singletonObject == null) {
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if (singletonFactory != null) {
                    // 这里调的就是 getEarlyBeanReference 方法，返回的是一个代理对象
                    singletonObject = singletonFactory.getObject();
                    // 放到二级缓存中，替换【之前的 bean 实例化时，初始生成的原始对象】
                    earlySingletonObjects.put(beanName, singletonObject);
                }
            }
        }
        return singletonObject;
    }

}
