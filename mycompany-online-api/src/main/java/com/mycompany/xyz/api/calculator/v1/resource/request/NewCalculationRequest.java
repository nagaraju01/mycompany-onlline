/*
 * Copyright  ©  My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.calculator.v1.resource.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mycompany.xyz.api.calculator.v1.resource.model.Calculation;
import com.mycompany.xyz.web.deserializers.StdDateTimeDeserializer;
import java.util.List;
import javax.validation.constraints.Future;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.joda.time.DateTime;

/**
 * NewCalculationRequest class - for holding request parameters of calculation
 * request
 *
 * @author nagarajut
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewCalculationRequest extends AbstractCalcRequest {

    private Integer id;

    private List<Calculation> calculations;

    @Future
    @JsonDeserialize(using = StdDateTimeDeserializer.class)
    private DateTime requetedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        return "NewCalculationRequest{" + "id=" + id +  ", calculations=" + calculations
                + ", requetedTime=" + requetedTime + '}';
    }

}
