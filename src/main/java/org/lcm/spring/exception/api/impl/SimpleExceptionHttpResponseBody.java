package org.lcm.spring.exception.api.impl;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.lcm.spring.exception.api.ExceptionHttpResponseBody;
@Data
@NoArgsConstructor
public class SimpleExceptionHttpResponseBody implements ExceptionHttpResponseBody {
    private int code;
    private String message;

    // Ant-Design-Pro umi-request 错误处理接口格式规范
    private boolean success; // if request is successful
    private Object data; // response data
    private String errorCode; // code for errorType
    private String errorMessage; // message display to user
    private int showType; // error display type： 0 silent; 1 message.warn; 2 message.error; 4 notification; 9 page
    private String traceId; // Convenient for back-end Troubleshooting: unique request ID
    private String host; // Convenient for backend Troubleshooting: host of current access server
}
