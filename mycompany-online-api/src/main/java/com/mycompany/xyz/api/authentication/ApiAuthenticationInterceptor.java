/*
 * Copyright © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.authentication;

import com.google.gson.GsonBuilder;
import com.mycompany.xyz.exception.RestExceptionResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * ApiAuthenticationInterceptor class - Will intercept a request and check for
 * valid user credentials whenever a
 * {@code com.mycompany.xyz.api.authentication.Authenticate} annotation is
 * found on the handler method.
 *
 * @author nagarajut
 */
public class ApiAuthenticationInterceptor extends HandlerInterceptorAdapter {


    private Logger logger = LoggerFactory.getLogger(ApiAuthenticationInterceptor.class);

    /**
	 * Intercept the execution of a handler. Called after HandlerMapping determined
	 * an appropriate handler object, but before HandlerAdapter invokes the handler.
	 * 
	 * Implemented in a way to check whether the user credentials are found in the 
	 * request and are valid by making a call to the service layer.
	 * 
	 * @param request current HTTP request
	 * @param response current HTTP response
	 * @param handler chosen handler to execute, for type and/or instance evaluation
	 * @return {@code true} if the execution chain should proceed with the following 
	 * interceptor or the handler itself
	 * @throws Exception in case of errors
	 * @see ApiAuthenticationInterceptor#shouldAuthenticate(Method)
	 * @see ApiAuthenticationInterceptor#isValidCredentials(String, String)
	 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    	
    	// Extract userName and apiKey from the request
        String userName = request.getParameter("userName");
        String apiKey   = request.getParameter("apiKey");
        
        if (userName == null) {
        	// Not found as part of the parameters
        	userName = request.getHeader("userName");
        }
        
        if (apiKey == null) {
        	// Not found as part of the parameters
        	apiKey = request.getHeader("apiKey");
        }

        // In case no method is been matched
        if (handler instanceof HandlerMethod) {
        	
        	// The method being called
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            // Checks if the annotation exists and if the provided
            // credentials are valid. If details given don't match 
            // our database records halt with a 401 response
            if (shouldAuthenticate(handlerMethod.getMethod()) 
            		&& !isValidCredentials(userName, apiKey)) {
                return halt(response);
            }

            // Keep user in request context; if needed
            request.setAttribute("userName", userName);
        }

        // Continue to interceptors chain or handle method
        return super.preHandle(request, response, handler);
    }

    /**
     * Checks whether the provided credentials (username and api key) 
     * are valid or not.
     *
     * @param userName The user name
     * @param apiKey The api key
     * @return {@code true} or {@code false} depending on whether the 
     * credentials are valid; or not
     * @see UserService#getByUsername(String)
     */
    private boolean isValidCredentials(String userName, String apiKey) {

        // Check for non-empty credentials
        if (StringUtils.isEmpty(userName)) return false;
        if (StringUtils.isEmpty(apiKey))   return false;

        // User can't be null and api key must match
        return "manager".equals(userName) && "abcd1234".equals(apiKey);
    }

    /**
     * Returns whether the controller method being called has the specified
     * annotation.
     *
     * @param method The method being called
     * @return Whether the specified annotation is present
     */
    private boolean shouldAuthenticate(Method method) {
        return AnnotationUtils.findAnnotation(method, Authenticate.class) != null;
    }

    /**
     * always render the response in standard API response format
     *
     * @return APIExceptionResponse
     */
    private RestExceptionResponse apiErrResponse() {
        RestExceptionResponse response = new RestExceptionResponse();
        response.setSuccess(Boolean.FALSE);
        response.setMessage("Invalid request");
        Map<String, String> errors = new HashMap<>();
        errors.put("athentication", "userName or apiKey missing or invalid");
        response.setErrors(errors);
        return response;
    }

    /**
     * Adds the appropriate unauthorised code and message to the
     * {@code HttpServletResponse} being returned.
     *
     * @param response {@code HttpServletResponse}
     */
    private boolean halt(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(new GsonBuilder().create().toJson(apiErrResponse()));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
            logger.warn("Cannot write to response");
        }
        logger.warn("Invalid user credentials supplied");
        return false;
    }
}
