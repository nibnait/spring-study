package cc.tianbin.springframework.beans.factory.aware;

import cc.tianbin.springframework.beans.exception.BeansException;
import cc.tianbin.springframework.beans.factory.BeanFactory;

/**
 * Created by nibnait on 2022/10/08
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
