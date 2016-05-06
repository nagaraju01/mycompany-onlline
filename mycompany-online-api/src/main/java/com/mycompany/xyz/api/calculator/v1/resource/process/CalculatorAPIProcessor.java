/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.calculator.v1.resource.process;

import com.mycompany.xyz.api.authentication.SecurityContextResult;
import com.mycompany.xyz.api.calculator.v1.resource.model.Calculation;
import com.mycompany.xyz.api.calculator.v1.resource.request.NewCalculationRequest;
import com.mycompany.xyz.api.calculator.v1.resource.request.NewCalculationRequestBody;
import com.mycompany.xyz.api.calculator.v1.resource.response.NewCalculationResponse;
import com.mycompany.xyz.api.common.resource.constants.ErrorCode;
import com.mycompany.xyz.api.common.resource.constants.MessageCode;
import com.mycompany.xyz.api.common.resource.process.BaseProcessAPIProcessor;
import com.mycompany.xyz.common.util.ResponseUtils;
import com.mycompany.xyz.common.validator.ValidationUtil;
import com.mycompany.xyz.dto.calculator.CalculatorDTO;
import com.mycompany.xyz.exception.BusinessException;
import com.mycompany.xyz.exception.NotFoundException;
import com.mycompany.xyz.service.CalculatorService;
import com.mycompany.xyz.util.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CalculatorAPIProcessor class
 *
 * @author nagarajut
 */
@Component("calculatorAPIProcessor")
public class CalculatorAPIProcessor extends BaseProcessAPIProcessor {

    private NewCalculationRequest request;

    @Autowired
    ValidationUtil validationUtil;

    @Autowired
    ResponseUtils responseUtils;

    @Autowired
    protected ReflectionUtils reflectionUtils;

    @Autowired
    protected CalculatorService calculatorService;

    /**
     * default constructor
     */
    public CalculatorAPIProcessor() {
        this.request = null;
    }

    /**
     * constructor
     *
     * @param logger
     * @param request
     */
    public CalculatorAPIProcessor(NewCalculationRequest request) {
        this.request = request;
    }

    /**
     * executes to process request and return response
     *
     * @param response NewCalculationRequestBody
     * @return NewCalculationResponse
     */
    public Boolean execute(SecurityContextResult ctx, Integer id, NewCalculationRequestBody requestBody,
            NewCalculationResponse response) throws NotFoundException {

        try {

            logger.info("Processing new calculation started....");
            request = new NewCalculationRequest();
            request.setId(id);
            reflectionUtils.transform(requestBody, request);
            logger.info(request.toString());

            if (response.getErrors().isEmpty()) {

                validateCalculatorRequest(request); // core business validation

                CalculatorDTO calculatorDTO = getDTOFromRequest(request);

                // process new calcuation request
                CalculatorDTO calculatorResultDTO = calculatorService.calculateNumbers(calculatorDTO);
                logger.info("processing of new calculation successful..." + calculatorResultDTO.toString());
                response.setId(calculatorDTO.getId());
                response.setRequetedTime(requestBody.getRequetedTime());
                response.setCalculations(calculatorDTO.getCalculations());

                // add warning(s)
                // .....
                // populate the API response
                response.setMessage(MessageCode.CALC_CREATION_SUCCESS_MSG.getDescription());
                response.setSuccess(Boolean.TRUE);
                return Boolean.TRUE;

            } else {
                logger.info("exception  when processing new calculation");
                response.setSuccess(Boolean.FALSE);
                response.setMessage(MessageCode.CALC_CREATION_FAIL_MSG.getDescription());
                response.addError(ErrorCode.CALC_NEW_LEAD_CREATION_UNKNOWN_ERR.getCode(),
                        ErrorCode.CALC_NEW_LEAD_CREATION_UNKNOWN_ERR.getDescription()); // TODO: catch error messages
            }

        } catch (BusinessException ex) {
            logger.info("exception  when processing new calculation");
            logger.error(ex);
            response.setSuccess(Boolean.FALSE);
            response.setMessage(MessageCode.CALC_CREATION_FAIL_MSG.getDescription());
            responseUtils.addError(ex, response);

        }

        return Boolean.TRUE;
    }

    /**
     * generates DTO from api request
     *
     * @param request
     * @return CalculatorLeadDTO
     */
    private CalculatorDTO getDTOFromRequest(NewCalculationRequest request) {
        CalculatorDTO dto = new CalculatorDTO();
        dto.setId(request.getId());
        dto.setCalculations(request.getCalculations());
        dto.setRequetedTime(request.getRequetedTime());
        return dto;
    }

    /**
     * validates calculator request - extended core business validation
     *
     * @param request
     * @throws BusinessException
     */
    private void validateCalculatorRequest(NewCalculationRequest request) throws BusinessException {
        // validates the request - business level
        if (null != request.getCalculations()) {
            for (Calculation calc : request.getCalculations()) {
                if (null == calc.getType()) {
                    throw new BusinessException("api.calculator.common.type.mandatory");
                }
            }
        }
    }

}
