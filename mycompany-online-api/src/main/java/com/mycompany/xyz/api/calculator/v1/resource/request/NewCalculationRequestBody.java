/*
 * Copyright  ©  My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.calculator.v1.resource.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mycompany.xyz.api.calculator.v1.resource.model.Calculation;
import com.mycompany.xyz.api.common.resource.request.RequestBody;
import com.mycompany.xyz.web.deserializers.StdDateTimeDeserializer;
import java.util.List;
import javax.validation.constraints.Future;
import org.joda.time.DateTime;

/**
 * NewCalculationRequestBody class
 *
 * @author Nagarajut
 */
public class NewCalculationRequestBody extends RequestBody {

    private List<Calculation> calculations;

    @Future
    @JsonDeserialize(using = StdDateTimeDeserializer.class)
    private DateTime requetedTime;

    public List<Calculation> getCalculations() {
        return calculations;
    }

    public void setCalculations(List<Calculation> calculations) {
        this.calculations = calculations;
    }

    public DateTime getRequetedTime() {
        return requetedTime;
    }

    public void setRequetedTime(DateTime requetedTime) {
        this.requetedTime = requetedTime;
    }

    @Override
    public String toString() {
        return "NewCalculationRequestBody{" + "numbers=" + calculations + ", requetedTime=" + requetedTime + '}';
    }

}
