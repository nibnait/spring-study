package cc.tianbin.springframework.beans.factory;

import cc.tianbin.springframework.beans.exception.BeansException;

import java.util.Map;

/**
 * Created by nibnait on 2022/10/06
 */
public interface ListableBeanFactory {

    /**
     * 按照类型返回 Bean 实例
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();

}
