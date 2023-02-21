package cc.tianbin.springframework.context;

import java.util.EventListener;

/**
 * Created by nibnait on 2022/10/09
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);

}
