package org.lcm.spring.exception.controlleradvice;


import org.lcm.spring.exception.event.GlobalExceptionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 1.全局的异常处理 MyGlobalExceptionHandler  - 推荐
 *      (0) @ControllerAdvice 实现全局的异常处理
 *      (1) 请求参数异常的处理
 *      (2) 默认异常的处理
 *  优点：
 *      (1) Controller中的方法的返回值不必设置为ResponseEntity，直接设置为正常情况下返回的数据类型即可。
 *          如果条件不满足，可以手动（且放心）抛异常。
 *          这样的话，既满足了逻辑完整性，也对swagger比较友好
 *      (2) 如果controller中出现了意料之外的异常，会有最终的地方来处理（记录下日志并返回你希望的内容）
 *      (3) 可以自定义一个或一些自己的异常，更加个性化地返回内容和 HTTP Code。
 *
 *
 *  2. Controller级别的异常处理 - 配合全局异常处理
 *
 *  3. 使用 ResponseEntity 还是 注解 @ResponseStatus 返回?
 *      Warning: when using this annotation on an exception class, or when setting the reason attribute of this annotation,
 *      the HttpServletResponse.sendError method will be used.
 *      With HttpServletResponse.sendError, the response is considered complete and should not be written to any further.
 *      Furthermore, the Servlet container will typically write an HTML error page therefore making the use of a reason
 *      unsuitable for REST APIs. For such cases it is preferable to use a org.springframework.http.ResponseEntity
 *      as a return type and avoid the use of @ResponseStatus altogether.
 *
 */
@ControllerAdvice
public class GlobalExceptionHandlerControllerAdvice implements ApplicationEventPublisherAware {
    private static ApplicationEventPublisher applicationEventPublisher;

    //日志对象
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerControllerAdvice.class);

    /**
     * 请求参数异常的处理
     * 外部请求本微服务所传递的参数异常
     */
    @ExceptionHandler({IllegalArgumentException.class, TypeMismatchException.class})
    @ResponseBody
    public ResponseEntity<ExceptionResponseBody> illegalArgumentExceptionHandler(HttpServletRequest request, IllegalArgumentException exception){
        // (1) 响应体的设置
        ExceptionResponseBody body = new ExceptionResponseBody(HttpStatus.BAD_REQUEST, exception, request);

        // (2) 详细的错误信息
        body.addInformation("备注","GlobalExceptionHandlerControllerAdvice illegalArgumentExceptionHandler()");

        // (3) 发布一个 GlobalExceptionEvent, 监听器可以进行打印、写入文件等处理
        applicationEventPublisher.publishEvent(new GlobalExceptionEvent(this, body));

        // (4) 返回响应实体
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * 默认异常的处理
     * 当其它的异常处理器都无法处理的时候偶，本处理器进行处理
     * 不要使用注解 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponseBody> defaultExceptionHandler(HttpServletRequest request, Exception exception){

        // (1) 响应体的设置
        ExceptionResponseBody body = new ExceptionResponseBody(HttpStatus.INTERNAL_SERVER_ERROR, exception, request);

        // (2) 详细的错误信息
        body.addInformation("备注","GlobalExceptionHandlerControllerAdvice defaultExceptionHandler()");

        // (3) 发布一个 GlobalExceptionEvent, 监听器可以进行打印、写入文件等处理
        applicationEventPublisher.publishEvent(new GlobalExceptionEvent(this, body));

        // (4) 返回响应实体
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        GlobalExceptionHandlerControllerAdvice.applicationEventPublisher = applicationEventPublisher;
    }
}
