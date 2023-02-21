package cc.tianbin.springframework.beans.factory.config;

/**
 * Created by nibnait on 2022/10/01
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
