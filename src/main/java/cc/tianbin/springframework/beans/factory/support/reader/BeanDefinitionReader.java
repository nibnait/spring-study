package cc.tianbin.springframework.beans.factory.support.reader;

import cc.tianbin.springframework.beans.exception.BeansException;
import cc.tianbin.springframework.beans.factory.support.registry.BeanDefinitionRegistry;
import cc.tianbin.springframework.core.io.resourceloader.ResourceLoader;
import cc.tianbin.springframework.core.io.resource.Resource;

/**
 * Created by nibnait on 2022/10/06
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    int loadBeanDefinitions(Resource resource) throws BeansException;

    int loadBeanDefinitions(Resource... resources) throws BeansException;

    int loadBeanDefinitions(String location) throws BeansException;

    int loadBeanDefinitions(String... locations) throws BeansException;

}
