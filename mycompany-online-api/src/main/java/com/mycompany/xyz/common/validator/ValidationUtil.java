/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.common.validator;

import com.mycompany.xyz.api.common.resource.constants.ErrorCode;
import com.mycompany.xyz.exception.InvalidRequestException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * ValidationUtil class
 *
 * @author nagarajut
 */
@Component
public class ValidationUtil {

    public SimpleDateFormat apiDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    /**
     * default constructor
     */
    public ValidationUtil() {
    }

    /**
     * checks if the date sent in API request is in valid format
     *
     * @param requestDate
     * @return boolean
     */
    public boolean isValidAPIDate(String requestDate) {
        Date date = null;
        try {
            date = apiDate.parse(requestDate);
        } catch (NullPointerException | ParseException ex) {
        }

        return date != null;
    }

    /**
     * gets GregorianCalendar date
     *
     * @param date (yyyy-MM-dd'T'HH:mm:ssZ)
     * @return GregorianCalendar
     */
    public GregorianCalendar getGCDate(String date) {
        GregorianCalendar gcDate = new GregorianCalendar();
        try {
            gcDate.setTime(apiDate.parse(date));
        } catch (NullPointerException | ParseException ex) {
            return null;
        }
        return gcDate;
    }

    /**
     * Checks if the parameter only contains numeric data (no symbols or
     * letters)
     *
     * @param numericData
     * @return true if the data is correct
     */
    public boolean validateNumericData(String numericData) {

        if (numericData == null) {
            return false;
        }
        String numData = numericData.trim();
        if (numData.length() == 0) {
            return false;
        }

        try {
            Integer.parseInt(numData);
        } catch (NumberFormatException ex) {
            try {
                return !new Float(numData).isNaN();
            } catch (NumberFormatException ex1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks is the input contains numbers or letters only
     *
     * @param data
     * @return true if the data is correct
     */
    public boolean validateAlphanumeric(String data) {
        return StringUtils.isAlphanumericSpace(data);
    }

    /**
     * Checks if the String parameter length does not exceed the maximum length
     * specified.
     *
     * @param data
     * @param maxLength
     * @return
     */
    public boolean checkLength(String data, Integer maxLength) {
        return data.length() <= maxLength;
    }

    /**
     * Throw an Exception with given {@link ErrorCode} if given value is null
     *
     * @param value - any object
     * @param errorCode - {@link ErrorCode}, which should be used in case is
     * given value null
     * @throws InvalidRequestException
     */
    public void validateNotNull(final Object value, final ErrorCode errorCode) throws InvalidRequestException {
        if (value == null) {
            throw new InvalidRequestException(errorCode.getCode(), errorCode.getDescription());
        }
    }

    /**
     * Converts {@link BindingResult} errors messages to collection. Only error
     * codes which are enclosed in "{}" brackets are included.
     *
     * @param result
     * @return list of error messages
     */
    public static Set<String> toErrorMessageList(BindingResult result) {
        Set<String> codeList = new HashSet<>();
        for (Object object : result.getAllErrors()) {
            String defaultMessage = null;
            if (object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;
                defaultMessage = fieldError.getDefaultMessage();
            }
            if (object instanceof ObjectError) {
                ObjectError objectError = (ObjectError) object;
                defaultMessage = objectError.getDefaultMessage();
            }
            if (defaultMessage.startsWith("{") && defaultMessage.endsWith("}")) {
                defaultMessage = defaultMessage.replaceAll("(\\{|\\})", "");
                codeList.add(defaultMessage);
            }
        }
        return codeList;
    }

    /**
     * to Error Message List
     *
     * @param result
     * @return Set<String>
     */
    public static Set<String> toErrorMessageList(Errors result) {
        Set<String> codeList = new HashSet<>();
        for (Object object : result.getAllErrors()) {
            String defaultMessage = null;
            if (object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;
                defaultMessage = fieldError.getDefaultMessage();
            }
            if (object instanceof ObjectError) {
                ObjectError objectError = (ObjectError) object;
                defaultMessage = objectError.getDefaultMessage();
            }
            if (defaultMessage.startsWith("{") && defaultMessage.endsWith("}")) {
                defaultMessage = defaultMessage.replaceAll("(\\{|\\})", "");
                codeList.add(defaultMessage);
            }
        }
        return codeList;
    }

    /**
     * Returns list of error codes
     *
     * @param result
     * @return Set<String>
     */
    public static Set<String> toErrorCodeList(BindingResult result) {
        Set<String> codeList = new HashSet<>();
        for (ObjectError object : result.getAllErrors()) {
            codeList.add(object.getDefaultMessage());
        }
        return codeList;
    }

}
