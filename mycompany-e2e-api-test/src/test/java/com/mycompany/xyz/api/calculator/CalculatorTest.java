/*
 * Copyright  ©  My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.calculator;

import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.mycompany.xyz.api.calculator.v1.resource.model.Calculation;
import com.mycompany.xyz.api.calculator.v1.resource.request.NewCalculationRequest;
import com.mycompany.xyz.api.calculator.v1.resource.request.NewCalculationRequestBody;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.springframework.http.HttpStatus;

/**
 * E2E test for the Calculator (i.e.
 * {@link com.mycompany.xyz.api.calculator.v1.resource.CalculatorResource}
 *
 * Note* - Requires SCOL app to be running
 *
 * @author nagarajut
 */
public class CalculatorTest {

    private static final String RESOURCE_URL = "http://localhost:8080/xyz/api/v1/calculator";

    private static final String USERNAME = "manager";
    private static final String API_KEY = "abcd1234";

    /**
     * E2E test method that test the full journey null null null null null null
     * null null null null     {@link com.mycompany.xyz.api.calculator.v1.resource.CalculatorResource#getMultipleCalculations(
     * MultipleCalculationsRequestBody, SecurityContextResult, BindingResult)}
     */
    @Test
    public void testGetMultipleCalculations() {

        Integer array[] = {2, 3};
        List<Integer> list = Arrays.asList(array);

        given()
                .auth().none()
                .contentType(ContentType.JSON)
                .request()
                .body(new String(
                                "{"
                                + "\"calculations\": 	[{"
                                + "\"type\" : \"1\","
                                + "\"numbers\" : [2,3]"
                                + "},"
                                + "{"
                                + "\"type\" : \"2\","
                                + "\"numbers\" : [2,3]"
                                + "}],"
                                + "\"requetedTime\" : \"2017/01/23 14:30:00\""
                                + "}"))
                .log().all() // log all for debugging/information purpose
                .when()
                .post(RESOURCE_URL + "/calculate/1" + "?userName=" + USERNAME + "&apiKey=" + API_KEY)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("success", equalTo(true))
                .body("message", equalTo("new calculation has been processed"))
                .body("calculations.type", hasItems("1", "2"))
                .body("calculations.numbers", hasItems(list))
                .log().all() // log all for debugging/information purpose
                ;

    }

    /**
     * E2E test method that test the full journey for null null null null null
     * null null null null null null null null null null null null     {@link com.mycompany.xyz.api.calculator.v1.resource.CalculatorResource#newCalculation(
     * Integer, NewCalculationRequestBody, SecurityContextResult, BindingResult)}
     */
    @Test
    public void testNewCalculations_withType() {

        testNewCalculation(RESOURCE_URL + "/calculate" + "/" + 1 + "?userName=" + USERNAME
                + "&apiKey=" + API_KEY);

        testNewCalculation(RESOURCE_URL + "/calculate" + "/" + 2 + "?userName=" + USERNAME
                + "&apiKey=" + API_KEY);

    }

    /**
     * Generic method for calling API add new calculation method
     *
     * @param resourceURL the URL resource
     */
    private void testNewCalculation(String resourceURL) {
        given()
                .auth().none()
                .contentType(ContentType.JSON)
                .request()
                .body(CalculatorTestData.getNewCalculationJSONRequest().toString())
                .log().all() // log all for debugging/information purpose
                .when()
                .post(resourceURL)
                .then()
                .log().ifError() // log all for debugging/information purpose
                .statusCode(HttpStatus.OK.value())
                .body("success", equalTo(true))
                .body("message", equalTo("Calculator result details have been returned"));
    }

    /**
     * Test Data class for making and returning test data
     *
     * @author nagarajut
     */
    private static class CalculatorTestData {

        /**
         * Returns a sample {@link NewCalculationRequestBody} object for test
         * use
         *
         * @return {@link NewCalculationRequestBody}
         */
        public static NewCalculationRequest getNewCalculationJSONRequest() {

            return new NewCalculationRequest() {
                {
                    setCalculations(getTestCalculations());
                    DateTimeFormatter formatter = DateTimeFormat
                            .forPattern("yyyy/MM/dd HH:mm:ss");
                    setRequetedTime(formatter.parseDateTime("2017/01/23 14:30:00"));
                }
            };
        }
    }

    static List getTestCalculations() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(5);
        Calculation calculation1 = new Calculation();
        calculation1.setType("1");
        calculation1.setNumbers(numbers);

        Calculation calculation2 = new Calculation();
        calculation2.setType("2");
        calculation2.setNumbers(numbers);
        return asList(calculation1, calculation2);
    }
}
