/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.common.resource.constants;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * WarningCode enumeration class
 *
 * Code - <FunctionalityPrefix><Number in 200 series>
 * Type - <ModuleName>
 * Description - <Default description> optionally used/extended whist setting
 * BusinessException/API Response
 *
 * @author nagarajut
 */
public enum WarningCode {

    /* #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*# */
    /* !!!! ANY CHANGES TO THIS LIST MUST BE VERIFIED WITH TEAM TO AVOID DUPLICATES            */
    /* #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*# */
    GENERIC_WARN("NRGI200", "NRGI", "unknown"),
    // Project 
    CALC_WARN("CALCWARN276", "CALCULATION", "Unable to do additional calculation"),
    
    // default
    NONE("000", "", "");
    /* #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*# */
    /* !!!! ANY CHANGES TO THIS LIST MUST BE VERIFIED WITH TEAM TO AVOID DUPLICATES            */
    /* #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*# */

    protected final String code;

    protected final String type;

    protected final String description;

    private WarningCode(final String code, final String type, final String description) {
        this.code = code;
        this.type = type;
        this.description = description;
    }

    // lookup table
    private static final Map lookup = new HashMap();

    // populate the lookup table on loading time
    static {
        for (WarningCode s : EnumSet.allOf(WarningCode.class)) {
            lookup.put(s.getCode(), s);
        }
    }

    // this method can be used for reverse lookup purpose
    public static WarningCode get(String code) {
        return (WarningCode) lookup.get(code);
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
