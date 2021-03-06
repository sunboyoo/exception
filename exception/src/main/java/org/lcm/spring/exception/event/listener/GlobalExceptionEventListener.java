package org.lcm.spring.exception.event.listener;


import org.lcm.spring.exception.event.GlobalExceptionEvent;
import org.lcm.spring.exception.event.processor.GlobalExceptionEventProcessor;
import org.springframework.context.ApplicationListener;

/**
 * GlobalExceptionEventListener 可以留给客户模块实现
 */
public class GlobalExceptionEventListener implements ApplicationListener<GlobalExceptionEvent> {
    private final GlobalExceptionEventProcessor globalExceptionEventProcessor;

    public GlobalExceptionEventListener(GlobalExceptionEventProcessor globalExceptionEventProcessor) {
        this.globalExceptionEventProcessor = globalExceptionEventProcessor;
    }

    @Override
    public void onApplicationEvent(GlobalExceptionEvent event) {
        this.globalExceptionEventProcessor.process(event);
    }
}
