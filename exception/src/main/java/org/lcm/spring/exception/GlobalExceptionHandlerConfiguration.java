package org.lcm.spring.exception;

import org.lcm.spring.exception.controlleradvice.GlobalExceptionHandlerControllerAdvice;
import org.lcm.spring.exception.event.listener.GlobalExceptionEventListener;
import org.lcm.spring.exception.event.processor.GlobalExceptionEventProcessor;
import org.springframework.context.annotation.Import;

/**
 * 向容器中注册组件
 */
@Import({
        GlobalExceptionHandlerControllerAdvice.class,
        GlobalExceptionEventProcessor.class,
        GlobalExceptionEventListener.class
})
public class GlobalExceptionHandlerConfiguration {

}
