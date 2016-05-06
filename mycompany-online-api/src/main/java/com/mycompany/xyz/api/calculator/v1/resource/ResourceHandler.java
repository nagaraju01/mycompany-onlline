/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.calculator.v1.resource;

import com.mycompany.xyz.api.common.resource.Resource;
import com.mycompany.xyz.api.common.resource.response.ApiResponse;
import com.mycompany.xyz.util.MessageHandler;
import com.mycompany.xyz.util.RequestUtils;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * ResourceHandler abstract class
 *
 * @author nagarajut
 */
@RestController
public abstract class ResourceHandler implements Resource {

    private static final Logger LOGGER = Logger.getLogger(ResourceHandler.class);

    @Autowired
    HttpServletRequest httpRequest;

    @Autowired
    protected MessageHandler messageHandler;

    /**
     * default constructor
     */
    public ResourceHandler() {
        super();
    }

    @Override
    public String getContextPath() {
        // Client used this URI to reach this resource method
        return httpRequest.getContextPath();
    }

    /**
     * adds an error to response
     *
     * @param code
     * @param response
     * @param args
     */
    protected void addErrorToResponse(String code, ApiResponse response, Object[] args) {
        response.addError(messageHandler.getMsgCode(code),
                messageHandler.getMsgDesc(code, args));
    }

    /**
     * gets application URL
     *
     * @return String
     */
    protected String getAppUrl() {
        return RequestUtils.getApplicationUrlPrefix(httpRequest);
    }

    /**
     * renders API Response in a standard way
     *
     * @param response
     * @return ResponseEntity<?>
     */
    protected static ResponseEntity<?> renderResponse(ApiResponse response) {
        if (null == response) {
            return ResponseEntity.badRequest().build();
        }
        return response.isSuccess() ? ResponseEntity.ok().body(response) : ResponseEntity.badRequest().body(response);
    }

}
