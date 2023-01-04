package cc.tianbin.springframework.test.chapter.ch04_event.listener;

import cc.tianbin.springframework.context.ApplicationListener;
import cc.tianbin.springframework.context.event.ContextRefreshedEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by nibnait on 2022/10/09
 */
@Slf4j
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("刷新事件：{}", this.getClass().getName());
    }
}
