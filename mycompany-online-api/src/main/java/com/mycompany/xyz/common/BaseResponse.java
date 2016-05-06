/*
 * Copyright © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * BaseResponse abstract class
 *
 * @author ajantham
 */
public abstract class BaseResponse implements Serializable {

    protected Boolean success = Boolean.TRUE; //set false if operation failed

    protected String message;

    protected Map<String, String> errors;

    protected Map<String, String> warnings;

    public BaseResponse() {
        this.errors = new HashMap<>();
        this.warnings = new HashMap<>();
    }

    public Boolean isSuccess() {
        return success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> error) {
        this.errors = error;
    }

    public void addError(String code, String message) {
        this.errors.put(code, message);
    }

    public Map<String, String> getWarnings() {
        return warnings;
    }

    public void setWarnings(Map<String, String> warning) {
        this.warnings = warning;
    }

    public void addWarning(String code, String message) {
        this.warnings.put(code, message);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public boolean hasNotErrors() {
        return !hasErrors();
    }

    @Override
    public String toString() {
        return "BaseResponse{" + ", message=" + message + ", error=" + errors + ", success=" + success + '}';
    }

}
