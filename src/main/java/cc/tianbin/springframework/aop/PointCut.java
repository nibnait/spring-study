package cc.tianbin.springframework.aop;

/**
 * Created by nibnait on 2022/10/10
 */
public interface PointCut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
