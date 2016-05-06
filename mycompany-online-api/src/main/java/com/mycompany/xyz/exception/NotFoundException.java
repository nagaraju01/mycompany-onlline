/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.exception;

import com.mycompany.xyz.util.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * NotFoundException exception Can be thrown on the Controller level or API to
 * indicate the requested resource does not exist.
 *
 * @author nagarajut
 */
@Component("NotFoundException")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4491709723926902236L;

    @Autowired
    protected MessageHandler messageHandler;

    protected String errorCode = "";

    protected String errorDesc = "";

    /**
     * default constructor
     */
    public NotFoundException() {
    }

    /**
     * NotFoundException constructor
     *
     * @param errorCode
     */
    public NotFoundException(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * NotFoundException constructor
     *
     * @param errorCode
     * @param errorDisplayKey
     * @param message
     */
    public NotFoundException(String errorCode, String invalidReason) {

        this.errorCode = errorCode;
        this.errorDesc = invalidReason;
    }

    /**
     * NotFoundException constructor
     *
     * @param errorCode
     * @param errorDisplayKey
     * @param isProperty
     */
    public NotFoundException(String errorCode, String errorDescription, Boolean isProperty) {

        if (isProperty) {
            setErrorDetailsFromMessageSource(errorCode, errorDescription);
        } else {
            this.errorCode = errorCode;
            this.errorDesc = errorDescription;
        }

    }

    /**
     * NotFoundException constructor
     *
     * @param errorCode
     * @param errorDisplayKey
     * @param message
     */
    public NotFoundException(String errorCode, String errorDescription, String defaultMessage) {
        super(defaultMessage);
        this.errorCode = errorCode;
        this.errorDesc = errorDescription;
    }

    /**
     * sets error details from message source
     *
     * @param errorCode
     * @param errorDescription
     */
    private void setErrorDetailsFromMessageSource(String errorCode, String errorDescription) {
        this.errorCode = messageHandler.getMsgCode(errorCode);
        this.errorDesc = messageHandler.getMsgDesc(errorDescription);
    }

    /**
     * gets invalid reason
     *
     * @return String
     */
    public String getInvalidReason() {
        return errorDesc;
    }

    /**
     * gets error code
     *
     * @return String
     */
    public String getErrorCode() {
        return this.errorCode;
    }

    /**
     * sets code
     *
     * @param code
     */
    public void setCode(String code) {
        setErrorDetailsFromMessageSource(code, code);
    }

}
