/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.common.resource;

import java.io.Serializable;
import javax.ws.rs.GET;

/**
 * Resource interface for REST Web Service
 *
 * @author nagarajut
 */
public interface Resource extends Serializable {

    /**
     * Gets the Resource URI context path info
     *
     * @return String
     */
    @GET
    public String getContextPath();

}
