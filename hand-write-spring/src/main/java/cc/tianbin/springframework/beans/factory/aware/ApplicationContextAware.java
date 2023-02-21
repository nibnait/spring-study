package cc.tianbin.springframework.beans.factory.aware;

import cc.tianbin.springframework.beans.exception.BeansException;
import cc.tianbin.springframework.context.ApplicationContext;

/**
 * Created by nibnait on 2022/10/08
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
