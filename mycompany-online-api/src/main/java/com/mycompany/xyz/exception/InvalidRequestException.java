/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.exception;

/**
 * InvalidRequestException exception
 *
 * @author ajantham
 */
public class InvalidRequestException extends RuntimeException {

    private String errorCode = "";

    private String message = "";

    /**
     * NRGIException constructor
     *
     * @param errorCode
     * @param errorDisplayKey
     * @param message
     */
    public InvalidRequestException(String errorCode, String invalidReason) {

        this.errorCode = errorCode;
        this.message = invalidReason;
    }

    /**
     * NRGIException constructor
     *
     * @param errorCode
     */
    public InvalidRequestException(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getInvalidReason() {
        return message;
    }

    /**
     * gets error code
     *
     * @return
     */
    public String getErrorCode() {
        return this.errorCode;
    }

}
