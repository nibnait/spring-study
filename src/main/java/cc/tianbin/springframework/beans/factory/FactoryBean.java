package cc.tianbin.springframework.beans.factory;

/**
 * Created by nibnait on 2022/10/09
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}
