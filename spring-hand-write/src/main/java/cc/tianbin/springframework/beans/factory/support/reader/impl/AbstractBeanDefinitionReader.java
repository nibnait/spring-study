package cc.tianbin.springframework.beans.factory.support.reader.impl;

import cc.tianbin.springframework.beans.factory.support.reader.BeanDefinitionReader;
import cc.tianbin.springframework.beans.factory.support.registry.BeanDefinitionRegistry;
import cc.tianbin.springframework.core.io.resourceloader.impl.DefaultResourceLoader;
import cc.tianbin.springframework.core.io.resourceloader.ResourceLoader;

/**
 * Created by nibnait on 2022/10/06
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

}
