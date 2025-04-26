package com.finance.interfaces;

/**
 * Interface for authentication functionality
 * Condition VI: Interface
 */
public interface Authentication {
    /**
     * Authenticate a user with username and password
     * @param username User's username
     * @param password User's password
     * @return true if authentication successful, false otherwise
     */
    boolean authenticate(String username, String password);
    
    /**
     * Log out the current user
     */
    void logout();
    
    /**
     * Check if a user is currently authenticated
     * @return true if a user is authenticated, false otherwise
     */
    boolean isAuthenticated();
}
