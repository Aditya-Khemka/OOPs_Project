package com.finance.model;

/**
 * Represents an expense transaction
 * Condition VII: Hierarchical Inheritance (extends Transaction)
 */
public class Expense extends Transaction {
    private boolean isNecessary;
    
    // Condition II: Overloaded constructors
    public Expense(double amount, String category) {
        super(amount, category);
        this.isNecessary = true;
    }
    
    public Expense(double amount, String category, boolean isNecessary) {
        super(amount, category);
        this.isNecessary = isNecessary;
    }
    
    @Override
    public String getType() {
        return "Expense";
    }
    
    // Getters and setters
    public boolean isNecessary() {
        return isNecessary;
    }
    
    public void setNecessary(boolean necessary) {
        isNecessary = necessary;
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Necessary: " + (isNecessary ? "Yes" : "No");
    }
}
