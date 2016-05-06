/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.exception.advice;

import com.mycompany.xyz.common.util.ResponseUtils;
import com.mycompany.xyz.exception.BusinessException;
import com.mycompany.xyz.exception.RestExceptionResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 * BusinessExceptionHandlerAdvice class
 *
 * @author Nagarajut
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BusinessExceptionHandlerAdvice {

    private final static Logger LOGGER = Logger
            .getLogger(BusinessExceptionHandlerAdvice.class);

    @Autowired
    ResponseUtils responseUtils;

    /**
     * handles BusinessException, IllegalArgumentException and
     * IllegalStateException
     *
     * @param ex
     * @param request
     * @return ResponseEntity<?>
     */
    @ExceptionHandler({BusinessException.class, IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<?> handleBusinessException(Exception ex, WebRequest request) {
        RestExceptionResponse response = new RestExceptionResponse();
        response.setSuccess(Boolean.FALSE);
        LOGGER.error("Business error", ex);
        response.setMessage("Validation failed. Request couldn't be processed");
        if (ex instanceof BusinessException) {
            responseUtils.addError((BusinessException) ex, response);
        } else if (ex instanceof IllegalStateException) {
            response.addError("ERR", ex.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }

}
