/*
 * Copyright  ©  My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * UserNotFoundException class
 *
 * @author peterj
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends IllegalArgumentException {

    private Integer userId;

    /**
     * Constructs an <code>UserNotFoundException</code> with no detail message.
     */
    public UserNotFoundException() {
        super();
    }

    /**
     * Constructs an <code>UserNotFoundException</code> with no detail message
     * and with user ID.
     */
    public UserNotFoundException(int userId) {
        super();
        this.userId = userId;
    }

    /**
     * Constructs an <code>UserNotFoundException</code> with the specified
     * detail message.
     *
     * @param s the detail message.
     */
    public UserNotFoundException(String s) {
        super(s);
    }

    /**
     * Constructs an <code>UserNotFoundException</code> with the specified
     * detail message and with user ID.
     *
     * @param s the detail message.
     */
    public UserNotFoundException(String s, int userId) {
        super(s);
        this.userId = userId;
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * <p>
     * Note that the detail message associated with <code>cause</code> is
     * <i>not</i> automatically incorporated in this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval by
     * the {@link Throwable#getMessage()} method).
     * @param user ID
     * @param cause the cause (which is saved for later retrieval by the
     * {@link Throwable#getCause()} method). (A <tt>null</tt> value is
     * permitted, and indicates that the cause is nonexistent or unknown.)
     * @since 1.5
     */
    public UserNotFoundException(String message, int userId, Throwable cause) {
        super(message, cause);
        this.userId = userId;
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * <p>
     * Note that the detail message associated with <code>cause</code> is
     * <i>not</i> automatically incorporated in this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval by
     * the {@link Throwable#getMessage()} method).
     * @param cause the cause (which is saved for later retrieval by the
     * {@link Throwable#getCause()} method). (A <tt>null</tt> value is
     * permitted, and indicates that the cause is nonexistent or unknown.)
     * @since 1.5
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
