/*
 * Copyright  ©  My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.calculator.v1.resource.response;

import com.mycompany.xyz.api.common.resource.response.ApiResponse;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * AbstractCalcResponse class
 *
 * @author nagarajut
 */
@XmlRootElement
public abstract class AbstractCalcResponse extends ApiResponse {

    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AbstractCalcResponse{" + "id=" + id + '}';
    }

}
