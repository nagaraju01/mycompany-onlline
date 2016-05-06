/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.common.validator;

import com.mycompany.xyz.exception.BusinessException;
import com.mycompany.xyz.exception.NotFoundException;
import com.mycompany.xyz.util.MessageHandler;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

/**
 * BaseAPIRequestValidator class
 *
 * @author nagarajut
 */
@Component
public abstract class BaseRequestValidator implements Validator {

    protected Logger logger = Logger.getLogger(getClass());

    protected static final String FAIL_MSG = "common.request.validation.failed";

    private static final String TYPE_MISMATCH = "typeMismatch";

    @Autowired
    HttpServletRequest httpRequest;

    @Autowired
    protected ValidationUtil validationUtil;

    @Autowired
    protected MessageHandler messageHandler;

    /**
     * authenticates and validates the request
     *
     * @param result
     * @return Response
     */
    protected void throwBusinessException(BindingResult result) throws NotFoundException {
        if (result.hasErrors()) {
            for (ObjectError err : result.getAllErrors()) {
                if (TYPE_MISMATCH.equals(err.getCode())) {
                    throw new NotFoundException(messageHandler.getMessage(err.getCode()),
                            messageHandler.getMessage(err.getCodes()[1]));
                } else {
                    throw new BusinessException(messageHandler.getMsgCode(err.getCode()),
                            err.getCode(), err.getArguments(), err.getDefaultMessage());
                }
            }
        }
    }

}
