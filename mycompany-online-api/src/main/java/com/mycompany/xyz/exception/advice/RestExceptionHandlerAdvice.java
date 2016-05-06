/*
 * Copyright © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.exception.advice;

import com.mycompany.xyz.exception.NotFoundException;
import com.mycompany.xyz.exception.RestExceptionResponse;
import com.mycompany.xyz.exception.TypeMismatchException;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import org.apache.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 * RestExceptionHandlerAdvice class
 *
 * @author Nagarajut
 */
@ControllerAdvice(annotations = RestController.class)
@Order(Ordered.LOWEST_PRECEDENCE)
class RestExceptionHandlerAdvice {

    private final static Logger LOGGER = Logger
            .getLogger(RestExceptionHandlerAdvice.class);

    /**
     * Handle exceptions thrown by handlers.
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> exception(Exception exception, WebRequest request) {

        RestExceptionResponse response = new RestExceptionResponse();
        response.setSuccess(Boolean.FALSE);

        if (exception instanceof HttpMessageNotReadableException) {

            // Request body is not present or readable.
            LOGGER.warn("Request body not readable", exception);
            response.setMessage("Required request body content"
                    + " is missing or is not readable");

        } else if (exception instanceof HttpMediaTypeNotAcceptableException) {

            // Request is not having acceptable media type representation.
            LOGGER.warn("Request mediatype not accetable", exception);
            response.setMessage("Required acceptable mediatype representation");

        } else if (exception instanceof NotFoundException) {

            LOGGER.warn("Requested resource does not exist", exception);
            // Requested resource does not exist. NotFound means 404 and no respsonse body
            return ResponseEntity.notFound().build();

        } else if (exception instanceof TypeMismatchException) {

            // Type Mismatch Error for the request properties/attributes
            TypeMismatchException ex = (TypeMismatchException) exception;
            response.setSuccess(Boolean.FALSE);
            response.setMessage(ex.getMessage());
            response.addError(ex.getErrorCode(), ex.getInvalidReason());
            return ResponseEntity.badRequest().body(response);

        } else if (exception instanceof ConstraintViolationException) {

            // Bean validation erros are found. These errors normally violate the 
            // validation annotations of the JSR-303 specification used in models.
            response.setMessage("Some values are missing or invalid");
            ConstraintViolationException ex = (ConstraintViolationException) exception;
            ex.getConstraintViolations().stream().forEach(violation -> {
                response.addError(violation.getPropertyPath().toString(),
                        violation.getMessage());
            });

        } else if (exception instanceof ValidationException) {

            // A ValidationException is thrown when a logical error is found.
            // Alternatively when the request conflicts with the requirements 
            // of the business logic, such an exception is important.
            response.setMessage("Invalid request");
            ValidationException ex = (ValidationException) exception;
            response.setMessage(ex.getMessage());

        } else {
            // Any other exceptions thrown
            LOGGER.error("Unexpected error", exception);
            response.setMessage("Request couldn't be processed");
        }

        return ResponseEntity.badRequest().body(response);
    }

}
