/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.common.resource.response;

import com.mycompany.xyz.common.BaseResponse;

/**
 * Response abstract supper class for all resource responses.
 *
 * @author nagarajut
 */
public abstract class ApiResponse extends BaseResponse {

    // add any additional properties if required
    public ApiResponse() {
        super();
        setSuccess(Boolean.FALSE);
    }

}
