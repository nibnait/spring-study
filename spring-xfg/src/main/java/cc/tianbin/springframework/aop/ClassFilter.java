package cc.tianbin.springframework.aop;

/**
 * 定义类匹配类，用于切点找到给定的接口和目标类。
 * Created by nibnait on 2022/10/10
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);

}
