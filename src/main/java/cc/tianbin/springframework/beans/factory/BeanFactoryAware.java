package cc.tianbin.springframework.beans.factory;

import cc.tianbin.springframework.beans.exception.BeansException;

/**
 * Created by nibnait on 2022/10/08
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
