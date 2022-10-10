package cc.tianbin.springframework.aop;

import lombok.Data;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * Created by nibnait on 2022/10/10
 */
@Data
public class AdvisedSupport {

    // ProxyConfig
    private boolean proxyTargetClass = false;

    // 被代理的目标对象
    private TargetSource targetSource;

    // 方法拦截器
    private MethodInterceptor methodInterceptor;

    // 方法匹配器（检查目标方法是否符合通知条件）
    private MethodMatcher methodMatcher;

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

}
