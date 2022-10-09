package cc.tianbin.springframework.context;

/**
 * Created by nibnait on 2022/10/09
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
