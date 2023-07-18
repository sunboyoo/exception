package org.lcm.spring.exception.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.security.web.SecurityFilterChain;

// Main configuration class that checks if Spring Security is active in the application

@Configuration
public class SpringSecurityInstructionAutoConfiguration implements ApplicationListener<ApplicationReadyEvent> {
    private final static Logger LOGGER = LoggerFactory.getLogger(SpringSecurityInstructionAutoConfiguration.class);

    // This method is called when the ApplicationReadyEvent is published
    // i.e., when the ApplicationContext is fully started
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Fetch the application context directly from the event
        GenericApplicationContext context = (GenericApplicationContext) event.getApplicationContext();
        // Check if SecurityFilterChain bean is present which indicates that Spring Security is active
        if (context.getBeanNamesForType(SecurityFilterChain.class).length > 0) {
            LOGGER.info("""
                
                
                The SecurityFilterChain bean is present.
                To disable Spring Security, add the following configuration to the file application.properties:
                spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
                
                """);
        } else {
            LOGGER.info("The SecurityFilterChain bean is not present. Spring Security is not running.");
        }
    }

    // Configuration class that will be used when SecurityAutoConfiguration is present on the classpath
    @Configuration
    @ConditionalOnClass(SecurityAutoConfiguration.class)
    public static class SecurityAutoConfigurationPresentConfiguration {
        @Bean
        public String checkSecurityAutoConfigurationPresent() {
            LOGGER.info("SecurityAutoConfiguration.class is present in the class path.");
            return "SecurityAutoConfiguration.class is present in the class path.";
        }
    }

    // Configuration class that will be used when SecurityAutoConfiguration is missing from the classpath
    @Configuration
    @ConditionalOnMissingClass("org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration")
    public static class SecurityAutoConfigurationMissingConfiguration {
        @Bean
        public String checkSecurityAutoConfigurationMissing() {
            LOGGER.info("SecurityAutoConfiguration.class is not present in the class path.");
            return "SecurityAutoConfiguration.class is not present in the class path.";
        }
    }

}
