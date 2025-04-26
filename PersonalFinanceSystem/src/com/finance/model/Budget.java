package com.finance.model;

/**
 * Represents a budget with specific amount and time period
 */
public class Budget {
    private String name;
    private Double amount; // Condition IX: Wrapper
    private String period; // "Monthly", "Weekly", "Daily"
    
    // Condition II: Overloaded constructors
    public Budget() {
        this.name = "Default Budget";
        this.amount = 0.0;
        this.period = "Monthly";
    }
    
    public Budget(String name) {
        this.name = name;
        this.amount = 0.0;
        this.period = "Monthly";
    }
    
    public Budget(String name, double amount) {
        this.name = name;
        this.amount = amount;
        this.period = "Monthly";
    }
    
    public Budget(String name, double amount, String period) {
        this.name = name;
        this.amount = amount;
        this.period = period;
    }
    
    // Getters and setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Double getAmount() {
        return amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public String getPeriod() {
        return period;
    }
    
    public void setPeriod(String period) {
        this.period = period;
    }
    
    // Condition IV: Nested class
    public static class BudgetCalculator {
        /**
         * Calculate daily budget amount
         * @param budget The budget to calculate from
         * @return Daily budget amount
         */
        public static double calculateDailyAmount(Budget budget) {
            String period = budget.getPeriod();
            double amount = budget.getAmount();
            
            if (period.equalsIgnoreCase("Monthly")) {
                return amount / 30.0;
            } else if (period.equalsIgnoreCase("Weekly")) {
                return amount / 7.0;
            } else {
                return amount;
            }
        }
        
        /**
         * Check if spending is within budget
         * @param budget The budget to check against
         * @param spent Amount spent
         * @return true if within budget, false otherwise
         */
        public static boolean isWithinBudget(Budget budget, double spent) {
            return spent <= budget.getAmount();
        }
    }
    
    @Override
    public String toString() {
        return "Budget: " + name + " - " + amount + " (" + period + ")";
    }
}
