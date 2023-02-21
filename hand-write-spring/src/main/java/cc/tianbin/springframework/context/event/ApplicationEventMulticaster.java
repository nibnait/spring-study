package cc.tianbin.springframework.context.event;

import cc.tianbin.springframework.context.ApplicationEvent;
import cc.tianbin.springframework.context.ApplicationListener;

/**
 * 事件广播器
 * Created by nibnait on 2022/10/09
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);

}
