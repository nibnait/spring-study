package cc.tianbin.springframework.context;

import cc.tianbin.springframework.beans.exception.BeansException;

/**
 * Created by nibnait on 2022/10/06
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     */
    void refresh() throws BeansException;

}
