/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.common.resource.request;

import java.io.Serializable;

/**
 * Request abstract supper class for all resource requests.
 *
 * @author nagarajut
 */
public abstract class ApiRequest implements Serializable {

    protected String userName;

    protected String apiKey;

    public ApiRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userId) {
        this.userName = userId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String toString() {
        return "ApiRequest{" + "userName=" + userName + ", apiKey=" + apiKey + '}';
    }


}
