package com.finance.model;

import java.util.Date;

/**
 * Abstract class representing a financial transaction
 * Condition V: Abstract class
 */
public abstract class Transaction {
    protected Double amount; // Condition IX: Wrapper
    protected String category;
    protected Date date;
    
    public Transaction(double amount, String category) {
        this.amount = amount;
        this.category = category;
        this.date = new Date();
    }
    
    // Abstract method to be implemented by subclasses
    public abstract String getType();
    
    // Getters and setters
    public Double getAmount() {
        return amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    @Override
    public String toString() {
        return getType() + ": $" + amount + " - Category: " + category + " - Date: " + date;
    }
}
