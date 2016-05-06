/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.common.resource.constants;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * MessageCode enumeration class
 *
 * Code - <FunctionalityPrefix><Number in 300 series>
 * Type - <ModuleName>
 * Description - <Default description> optionally used/extended whist setting
 * BusinessException/API Response
 *
 * @author nagarajut
 */
public enum MessageCode {

    /* #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*# */
    /* !!!! ANY CHANGES TO THIS LIST MUST BE VERIFIED WITH TEAM TO AVOID DUPLICATES            */
    /* #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*# */
    GENERIC_MSG("300", "", "unknown"),
    INVALID_REQUEST_MSG("302", "", "invalid request"),
    // New Calculation
    CALC_CREATION_SUCCESS_MSG("CALC301", "CALCULATION", "new calculation has been processed"),
    CALC_CREATION_FAIL_MSG("CALCJ302", "CALCULATION", "calcuation failed"),

    // default
    NONE("000", "", "");
    /* #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*# */
    /* !!!! ANY CHANGES TO THIS LIST MUST BE VERIFIED WITH TEAM TO AVOID DUPLICATES            */
    /* #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*# */

    protected final String code;

    protected final String type;

    protected final String description;

    private MessageCode(final String code, final String type, final String description) {
        this.code = code;
        this.type = type;
        this.description = description;
    }

    // lookup table
    private static final Map lookup = new HashMap();

    // populate the lookup table on loading time
    static {
        for (MessageCode s : EnumSet.allOf(MessageCode.class)) {
            lookup.put(s.getCode(), s);
        }
    }

    // this method can be used for reverse lookup purpose
    public static MessageCode get(String code) {
        return (MessageCode) lookup.get(code);
    }

    public String getCode() {
        return code;
    }

    public Integer getCodeInt() {
        return Integer.valueOf(code);
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return code + ": " + type + ": " + description;
    }

}
