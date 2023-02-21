package cc.tianbin.springframework.循环依赖;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by nibnait on 2023/02/21
 */
public class MainTest2_二级缓存 {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    // 一级缓存：完整的成品对象
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    // 二级缓存：不完整的半成品对象，，与 一级缓存 区分一下。。
    private Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>();

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

    }

    private Object getBean(String beanName) throws Exception {
        Object singleton = getSingleton(beanName);
        if (singleton != null) {
            return singleton;
        }

        // 1. bean 实例化
        RootBeanDefinition bd = (RootBeanDefinition) beanDefinitionMap.get(beanName);
        Class<?> clazz = bd.getBeanClass();
        Object instance = clazz.newInstance();
        earlySingletonObjects.put(beanName, instance);

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

        // 3. 添加到缓存
        singletonObjects.put(beanName, instance);
        earlySingletonObjects.remove(beanName);
        return instance;
    }

    private Object getSingleton(String beanName) {
        Object singletonObject = singletonObjects.get(beanName);
        if (singletonObject == null) {
            singletonObject = earlySingletonObjects.get(beanName);
        }
        return singletonObject;
    }

}
