package cc.tianbin.springframework.beans.factory.aware;

/**
 * Created by nibnait on 2022/10/08
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);

}
