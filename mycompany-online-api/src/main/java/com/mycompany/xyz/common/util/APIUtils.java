/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * APIUtils Class
 *
 * @author nagarajut
 */
public class APIUtils {

    /**
     * gets ip address from HttpServletRequest
     *
     * @param request
     * @return String
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (null != ip && !"".equals(ip.trim())
                && !"unknown".equalsIgnoreCase(ip)) {
            return " - " + ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (null != ip && !"".equals(ip.trim())
                && !"unknown".equalsIgnoreCase(ip)) {
            // get first ip from proxy ip
            int index = ip.indexOf(',');
            if (index != -1) {
                return " - " + ip.substring(0, index);
            } else {
                return " - " + ip;
            }
        }
        return " - " + request.getRemoteAddr();
    }

    /**
     * Utility method which convert Null Boolean to false.
     *
     * @param value
     * @return false in case if is given value null, true otherwise
     */
    public static Boolean toBoolean(Boolean value) {
        return value == null ? Boolean.FALSE : value;
    }

    /**
     * Utility method which convert Null String to blank String.
     *
     * @param value
     * @return blank string in case if is given value null, value otherwise
     */
    public static String toBlank(String value) {
        return value == null ? "" : value;
    }

}
