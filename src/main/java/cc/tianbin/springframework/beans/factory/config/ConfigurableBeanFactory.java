package cc.tianbin.springframework.beans.factory.config;

import cc.tianbin.springframework.beans.factory.HierarchicalBeanFactory;
import cc.tianbin.springframework.beans.factory.support.registry.SingletonBeanRegistry;
import cc.tianbin.springframework.util.StringValueResolver;

/**
 * Created by nibnait on 2022/10/06
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);
}
