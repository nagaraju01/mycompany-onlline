/*
 * Copyright  ©  My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.calculator;

import com.mycompany.xyz.api.calculator.v1.resource.request.NewCalculationRequestBody;


/**
 * An object encapsulating NewCalculationRequestBody in a JSON format.
 *
 * To be used for E2E API tests
 *
 * @author nagarajut
 */
public class NewCalculationJSONRequest extends NewCalculationRequestBody {

    private String requetedTimeString;

    public String getRequetedTimeString() {
        return requetedTimeString;
    }

    public void setRequetedTimeString(String requetedTimeString) {
        this.requetedTimeString = requetedTimeString;
    }

    @Override
    public String toString() {
        return super.toString() + "- NewCalculationJSONRequest{ requetedTimeString=" + requetedTimeString
                + "}";
    }
}
