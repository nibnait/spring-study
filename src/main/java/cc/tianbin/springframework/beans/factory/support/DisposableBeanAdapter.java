package cc.tianbin.springframework.beans.factory.support;

import cc.tianbin.springframework.beans.exception.BeansException;
import cc.tianbin.springframework.beans.factory.DisposableBean;
import cc.tianbin.springframework.beans.factory.config.BeanDefinition;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;

/**
 * 定义销毁方法适配器
 * Created by nibnait on 2022/10/08
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 实现接口 DisposableBean
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        // 配置信息 destroy-method
        if (StringUtils.isNotBlank(destroyMethodName)
                && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (destroyMethod == null) {
                throw new BeansException("Could not find a destroy method named '{}' on bean with name {}", destroyMethodName, beanName);
            }
            destroyMethod.invoke(bean);
        }
    }
}
