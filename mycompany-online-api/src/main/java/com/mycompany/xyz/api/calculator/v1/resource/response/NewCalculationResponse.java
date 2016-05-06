/*
 * Copyright  ©  My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.calculator.v1.resource.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mycompany.xyz.api.calculator.v1.resource.model.Calculation;
import com.mycompany.xyz.web.serializers.DateTimeSerializer;
import java.util.List;
import javax.validation.constraints.Future;
import javax.xml.bind.annotation.XmlRootElement;
import org.joda.time.DateTime;

/**
 * NewCalculationResponse class
 *
 * @author nagarajut
 */
@XmlRootElement
public class NewCalculationResponse extends AbstractCalcResponse {

    // add properties if required
    private List<Calculation> calculations;

    @Future
    @JsonSerialize(using = DateTimeSerializer.class)
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

}
