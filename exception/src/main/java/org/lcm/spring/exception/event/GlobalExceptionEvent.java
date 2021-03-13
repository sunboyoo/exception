package org.lcm.spring.exception.event;

import lombok.Getter;
import lombok.Setter;
import org.lcm.spring.exception.controlleradvice.ExceptionResponseBody;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class GlobalExceptionEvent extends ApplicationEvent {
    private ExceptionResponseBody exceptionResponseBody;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public GlobalExceptionEvent(Object source) {
        super(source);
    }

    public GlobalExceptionEvent(Object source, ExceptionResponseBody exceptionResponseBody) {
        super(source);
        this.exceptionResponseBody = exceptionResponseBody;
    }
}
