package cc.tianbin.springframework.aop;

/**
 * Created by nibnait on 2022/10/10
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
