/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.authentication;

import java.util.Arrays;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Will resolve a {@code SecurityContextResult} object when found in the params
 * of a method.
 *
 * @see HandlerMethodArgumentResolver
 *
 * @author nagarajut
 */
public class SecurityContextResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(SecurityContextResult.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {

        String[] attributes = webRequest.getAttributeNames(WebRequest.SCOPE_REQUEST);
        if (!Arrays.asList(attributes).contains("userName")) {
            throw new IllegalStateException(
                    "Could not find [userName] in the request scope. "
                    + "Is the Authenticate annotation present on the "
                    + "mehtod being called?");
        }

        String userName = (String) webRequest.getAttribute("userName",
                WebRequest.SCOPE_REQUEST);
        return resolve(userName);
    }

    /**
     * Finds the user by it's user name and returns an implementation of the
     * {@code SecurityContext} containing userful user/auth utility methods.
     *
     * @param userName The user name to find the user
     * @return	{@code SecurityContextResult} the result
     *
     * @see SecurityContextResult
     */
    private SecurityContextResult resolve(String userName) {
        return new SecurityContextResult(userName);
    }
}
