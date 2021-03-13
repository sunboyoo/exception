package org.lcm.spring.exception.event.processor;

import org.lcm.spring.exception.controlleradvice.ExceptionResponseBody;
import org.lcm.spring.exception.event.GlobalExceptionEvent;

import java.util.Arrays;
import java.util.List;

/**
 * GlobalExceptionEventProcessor 可以留给客户模块实现
 */
public class GlobalExceptionEventProcessor {

    public void process(GlobalExceptionEvent event) {
        ExceptionResponseBody exceptionResponseBody = event.getExceptionResponseBody();
        Exception exception = (Exception) exceptionResponseBody.getInformation().get("exception");
        System.out.println("-----------------------exception stack trace--------------------------------");
        exception.printStackTrace();
    }
}
