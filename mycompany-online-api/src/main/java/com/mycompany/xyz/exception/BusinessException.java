/*
 \* Copyright  ©  My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.exception;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * BusinessException class
 *
 * @author nagarajut
 */
@Component
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Business validation error")
public class BusinessException extends RuntimeException {

    private String errorCode = "";

    private String errorDisplayKey = "";

    private Object[] args;

    private Map<String, Object> properties;

    // default constructor
    public BusinessException() {
    }
    
/**
     * BusinessException constructor
     *
     * @param errorCode
     * @param errorDisplayKey
     * @param message
     */
    public BusinessException(String errorCode, String errorDisplayKey, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorDisplayKey = errorDisplayKey;
    }    

    /**
     * BusinessException constructor
     *
     * @param errorCode
     * @param errorDisplayKey
     * @param args
     * @param message
     */
    public BusinessException(String errorCode, String errorDisplayKey, Object[] args, String message) {
        super(message);
        this.args = args;
        this.errorCode = errorCode;
        this.errorDisplayKey = errorDisplayKey;
    }

    /**
     * BusinessException constructor
     *
     * @param errorCode
     * @param message
     *
     */
    public BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * BusinessException constructor
     *
     * @param errorCode
     * @param message
     * @param cause
     */
    public BusinessException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * BusinessException constructor
     *
     * @param errorCode
     */
    public BusinessException(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * gets error display key
     *
     * @return
     */
    public String getErrorDisplayKey() {
        return errorDisplayKey;
    }

    /**
     * gets error code
     *
     * @return
     */
    public String getErrorCode() {
        return this.errorCode;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

}
