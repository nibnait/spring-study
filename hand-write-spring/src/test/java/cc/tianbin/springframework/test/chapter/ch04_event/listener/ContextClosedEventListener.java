package cc.tianbin.springframework.test.chapter.ch04_event.listener;

import cc.tianbin.springframework.context.ApplicationListener;
import cc.tianbin.springframework.context.event.ContextClosedEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by nibnait on 2022/10/09
 */
@Slf4j
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        log.info("关闭事件: {}", this.getClass().getName());
    }
}
