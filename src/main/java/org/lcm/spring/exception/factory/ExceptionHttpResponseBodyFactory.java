package org.lcm.spring.exception.factory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.lcm.spring.exception.api.ExceptionHttpResponseBody;
import org.springframework.lang.Nullable;
public interface ExceptionHttpResponseBodyFactory<T extends ExceptionHttpResponseBody> {

    // create an instance, configure it, and return it
    default T create(Exception exception, HttpServletRequest request, @Nullable HttpSession session) {
        T t = newInstance();
        configInstance(t, exception, request, session);
        return t;
    }

    T newInstance();

    void configInstance(
            T body,
            Exception exception,
            HttpServletRequest request,
            @Nullable HttpSession session);
}
