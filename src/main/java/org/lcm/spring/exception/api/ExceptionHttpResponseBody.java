package org.lcm.spring.exception.api;

import java.io.Serializable;

/*
    An HTTP response entity consists of headers and body
    ExceptionHttpResponseEntityBody represents the body of an HTTP response entity
    It can be used as below:
    ResponseEntity<? extends ExceptionHttpResponseEntityBody>

* */
public interface ExceptionHttpResponseBody extends Serializable {

}
