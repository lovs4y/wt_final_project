package com.prazhmovska.vladyslava.wt_final_project.service;

/**
 * Service which contains common authentication methods. Useful for services which needs
 * current security context details like Principal details.
 */
public class AuthorizedContext {

    /**
     * Gets current user id from security context using {@link AuthenticationHelper}.
     *
     * @return current user id.
     */
    public Long getCurrentUserId() {
        return AuthenticationHelper.getCurrentUserId();
    }
}
