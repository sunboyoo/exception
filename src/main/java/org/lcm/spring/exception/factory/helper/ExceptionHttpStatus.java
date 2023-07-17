package org.lcm.spring.exception.factory.helper;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;

public interface ExceptionHttpStatus {
    static HttpStatus getHttpStatus(Exception exception){
        if (exception instanceof AuthenticationException){
            // Authentication
            return HttpStatus.UNAUTHORIZED;
        } else if  (exception instanceof AccessDeniedException){
            // Authorization
            return HttpStatus.FORBIDDEN;
        } else if  (exception instanceof BindException ||
                exception instanceof IllegalArgumentException ||
                exception instanceof TypeMismatchException
        ){
            // Controller
            return HttpStatus.BAD_REQUEST;
        } else {
            // Others
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }


    }
}
