/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.common.resource.constants;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * ErrorCode enumeration class
 *
 * Code - <FunctionalityPrefix><Number in 000 to 100 series>
 * Type - <ModuleName>
 * Description - <Default description> optionally used/extended whist setting
 * BusinessException/API Response
 *
 * @author nagarajut
 */
public enum ErrorCode {

    /* #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*# */
    /* !!!! ANY CHANGES TO THIS LIST MUST BE VERIFIED WITH TEAM TO AVOID DUPLICATES            */
    /* #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*# */
    GENERIC_ERR("XYZ000", "XYZ", "unknown"),
    REQUEST_NULL_ERR("XYZ001", "XYZ", "Request cannot be null "),
    INVALID_DATE_ERR("XYZ002", "XYZ", "invalid date format"),
    USER_HAS_NO_PERMISSION_TO_ACCESS_THE_PROJECT_ERR("XYZ003", "XYZ", "User has no permission to access the resource"),
    // Calcualtion
    CALC_ID_MANDATORY_ERR("CALC002", "CALCULATION", "Project Id parameter is mandatory"),
    CALC_FAIL_ERR("CALC002", "CALCULATION", ""),
    INVALID_CALC_ID_ERR("CALC003", "CALCULATION", "Invalid Project Id"),
    // calculator
    CALC_ESTIMATE_UNKNOWN_ERR("CALCESTIMATE001", "CALCULATION", "Unkown error"),
    CALC_NEW_LEAD_CREATION_UNKNOWN_ERR("CALC_NEW_LEAD_CREATION001", "CALCULATION", "Unkown error"),
    
    // default
    NONE("000", "", "");
    /* #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*# */
    /* !!!! ANY CHANGES TO THIS LIST MUST BE VERIFIED WITH TEAM TO AVOID DUPLICATES            */
    /* #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*# */

    protected final String code;
    protected final String type;
    protected final String description;

    private ErrorCode(final String code, final String type, final String description) {
        this.code = code;
        this.type = type;
        this.description = description;
    }

    // lookup table
    private static final Map lookup = new HashMap();

    // populate the lookup table on loading time
    static {
        for (ErrorCode s : EnumSet.allOf(ErrorCode.class)) {
            lookup.put(s.getCode(), s);
        }
    }

    // this method can be used for reverse lookup purpose
    public static ErrorCode get(String code) {
        return (ErrorCode) lookup.get(code);
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
