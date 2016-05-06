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
 * TypeMismatchException exception
 *
 * @author nagarajut
 */
@Component("TypeMismatchException")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TypeMismatchException extends RuntimeException {

    public static final String TYPE_MISMATCH = "typeMismatch";

    @Autowired
    protected MessageHandler messageHandler;

    protected String errorCode = "";

    protected String errorDesc = "";

    /**
     * default constructor
     */
    public TypeMismatchException() {
    }

    /**
     * TypeMismatchException constructor
     *
     * @param errorCode
     */
    public TypeMismatchException(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * TypeMismatchException constructor
     *
     * @param errorCode
     * @param errorDisplayKey
     * @param message
     */
    public TypeMismatchException(String errorCode, String invalidReason) {

        this.errorCode = errorCode;
        this.errorDesc = invalidReason;
    }

    /**
     * TypeMismatchException constructor
     *
     * @param errorCode
     * @param errorDisplayKey
     * @param isProperty
     */
    public TypeMismatchException(String errorCode, String errorDescription, Boolean isProperty) {

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
    public TypeMismatchException(String errorCode, String errorDescription, String defaultMessage) {
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
