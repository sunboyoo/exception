package org.lcm.spring.exception.controlleradvice;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常响应体的类
 * 为了规范异常时的响应体的格式
 * 正常响应的时候，返回正常的数据类型，无需包装
 */
@Data
@NoArgsConstructor
public class ExceptionResponseBody implements Serializable {
    private Integer code;
    private String message;
    private String localDateTime;
    private Map<String, Object> information = new HashMap<>();

    public ExceptionResponseBody(HttpStatus httpStatus){
        this(httpStatus, null, null);
    }

    public ExceptionResponseBody(HttpStatus httpStatus, Exception exception){
        this(httpStatus, exception, null);
    }

    // 根据 HttpStatus, Exception, HttpServletRequest 三大元素，创建出一个 ExceptionResponseBody
    public ExceptionResponseBody(HttpStatus httpStatus, Exception exception, HttpServletRequest request){
        // (1) HttpStatus信息
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();

        this.localDateTime = LocalDateTime.now().toString();

        // (2) 异常相关的详细信息
        if (exception != null){
            this.information.put("exception", exception);
            this.information.put("type", exception.getClass());
            this.information.put("message", exception.getMessage());
            this.information.put("cause", exception.getCause());
            this.information.put("stackTrace", exception.getStackTrace());
        }

        // (3) 请求Request相关的详细信息
        if (request != null){
            this.information.put("remoteAddr", request.getRemoteAddr());
            this.information.put("remotePort", request.getRemotePort());
            this.information.put("uri", request.getRequestURI());
            this.information.put("queryString", request.getQueryString());
            this.information.put("parameterMap", request.getParameterMap());
            this.information.put("serverName", request.getServerName());
            this.information.put("serverPort", request.getServerPort());
        }
    }

    public void addInformation(String key, Object value){
        // 添加细节
        this.information.put(key, value);
    }

}
