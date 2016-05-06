/*
 * Copyright © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.dto.calculator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mycompany.xyz.api.calculator.v1.resource.model.Calculation;
import com.mycompany.xyz.web.serializers.DateTimeSerializer;
import java.util.List;
import org.joda.time.DateTime;

/**
 * CalculatorLeadDTO class
 *
 * @author nagarajut
 */
public class CalculatorDTO {

    private Integer id;

    private List<Calculation> calculations;

    @JsonSerialize(using = DateTimeSerializer.class)
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

    public void setCalculations(List<Calculation> numbers) {
        this.calculations = numbers;
    }

    public DateTime getRequetedTime() {
        return requetedTime;
    }

    public void setRequetedTime(DateTime createdDate) {
        this.requetedTime = createdDate;
    }

    @Override
    public String toString() {
        return "CalculatorDTO{" + "id=" + id + ", numbers=" + calculations +
                ", requetedTime=" + requetedTime + '}';
    }

}
