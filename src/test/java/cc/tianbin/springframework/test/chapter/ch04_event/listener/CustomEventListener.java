package cc.tianbin.springframework.test.chapter.ch04_event.listener;

import cc.tianbin.springframework.context.ApplicationListener;
import cc.tianbin.springframework.test.chapter.ch04_event.model.CustomEvent;
import io.github.nibnait.common.utils.date.DateTimeConvertUtils;
import io.github.nibnait.common.utils.date.DateUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by nibnait on 2022/10/09
 */
@Slf4j
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        log.info("收到 {} 消息；时间: {}", event.getSource(), DateTimeConvertUtils.timeStamp2DateTimeString(DateUtils.currentTimeMillis()));
        log.info("消息 {}:{}", event.getId(), event.getMessage());
    }
}
