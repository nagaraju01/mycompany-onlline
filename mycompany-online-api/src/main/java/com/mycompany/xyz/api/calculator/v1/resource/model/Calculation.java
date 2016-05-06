/*
 * Copyright  ©  My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.calculator.v1.resource.model;

import java.util.List;

/**
 * Calculation class
 *
 * @author Nagarajut
 */
public class Calculation {

    private String type;

    private List<Integer> numbers;

    private Double result;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Calculation{" + "type=" + type + ", numbers=" + numbers + ", result=" + result + '}';
    }

}
