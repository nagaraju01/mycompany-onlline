/*
 * Copyright  ©  My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.calculator.v1.resource.request;

import com.mycompany.xyz.api.common.resource.request.ApiRequest;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * AbstractCalcRequest class
 *
 * @author nagarajut
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbstractCalcRequest extends ApiRequest {

}
