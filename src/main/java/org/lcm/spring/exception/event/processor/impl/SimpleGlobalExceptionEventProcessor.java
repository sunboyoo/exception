package org.lcm.spring.exception.event.processor.impl;

import org.lcm.spring.exception.event.GlobalExceptionEvent;
import org.lcm.spring.exception.event.processor.GlobalExceptionEventProcessor;

public class SimpleGlobalExceptionEventProcessor implements GlobalExceptionEventProcessor {
    @Override
    public void process(GlobalExceptionEvent event) {
        System.out.println("-----------------------SimpleGlobalExceptionEventProcessor--------------------------------");
        System.out.println(event.getLocalDateTime());
        System.out.println(event.getException());
        System.out.println(event.getRequest());
        System.out.println(event.getSession());
    }
}
