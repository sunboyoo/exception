package org.lcm.exception.spring.boot.autoconfig;

import org.lcm.spring.exception.controlleradvice.GlobalExceptionHandlerControllerAdvice;
import org.lcm.spring.exception.event.listener.GlobalExceptionEventListener;
import org.lcm.spring.exception.event.processor.GlobalExceptionEventProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;


@Configuration
@ConditionalOnClass(GlobalExceptionHandlerControllerAdvice.class)
@Import(GlobalExceptionHandlerControllerAdvice.class)
public class GlobalExceptionHandlerAutoConfigure {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerAutoConfigure.class);
    {
        logger.info("Load GlobalExceptionHandlerAutoConfigure");
    }

    @ConditionalOnMissingBean
    @Bean
    public GlobalExceptionEventProcessor globalExceptionEventProcessor(){
        logger.info("Init GlobalExceptionEventProcessor");
        return new GlobalExceptionEventProcessor();
    }

    @ConditionalOnBean({GlobalExceptionEventProcessor.class})
    @Bean
    public GlobalExceptionEventListener globalExceptionEventListener(GlobalExceptionEventProcessor globalExceptionEventProcessor){
        logger.info("Init GlobalExceptionEventListener");
        return new GlobalExceptionEventListener(globalExceptionEventProcessor);
    }
}
