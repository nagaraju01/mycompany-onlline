/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.common.util;

import com.mycompany.xyz.common.BaseResponse;
import com.mycompany.xyz.exception.BusinessException;
import com.mycompany.xyz.util.MessageHandler;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ResponseUtils class
 *
 * @author Nagarajut
 */
@Component("ResponseUtils")
public class ResponseUtils {

    @Autowired
    protected MessageHandler messageHandler;

    public void addError(BusinessException ex, BaseResponse response) {
        if (StringUtils.isBlank(ex.getErrorDisplayKey())) {
            if (StringUtils.isBlank(ex.getMessage())) {
                response.addError("ERR", ex.getErrorCode());
            } else {
                response.addError(ex.getErrorCode(), ex.getMessage());
            }

        } else {
            response.addError(ex.getErrorCode(), messageHandler.getMsgDesc(ex.getErrorDisplayKey(), ex.getArgs()));
        }
    }

}
