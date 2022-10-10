package cc.tianbin.springframework.aop;

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

    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }
}
