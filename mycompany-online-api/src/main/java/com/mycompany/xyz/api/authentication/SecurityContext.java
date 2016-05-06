/*
 * Copyright  © My Company Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.api.authentication;

/**
 * SecurityContext interface
 *
 * @author nagarajut
 */
public interface SecurityContext<U, R> {

    /**
     * Returns a user of the given type.
     *
     * @return the user
     */
    U getUser();

    /**
     * Returns a boolean indicating whether the authenticated user is included
     * in the specified logical "role". If the user has not been authenticated,
     * the method returns <code>false</code>.
     *
     * @param role a <code>Object</code> specifying the name of the role
     * @return a <code>boolean</code> indicating whether the user making the
     * request belongs to a given role; <code>false</code> if the user has not
     * been authenticated
     */
    boolean isUserInRole(R role);
}
