package org.lcm.spring.exception.factory.helper;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public interface ExceptionDefaultMessage {

    static String getDefaultMessage(BindException bindException){
        BindingResult bindingResult = bindException.getBindingResult();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                return fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return null;
    }

    static String getDefaultMessage(Exception exception){
        if (exception instanceof AuthenticationException){
            // Authentication
            if (exception instanceof LockedException) {
                return "The account is locked";
            } else if (exception instanceof DisabledException) {
                return "The account is disabled";
            } else if (exception instanceof CredentialsExpiredException) {
                return "The account's credentials have expired";
            } else if (exception instanceof AccountExpiredException) {
                return "The account has expired";
            } else if (exception instanceof BadCredentialsException) {
                return "Wrong username or password";
            }
            return "Not authenticated.";
        } else if  (exception instanceof AccessDeniedException){
            // Authorization
            return "Not authorized";
        } else if  (exception instanceof BindException) {
            // Controller
            return getDefaultMessage((BindException) exception);
        } else if (exception instanceof IllegalArgumentException || exception instanceof TypeMismatchException){
            // IllegalArgumentException TypeMismatchException
            return HttpStatus.BAD_REQUEST.getReasonPhrase();
        } else {
            // Others
            return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
    }
}
