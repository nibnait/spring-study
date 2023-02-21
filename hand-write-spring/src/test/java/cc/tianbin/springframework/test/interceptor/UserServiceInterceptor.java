package cc.tianbin.springframework.test.interceptor;

import io.github.nibnait.common.utils.date.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by nibnait on 2022/10/10
 */
@Slf4j
public class UserServiceInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Long start = DateUtils.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            log.info("监控 - Begin By AOP");
            log.info("方法名称: {}", invocation.getMethod().getName());
            log.info("方法耗时: {}ms", DateUtils.currentTimeMillis() - start);
            log.info("监控 - End");
        }
    }
}
