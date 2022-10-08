package cc.tianbin.springframework.beans.factory;

/**
 * Created by nibnait on 2022/10/08
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String name);

}
