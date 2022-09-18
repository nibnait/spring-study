package cc.tianbin.springframework;

/**
 * Created by nibnait on 2022/09/17
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
