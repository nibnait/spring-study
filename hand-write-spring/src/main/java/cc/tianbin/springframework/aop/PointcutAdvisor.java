package cc.tianbin.springframework.aop;

/**
 * Created by nibnait on 2022/10/10
 */
public interface PointcutAdvisor extends Advisor {

    /**
     * Pointcut 用于获取 JoinPoint
     * 而 Advice 决定于 JoinPoint 执行什么操作
     */
    Pointcut getPointcut();

}
