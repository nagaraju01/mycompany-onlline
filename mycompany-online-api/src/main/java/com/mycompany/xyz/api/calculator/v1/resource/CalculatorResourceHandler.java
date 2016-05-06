/*
 * Copyright  ©  My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.calculator.v1.resource;

import com.mycompany.xyz.api.authentication.SecurityContextResult;
import static com.mycompany.xyz.api.calculator.v1.resource.ResourceHandler.renderResponse;
import com.mycompany.xyz.api.calculator.v1.resource.process.CalculatorAPIProcessor;
import com.mycompany.xyz.api.calculator.v1.resource.request.NewCalculationRequestBody;
import com.mycompany.xyz.api.calculator.v1.resource.response.NewCalculationResponse;
import com.mycompany.xyz.api.calculator.v1.resource.validation.NewCalculationValidator;
import com.mycompany.xyz.exception.NotFoundException;
import javax.validation.ValidationException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * CalculatorResourceHandler class or interface Description on a single line
 *
 * which will be called for getting calculator results.
 *
 * @author Nagarajut
 */
@RestController
@RequestMapping("/api/v1/calculator")
@Component("calculatorResourceV1")
public class CalculatorResourceHandler extends ResourceHandler implements CalculatorResource {

    private static final Logger LOGGER = Logger.getLogger(CalculatorResourceHandler.class);

    @Autowired
    private NewCalculationValidator newCalculationValidator;

    @Autowired
    @Qualifier("calculatorAPIProcessor")
    private CalculatorAPIProcessor calculatorAPIProcessor;

    /**
     * handles Add new lead resource request
     *
     * @param id
     * @param requestBody
     * @param ctx
     * @param result
     * @return an instance of NewCalculationResponse
     * @throws ValidationException
     * @throws NotFoundException
     */
    @Override
    public ResponseEntity<?> newCalculation(@PathVariable Integer id, @RequestBody NewCalculationRequestBody requestBody,
            SecurityContextResult ctx, BindingResult result) throws NotFoundException {
        // authorise and validate the request
        newCalculationValidator.validate(id, requestBody, ctx, result);

        // process the valid request
        NewCalculationResponse response = new NewCalculationResponse();
        calculatorAPIProcessor.execute(ctx, id, requestBody, response);
        
        // return the response in api standard format
        return renderResponse(response);
    }

}
