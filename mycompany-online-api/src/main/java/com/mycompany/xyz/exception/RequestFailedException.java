/*
 * Copyright  ©  My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.exception;

/**
 * RequestFailedException class
 *
 * @author Nagarajut
 */
@SuppressWarnings("serial")
public class RequestFailedException extends RuntimeException {

    private final Integer projectId;

    private final String code;

    /**
     * {@inheritDoc}
     */
    public RequestFailedException() {
        super();
        projectId = null;
        code = null;
    }

    /**
     * {@inheritDoc}
     */
    public RequestFailedException(String message) {
        super(message);
        projectId = null;
        code = null;
    }

    /**
     * Constructs a new exception with the specified detail message and with the
     * related project ID
     *
     * @param message
     * @param projectId
     */
    public RequestFailedException(String message, int projectId) {
        super(message);
        this.projectId = projectId;
        this.code = null;
    }

    /**
     * Constructs a new exception with the specified detail message and with the
     * related project ID
     *
     * @param message
     * @param code
     * @param projectId
     */
    public RequestFailedException(String message, String code, Integer projectId) {
        super(message);
        this.projectId = projectId;
        this.code = code;
    }

    /**
     * {@inheritDoc}
     */
    public RequestFailedException(String message, Throwable cause) {
        super(message, cause);
        projectId = null;
        code = null;
    }

    /**
     * Constructs a new exception with the specified detail message and with the
     * related project ID and the root cause.
     *
     * @param message
     * @param projectId
     * @param cause
     */
    public RequestFailedException(String message, int projectId, Throwable cause) {
        super(message, cause);
        this.projectId = projectId;
        code = null;
    }

    /**
     * {@inheritDoc}
     */
    public RequestFailedException(Throwable cause) {
        super(cause);
        this.projectId = null;
        code = null;
    }

    /**
     * Constructs a new exception with the cause and the related project ID
     *
     * @param cause
     * @param projectId
     */
    public RequestFailedException(Throwable cause, int projectId) {
        super(cause);
        this.projectId = projectId;
        code = null;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public String getCode() {
        return " code " + code;
    }

}
