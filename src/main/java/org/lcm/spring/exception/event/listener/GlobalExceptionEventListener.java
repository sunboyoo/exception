package org.lcm.spring.exception.event.listener;


import org.lcm.spring.exception.event.GlobalExceptionEvent;
import org.lcm.spring.exception.event.processor.GlobalExceptionEventProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;

public class GlobalExceptionEventListener implements ApplicationListener<GlobalExceptionEvent> {
    private final GlobalExceptionEventProcessor globalExceptionEventProcessor;

    public GlobalExceptionEventListener(GlobalExceptionEventProcessor globalExceptionEventProcessor) {
        this.globalExceptionEventProcessor = globalExceptionEventProcessor;
    }

    @Override
    public void onApplicationEvent(@NonNull GlobalExceptionEvent event) {
        this.globalExceptionEventProcessor.process(event);
    }
}
