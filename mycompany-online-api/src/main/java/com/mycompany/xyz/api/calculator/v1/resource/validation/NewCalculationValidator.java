/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.calculator.v1.resource.validation;

import com.mycompany.xyz.api.authentication.SecurityContextResult;
import com.mycompany.xyz.api.calculator.v1.resource.model.Calculation;
import com.mycompany.xyz.api.calculator.v1.resource.request.NewCalculationRequestBody;
import com.mycompany.xyz.api.common.resource.request.RequestBody;
import com.mycompany.xyz.common.validator.BaseRequestValidator;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

/**
 * NewCalculationValidator class
 *
 * @author Nagarajut
 */
@Component
public class NewCalculationValidator extends BaseRequestValidator {

    @Autowired
    private Validator validator;

    @Override
    public boolean supports(Class<?> type) {
        return RequestBody.class.equals(type);
    }

    public void validate(Integer id, NewCalculationRequestBody requestBody, SecurityContextResult ctx, BindingResult result) throws
            ValidationException {

        // Using a validator check for any bean violations
        Set<ConstraintViolation<NewCalculationRequestBody>> violations = validator.validate(requestBody);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }

        // validate the request body
        validate(requestBody, result);

        // throw if errors
        throwBusinessException(result);
    }

    /**
     * validates the request parameters and set correct values for them
     *
     * @param o and instance of AddNewLeadRequest
     * @param errors Errors
     */
    @Override
    public void validate(Object o, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        NewCalculationRequestBody request = (NewCalculationRequestBody) o;

        if (request != null) {

            // requested time is mandatory
            if (null == request.getRequetedTime()) {
                errors.reject("api.calculator.common.calcrequestedtime.mandatory", FAIL_MSG);
                return;
            }

            // numbers is mandatory
            if (null == request.getCalculations() || request.getCalculations().isEmpty()) {
                errors.reject("api.calculator.common.calcnumbers.mandatory", FAIL_MSG);
                return;
            }

            for (Calculation calc : request.getCalculations()) {
                for (Integer number : calc.getNumbers()) {
                    if (!(validationUtil.validateNumericData(String.valueOf(number)))) {
                        errors.reject("api.calculator.common.invalidnumber", FAIL_MSG);
                        return;
                    }
                }
            }

        } else {
            errors.reject("api.common.invalidrequest", FAIL_MSG);
        }
    }

}
