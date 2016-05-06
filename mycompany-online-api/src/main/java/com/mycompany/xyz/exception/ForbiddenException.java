package com.mycompany.xyz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Can be thrown on the Controller level to indicate the 
 * requested resource cannot be accessed.
 * 
 * @author mdanilakis
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 6602594138487962904L;
}
