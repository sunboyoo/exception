package org.lcm.spring.exception.autoconfiguration;

import org.lcm.spring.exception.api.ExceptionHttpResponseBody;
import org.lcm.spring.exception.controlleradvice.GlobalExceptionHandlerControllerAdvice;
import org.lcm.spring.exception.event.listener.GlobalExceptionEventListener;
import org.lcm.spring.exception.event.processor.GlobalExceptionEventProcessor;
import org.lcm.spring.exception.event.processor.impl.SimpleGlobalExceptionEventProcessor;
import org.lcm.spring.exception.factory.ExceptionHttpResponseBodyFactory;
import org.lcm.spring.exception.factory.impl.SimpleExceptionHttpResponseBodyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ConditionalOnClass(GlobalExceptionHandlerControllerAdvice.class)
@Import(GlobalExceptionHandlerControllerAdvice.class)
public class GlobalExceptionHandlerAutoConfiguration {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerAutoConfiguration.class);

    static {
        logger.info("Import GlobalExceptionHandlerAutoConfigure");
    }

    @ConditionalOnMissingBean
    @Bean
    public ExceptionHttpResponseBodyFactory<? extends ExceptionHttpResponseBody> exceptionHttpResponseEntityBodyFactory(){
        logger.info("Import SimpleExceptionHttpResponseEntityBodyFactory as the default ExceptionHttpResponseEntityBodyFactory.");
        logger.info("You could customize an ExceptionHttpResponseEntityBodyFactory for customization.");
        return new SimpleExceptionHttpResponseBodyFactory();
    }

    @ConditionalOnMissingBean
    @Bean
    public GlobalExceptionEventProcessor globalExceptionEventProcessor(){
        logger.info("Import SimpleGlobalExceptionEventProcessor");
        logger.warn("It is recommended to create a subclass of GlobalExceptionEventProcessor for customization.");
        return new SimpleGlobalExceptionEventProcessor();
    }

    @ConditionalOnBean({GlobalExceptionEventProcessor.class})
    @Bean
    public GlobalExceptionEventListener globalExceptionEventListener(GlobalExceptionEventProcessor globalExceptionEventProcessor){
        logger.info("Import GlobalExceptionEventListener");
        logger.warn("It is recommended to create a subclass of GlobalExceptionEventListener for customization.");
        return new GlobalExceptionEventListener(globalExceptionEventProcessor);
    }
}
