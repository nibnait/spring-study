package cc.tianbin.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by nibnait on 2022/09/17
 */
public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }


}
