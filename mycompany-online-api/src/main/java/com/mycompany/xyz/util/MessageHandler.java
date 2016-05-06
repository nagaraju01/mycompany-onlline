/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.util;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

/**
 * MessageHandler class
 *
 * @author nagarajut
 */
@Component
public class MessageHandler {

    protected static final String ERR_MSG_CODE_PREFIX = ".code";

    protected static final String ERR_MSG_DESC_PREFIX = ".desc";

    @Autowired
    protected MessageSource source;

    private Logger log4j = Logger.getLogger(getClass());

    /**
     * gets source
     *
     * @return MessageSource
     */
    public MessageSource getSource() {
        return source;
    }

    /**
     * gets message code
     *
     * @param msgCode
     * @return String
     */
    public String getMsgCode(String msgCode) {
        return getMessage(msgCode + ERR_MSG_CODE_PREFIX);
    }

    /**
     * gets message description
     *
     * @param msgDesc
     * @return String
     */
    public String getMsgDesc(String msgDesc) {
        return getMessage(msgDesc + ERR_MSG_DESC_PREFIX);
    }

    /**
     * gets message description with arguments
     *
     * @param msgDesc
     * @param args
     * @return String
     */
    public String getMsgDesc(String msgDesc, Object[] args) {
        return getMessage(msgDesc + ERR_MSG_DESC_PREFIX, args);
    }

    /**
     * gets message
     *
     * @param code
     * @param args
     * @return String
     */
    public String getMessage(final String code) {
        return getMessage(code, null);
    }

    /**
     * gets message
     *
     * @param code
     * @param args
     * @return String
     */
    public String getMessage(final String code, Object[] args) {
        return getMessage(code, args, Locale.ENGLISH);
    }

    /**
     * gets message
     *
     * @param code
     * @param args
     * @param locale
     * @return String
     */
    public String getMessage(final String code, Object[] args, Locale locale) {
        try {
            return source.getMessage(code, args, locale);
        } catch (NoSuchMessageException ex) {
            return "";
        }
    }

    /**
     * Converts {@link Errors} field message code contained in
     * <b>defaultMessages</b> to message.
     *
     * @param errors
     * @return list of merrors
     */
    public Set<String> getErrorMessages(Errors errors) {
        final Set<String> errorMessages = new HashSet<>();
        for (FieldError fieldError : errors.getFieldErrors()) {
            final String message = getMessage(fieldError.getDefaultMessage());
            if (isNotBlank(message)) {
                errorMessages.add(message);
            }
        }
        return errorMessages;
    }

    /**
     * Returns the first error message (if any) from given errors.
     *
     * If errors does not contain any errors the method returns NULL.
     *
     * @param errors
     * @return
     */
    public String getFirstErrorMessage(Errors errors) {
        final Set<String> errorMessages = getErrorMessages(errors);
        if (!errorMessages.isEmpty()) {
            return errorMessages.iterator().next();
        }
        return null;
    }

}
