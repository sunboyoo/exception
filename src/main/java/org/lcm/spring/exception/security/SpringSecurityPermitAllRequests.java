package org.lcm.spring.exception.security;

import java.lang.annotation.*;

// The SpringSecurityPermitAllRequests is a custom annotation that can be placed on any class.
// It is retained at runtime, documented, and can be inherited by subclasses.

@Target(ElementType.TYPE) // Applies to classes only
@Retention(RetentionPolicy.RUNTIME) // The annotation will be available at runtime
@Documented // Include this annotation in the documentation
@Inherited // Allow subclasses to inherit this annotation
public @interface SpringSecurityPermitAllRequests {
}