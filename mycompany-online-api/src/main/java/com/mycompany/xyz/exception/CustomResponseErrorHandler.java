/*
 * Copyright  ©  My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.exception;

import java.io.IOException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * CustomResponseErrorHandler class
 *
 * @author nagarajut
 */
public class CustomResponseErrorHandler implements ResponseErrorHandler {

    private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return errorHandler.hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        throw new CustomRestClientException(response);
    }

}
