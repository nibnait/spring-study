package cc.tianbin.springframework.beans.factory.config;

import cc.tianbin.springframework.beans.exception.BeansException;

/**
 * Created by nibnait on 2022/10/15
 */
public interface ObjectFactory<T> {

    T getObject() throws BeansException;

}
