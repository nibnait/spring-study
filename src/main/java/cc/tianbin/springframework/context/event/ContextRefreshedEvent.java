package cc.tianbin.springframework.context.event;

import cc.tianbin.springframework.context.ApplicationContextEvent;

/**
 * Created by nibnait on 2022/10/09
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
