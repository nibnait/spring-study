package cc.tianbin.springframework.factory.config;

/**
 * Created by nibnait on 2022/09/17
 */
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }
}
