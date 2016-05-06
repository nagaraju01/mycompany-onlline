/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.common.resource.process;

import com.mycompany.xyz.api.authentication.SecurityContextResult;
import com.mycompany.xyz.api.common.resource.request.ApiRequest;
import com.mycompany.xyz.api.common.resource.request.RequestBody;
import com.mycompany.xyz.exception.NotFoundException;
import com.mycompany.xyz.util.MessageHandler;
import com.mycompany.xyz.util.ReflectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BaseProcessAPIProcessor class
 *
 * @author nagaraju
 */
@Component
public class BaseProcessAPIProcessor {

    protected Logger logger = Logger.getLogger(getClass());

    @Autowired
    protected MessageHandler messageHandler;

    @Autowired
    protected ReflectionUtils reflectionUtils;

    /**
     * default constructor
     */
    public BaseProcessAPIProcessor() {
    }

    protected void populateApiRequest(SecurityContextResult ctx, RequestBody requestBody, ApiRequest request) throws NotFoundException {
        request.setUserName(ctx.getUser());
        reflectionUtils.transform(requestBody, request);
    }

}
