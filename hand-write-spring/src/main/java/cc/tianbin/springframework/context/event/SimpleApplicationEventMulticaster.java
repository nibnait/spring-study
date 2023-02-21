package cc.tianbin.springframework.context.event;

import cc.tianbin.springframework.beans.factory.BeanFactory;
import cc.tianbin.springframework.context.ApplicationEvent;
import cc.tianbin.springframework.context.ApplicationListener;

/**
 * Created by nibnait on 2022/10/09
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
