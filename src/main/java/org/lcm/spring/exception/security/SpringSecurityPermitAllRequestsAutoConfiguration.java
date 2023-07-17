package org.lcm.spring.exception.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

// The SpringSecurityPermitAllRequestsAutoConfiguration class is a configuration class that is loaded
// only when a bean marked with the @SpringSecurityPermitAllRequests annotation is present in the Spring application context.
// This class sets up a security filter chain which allows all incoming HTTP requests
// to pass through without any security constraints, effectively disabling Spring Security's protection for all paths.
@EnableWebSecurity // Enable Spring Security's web security support
@Configuration // Mark this class as a source of bean definitions
@ConditionalOnBean(annotation = SpringSecurityPermitAllRequests.class) // Only load this configuration if a bean with @SpringSecurityPermitAllRequests annotation is present
public class SpringSecurityPermitAllRequestsAutoConfiguration {

    private final Logger logger = LoggerFactory.getLogger(SpringSecurityPermitAllRequestsAutoConfiguration.class);

    // This code block runs when an instance of this class is created,
    // logging the presence of the @SpringSecurityPermitAllRequests annotation and the import of the SpringSecurityPermitAllRequestsAutoConfiguration
    {
        logger.info("""
        
        A bean with @SpringSecurityPermitAllRequests annotation is present.
        Loading SpringSecurityPermitAllRequestsAutoConfiguration.
        Spring Security will permit all incoming HTTP requests.
        """);
    }
    // This method sets up a security filter chain that allows all HTTP requests to pass through,
    // effectively disabling any security constraints on the incoming HTTP requests.
    // It also disables the CSRF (Cross-Site Request Forgery) protection which is enabled by default in Spring Security.
    @Bean // Define a bean that will be managed by the Spring container
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() // Permit all incoming HTTP requests
                )
                .csrf(AbstractHttpConfigurer::disable); // Disable CSRF protection
        return http.build(); // Build and return the SecurityFilterChain instance
    }
}
