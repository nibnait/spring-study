package cc.tianbin.springframework.循环依赖.proxy;

import cc.tianbin.springframework.循环依赖.ClassA;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by nibnait on 2023/02/21
 */
@Component
public class JdkProxyBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        if (bean instanceof ClassA) {
            JdkDynamicProxy jdkDynamicProxy = new JdkDynamicProxy(bean);
            return jdkDynamicProxy.getProxy();
        }
        return bean;
    }

}
