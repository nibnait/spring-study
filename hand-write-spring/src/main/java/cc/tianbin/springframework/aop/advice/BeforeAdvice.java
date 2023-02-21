package cc.tianbin.springframework.aop.advice;

import org.aopalliance.aop.Advice;

import java.lang.reflect.Method;

/**
 * Created by nibnait on 2022/10/10
 */
public interface BeforeAdvice extends Advice {

    void before(Method method, Object[] args, Object target) throws Throwable;

}
