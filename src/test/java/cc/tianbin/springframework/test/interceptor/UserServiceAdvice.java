package cc.tianbin.springframework.test.interceptor;

import cc.tianbin.springframework.aop.advice.AfterAdvice;
import cc.tianbin.springframework.aop.advice.BeforeAdvice;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * Created by nibnait on 2022/10/10
 */
@Slf4j
public class UserServiceAdvice implements BeforeAdvice, AfterAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        log.info("拦截方法 {} before", method.getName());
    }

    @Override
    public void after(Method method, Object[] args, Object target) throws Throwable {
        log.info("拦截方法 {} after", method.getName());
    }

}
