package cc.tianbin.springframework.aop;

import cc.tianbin.springframework.util.ClassUtils;

/**
 * Created by nibnait on 2022/10/10
 */
public class TargetSource {

    private Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    /**
     * 获取 target 对象的接口信息
     */
    public Class<?>[] getTargetClass() {
        Class<?> clazz = this.target.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        return clazz.getInterfaces();
    }
}
