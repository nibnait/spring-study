package cc.tianbin.springframework.beans.factory;

/**
 * Created by nibnait on 2022/10/08
 */
public interface InitializingBean {

    /**
     * Bean 处理了属性填充后调用
     */
    void afterPropertiesSet() throws Exception;

}
