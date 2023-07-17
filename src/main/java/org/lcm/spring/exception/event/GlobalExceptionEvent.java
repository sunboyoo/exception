package org.lcm.spring.exception.event;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class GlobalExceptionEvent extends ApplicationEvent {
    private final String localDateTime = LocalDateTime.now().toString();
    private final Map<String, Object> exception = new HashMap<>();
    private final Map<String, Object> request = new HashMap<>();
    private final Map<String, Object> session = new HashMap<>();

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public GlobalExceptionEvent(Object source) {
        super(source);
    }

    public GlobalExceptionEvent(Object source,
                                Exception exception,
                                HttpServletRequest request,
                                @Nullable HttpSession session) {
        super(source);

        // Exception
        this.exception.put("type", exception.getClass());
        this.exception.put("message", exception.getMessage());

        // Request
        this.request.put("remoteAddr", request.getRemoteAddr());
        this.request.put("remotePort", request.getRemotePort());
        this.request.put("uri", request.getRequestURI());
        this.request.put("queryString", request.getQueryString());
        this.request.put("parameterMap", request.getParameterMap());
        this.request.put("serverName", request.getServerName());
        this.request.put("serverPort", request.getServerPort());

        // Session
        if (session != null) {
            this.session.put("JSESSIONID", session.getId());
            this.session.put("creationTime", LocalDateTime.ofInstant(Instant.ofEpochMilli(session.getCreationTime()),
                    ZoneId.of(ZoneId.SHORT_IDS.get("EST"))));
        }
    }
}
