package cc.tianbin.springframework.test;

import cc.tianbin.springframework.aop.AdvisedSupport;
import cc.tianbin.springframework.aop.MethodMatcher;
import cc.tianbin.springframework.aop.TargetSource;
import cc.tianbin.springframework.aop.aspectj.AspectJExpressionPointcut;
import cc.tianbin.springframework.aop.framework.Cglib2AopProxy;
import cc.tianbin.springframework.aop.framework.JdkDynamicAopProxy;
import cc.tianbin.springframework.aop.framework.ReflectiveMethodInvocation;
import cc.tianbin.springframework.test.bean.IUserService;
import cc.tianbin.springframework.test.bean.UserService;
import cc.tianbin.springframework.test.bean.UserServiceImplAOP;
import cc.tianbin.springframework.test.bean.UserServiceInterceptor;
import io.github.nibnait.common.utils.date.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by nibnait on 2022/10/10
 */
@Slf4j
public class AopTest {

    @Test
    public void testProxyMethod() {
        // 目标对象（可以替换成任何目标对象）
        Object targetObj = new UserServiceImplAOP();
        // AOP 代理
        IUserService proxy = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {
            // 方法匹配器
            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* cc.tianbin.springframework.test.bean.IUserService.*(..))");

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodMatcher.matches(method, targetObj.getClass())) {
                    // 方法拦截器
                    MethodInterceptor methodInterceptor = invocation -> {
                        Long start = DateUtils.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        } finally {
                            log.info("监控 - Begin By AOP");
                            log.info("方法名称: {}", invocation.getMethod().getName());
                            log.info("方法耗时: {}ms", DateUtils.currentTimeMillis() - start);
                            log.info("监控 - End\r\n");
                        }
                    };
                    // 反射调用
                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
                }
                return method.invoke(targetObj, args);
            }
        });
        String result = proxy.queryUserInfo();
        log.info("result: {}", result);
    }

    @Test
    public void testAOP() throws NoSuchMethodException{
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* cc.tianbin.springframework.test.bean.UserService.*(..))");
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println((pointcut.matches(clazz)));
        System.out.println(pointcut.matches(method, clazz));
    }

    @Test
    public void testDynamic() {
        // 目标对象
        UserServiceImplAOP userService = new UserServiceImplAOP();

        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* cc.tianbin.springframework.test.bean.IUserService.*(..))"));

        // jdk 代理对象
        IUserService jdkProxy = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        log.info("jdkProxy: {}", jdkProxy.queryUserInfo());

        // cglib 代理对象
        IUserService cglibProxy = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        log.info("cglibProxy: {}", cglibProxy.queryUserInfo());
    }

}
