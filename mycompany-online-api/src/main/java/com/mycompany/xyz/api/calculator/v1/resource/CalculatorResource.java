/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.calculator.v1.resource;

import com.mycompany.xyz.api.authentication.Authenticate;
import com.mycompany.xyz.api.authentication.SecurityContextResult;
import com.mycompany.xyz.api.calculator.v1.resource.request.NewCalculationRequest;
import com.mycompany.xyz.api.calculator.v1.resource.request.NewCalculationRequestBody;
import com.mycompany.xyz.api.common.resource.Resource;
import com.mycompany.xyz.exception.NotFoundException;
import javax.ws.rs.core.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ProjectResource class
 *
 * @author Nagarajut
 */
public interface CalculatorResource extends Resource {

    /**
     * creates a new calculation based on the request
     *
     * @param routeId The route id of the Calculation request
     * @param requestBody an instance of Class#NewCalculationRequestBody
     * @see NewCalculationRequest
     * @param ctx The {@code SecurityContextResult} that can be accessed to
     * retrieve user details since this is an authenticated request
     * @param result an instance of BindingResult
     * @see NewCalculationRequest
     * @return an instance of NewCalculationResponse
     * @throws NotFoundException
     */
    @Authenticate
    @RequestMapping(value = "/calculate/{id}", method = RequestMethod.POST,
            headers = "Accept=application/json",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    ResponseEntity<?> newCalculation(@PathVariable Integer id, @RequestBody NewCalculationRequestBody requestBody, SecurityContextResult ctx,
            BindingResult result) throws NotFoundException;

}
