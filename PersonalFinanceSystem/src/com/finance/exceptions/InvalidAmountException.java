package com.finance.exceptions;

/**
 * Custom exception for invalid financial amounts
 * Condition XI: Exception handling
 */
public class InvalidAmountException extends Exception {
    // Condition II: Overloaded constructors
    public InvalidAmountException() {
        super("Invalid amount detected!");
    }
    
    public InvalidAmountException(String message) {
        super(message);
    }
}
