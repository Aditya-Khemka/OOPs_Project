package com.finance.model;

/**
 * Represents an income transaction
 * Condition VII: Hierarchical Inheritance (extends Transaction)
 */
public class Income extends Transaction {
    private String source;
    
    // Condition II: Overloaded constructors
    public Income(double amount, String category) {
        super(amount, category);
        this.source = "General";
    }
    
    public Income(double amount, String category, String source) {
        super(amount, category);
        this.source = source;
    }
    
    @Override
    public String getType() {
        return "Income";
    }
    
    // Getters and setters
    public String getSource() {
        return source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Source: " + source;
    }
}
