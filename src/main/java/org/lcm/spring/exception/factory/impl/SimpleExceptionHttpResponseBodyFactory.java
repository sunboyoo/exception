package org.lcm.spring.exception.factory.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.lcm.spring.exception.api.impl.SimpleExceptionHttpResponseBody;
import org.lcm.spring.exception.factory.ExceptionHttpResponseBodyFactory;
import org.lcm.spring.exception.factory.helper.ExceptionDefaultMessage;
import org.lcm.spring.exception.factory.helper.ExceptionHttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import java.util.UUID;

public class SimpleExceptionHttpResponseBodyFactory implements ExceptionHttpResponseBodyFactory<SimpleExceptionHttpResponseBody> {

    @Override
    public SimpleExceptionHttpResponseBody newInstance() {
        return new SimpleExceptionHttpResponseBody();
    }

    @Override
    public void configInstance(SimpleExceptionHttpResponseBody body,
                               Exception exception,
                               HttpServletRequest request,
                               @Nullable HttpSession session) {

        String message = ExceptionDefaultMessage.getDefaultMessage(exception);
        HttpStatus httpStatus = ExceptionHttpStatus.getHttpStatus(exception);

        body.setMessage(message);
        body.setCode(httpStatus.value());
        body.setErrorMessage(message);
        body.setErrorCode(String.valueOf(httpStatus.value()));

        body.setSuccess(false);
        body.setData(null);
        body.setShowType(FrontEndErrorShowType.MESSAGE_ERROR.getCode());
        body.setTraceId(UUID.randomUUID().toString());
        body.setHost(request.getServerName() + ":" + request.getServerPort());
    }
}
