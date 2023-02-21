package cc.tianbin.springframework.aop.framework.adapter;

import cc.tianbin.springframework.aop.advice.AfterAdvice;
import cn.hutool.core.lang.Assert;
import lombok.NoArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 方法拦截器
 * Created by nibnait on 2022/10/10
 */
@NoArgsConstructor
public class MethodAfterAdviceInterceptor implements MethodInterceptor {

    private AfterAdvice advice;

    public MethodAfterAdviceInterceptor(AfterAdvice advice) {
        Assert.notNull(advice, "Advice must not be null");
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        this.advice.after(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        return methodInvocation.proceed();
    }
}
