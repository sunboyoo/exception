package org.lcm.spring.exception.event.processor;

import org.lcm.spring.exception.event.GlobalExceptionEvent;

/**
 * GlobalExceptionEventProcessor 可以留给子类实现
 */
public interface GlobalExceptionEventProcessor {
    void process(GlobalExceptionEvent event);
}
