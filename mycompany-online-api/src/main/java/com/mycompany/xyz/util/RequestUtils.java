/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.util;

import java.nio.charset.Charset;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;

/**
 * Requests utility class
 *
 * @author nagarajut
 */
public class RequestUtils {

    private RequestUtils() {
    }

    /**
     * Returns application URL
     *
     * Example: If current requested page has URL
     * <code>http://localhost:8080/xyz/somePage?params=1</code>
     *
     * The method will return <code>http://localhost:8080/xyz</code>
     *
     *
     * @param request
     * @return application url
     */
    public static String getApplicationUrlPrefix(final HttpServletRequest request) {
        boolean includePort = true;
        if ("http".equals(request.getScheme().toLowerCase()) && (request.getServerPort() == 80)) {
            includePort = false;
        }
        if ("https".equals(request.getScheme().toLowerCase()) && (request.getServerPort() == 443)) {
            includePort = false;
        }
        StringBuilder url = new StringBuilder();
        url.append(request.getScheme()).append("://");
        url.append(request.getServerName());
        if (includePort) {
            url.append(":").append(request.getServerPort());
        }
        if (request.getRequestURI().startsWith(request.getContextPath())) {
            url.append(request.getContextPath());
        }
        return url.toString();
    }

    public static String getPartOfUrlAfterPattern(HttpServletRequest request, final String charSeq) {
        final String URI = request.getRequestURI();
        final String contextPath = request.getContextPath();
        final String pattern = URI.replaceFirst(contextPath, "");
        final int pos = pattern.indexOf(charSeq);
        if (pos != -1) {
            return pattern.substring(pos + charSeq.length());
        }
        return null;
    }

    /**
     * Generates Baic Authorization header value for given username and password
     *
     * Example: {@code Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==}
     *
     * @param username
     * @param password
     * @return Basic Authorization header
     *
     * @throws IllegalArgumentException - in case is username or password null
     */
    public static String createBasicAuthHeader(final String username, final String password) {
        if (username == null || password == null) {
            throw new IllegalArgumentException("Username and pass can not be null");
        }
        final byte[] credentials = (username + ":" + password).getBytes(Charset.forName("US-ASCII"));
        return "Basic " + Base64.encodeBase64String(credentials);
    }

}
