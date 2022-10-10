package cc.tianbin.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * Created by nibnait on 2022/10/10
 */
public interface Advisor {

    Advice getAdvice();

}
