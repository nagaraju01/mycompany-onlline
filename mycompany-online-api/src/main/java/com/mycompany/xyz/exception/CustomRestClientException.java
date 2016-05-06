/*
 * Copyright  ©  My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClientException;

/**
 * CustomRestClientException class
 *
 * @author nagarajut
 */
@SuppressWarnings("serial")
public class CustomRestClientException extends RestClientException {

    private HttpHeaders headers;

    private HttpStatus status;

    private String body;

    public CustomRestClientException(ClientHttpResponse res) throws IOException {
        super(res.getStatusText());
        setClientHttpResponse(res);
    }

    public CustomRestClientException(String msg) {
        super(msg);
    }

    public CustomRestClientException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }

    public CustomRestClientException(String msg, ClientHttpResponse res) throws IOException {
        super(msg);
        setClientHttpResponse(res);
    }

    public CustomRestClientException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public CustomRestClientException(String msg, Throwable ex, ClientHttpResponse res) throws IOException {
        super(msg, ex);
        setClientHttpResponse(res);
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setBody(InputStream body) throws IOException {
        if (body != null) {
            this.body = IOUtils.toString(body);
        }

    }

    public void setClientHttpResponse(ClientHttpResponse res) throws IOException {
        headers = res.getHeaders();
        status = res.getStatusCode();
        setBody(res.getBody());
    }

    @Override
    public String toString() {
        final StringBuilder message = new StringBuilder(super.toString());

        if (headers != null) {
            message.append(" Headers: [");
        }

        for (Map.Entry<String, String> entry : headers.toSingleValueMap().entrySet()) {
            message.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append(" ");

        }
        message.append(" Body [").append(getBody()).append("]");
        return message.toString();
    }

}
