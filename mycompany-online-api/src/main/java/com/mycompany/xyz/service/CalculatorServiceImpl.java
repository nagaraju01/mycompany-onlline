/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.service;

import com.mycompany.xyz.api.calculator.v1.resource.model.Calculation;
import com.mycompany.xyz.dto.calculator.CalculatorDTO;
import org.springframework.stereotype.Service;

/**
 * CalculatorServiceImpl class
 *
 * @author nagarajut
 */
@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public CalculatorDTO calculateNumbers(CalculatorDTO calculatorDTO) {

        if (null == calculatorDTO) {
            return calculatorDTO;
        }

        Integer result = 0;

        for (Calculation calc : calculatorDTO.getCalculations()) {
            switch (calc.getType()) {
                case "1": // sum up
                    result = calc.getNumbers().stream().map((number) -> number).reduce(result, Integer::sum);
                    break;
                case "2": // multiply
                    result = calc.getNumbers().stream().map((number) -> number).reduce(result, (accumulator, _item) -> accumulator * _item);
                    break;
            }
            if (null != result) {
                calc.setResult(result.doubleValue());
            }
        }

        return calculatorDTO;
    }

}
