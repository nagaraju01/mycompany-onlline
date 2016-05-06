/*
 * Copyright  © My Company. All Rights Reserved.
 */
package com.mycompany.xyz.api.authentication;

/**
 * SecurityContextResult class
 *
 * @author nagarajut
 */
public class SecurityContextResult implements SecurityContext<String, String> {

    private String user;

    public SecurityContextResult(String user) {
        this.user = user;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public boolean isUserInRole(String role) {
        return Boolean.TRUE;
    }

}
