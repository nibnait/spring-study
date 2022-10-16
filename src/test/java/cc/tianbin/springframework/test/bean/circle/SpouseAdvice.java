package cc.tianbin.springframework.test.bean.circle;

import cc.tianbin.springframework.aop.advice.BeforeAdvice;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * Created by nibnait on 2022/10/15
 */
@Slf4j
public class SpouseAdvice implements BeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        log.info("关怀小两口(切面): {}", method);
    }
}
