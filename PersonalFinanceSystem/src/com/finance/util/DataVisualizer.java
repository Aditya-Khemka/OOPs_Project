package com.finance.util;

import com.finance.model.*;

/**
 * Visualizes financial data in text format
 */
public class DataVisualizer {
    
    // Condition I: Overloaded method
    public void visualizeExpenses(User user) {
        System.out.println("\n--- Expense Visualization ---");
        
        Transaction[] transactions = user.getTransactions();
        int count = user.getTransactionCount();
        
        // Count expenses by category
        String[] categories = new String[20];
        double[] amounts = new double[20];
        int categoryCount = 0;
        
        for (int i = 0; i < count; i++) {
            if (transactions[i] instanceof Expense) {
                String category = transactions[i].getCategory();
                boolean found = false;
                
                // Check if category already exists
                for (int j = 0; j < categoryCount; j++) {
                    if (categories[j].equals(category)) {
                        amounts[j] += transactions[i].getAmount();
                        found = true;
                        break;
                    }
                }
                
                // Add new category if not found
                if (!found && categoryCount < categories.length) {
                    categories[categoryCount] = category;
                    amounts[categoryCount] = transactions[i].getAmount();
                    categoryCount++;
                }
            }
        }
        
        // Display bar chart
        for (int i = 0; i < categoryCount; i++) {
            System.out.printf("%-15s: $%-10.2f ", categories[i], amounts[i]);
            
            // Create a simple bar
            int barLength = (int)(amounts[i] / 10);
            for (int j = 0; j < barLength; j++) {
                System.out.print("■");
            }
            System.out.println();
        }
    }
    
    public void visualizeExpenses(User user, String period) {
        System.out.println("\n--- Expense Visualization (" + period + ") ---");
        // Period-specific visualization logic would go here
        visualizeExpenses(user);
    }
    
    // Condition III: Vararg overloading
    public void compareBudgets(Budget... budgets) {
        System.out.println("\n--- Budget Comparison ---");
        
        if (budgets.length == 0) {
            System.out.println("No budgets to compare.");
            return;
        }
        
        for (Budget budget : budgets) {
            System.out.printf("%-15s: $%-10.2f (%s)\n", 
                budget.getName(), budget.getAmount(), budget.getPeriod());
        }
    }
    
    // Simple text-based pie chart
    public void displayIncomePieChart(User user) {
        System.out.println("\n--- Income Distribution ---");
        
        Transaction[] transactions = user.getTransactions();
        int count = user.getTransactionCount();
        
        // Count income by category
        String[] categories = new String[20];
        double[] amounts = new double[20];
        int categoryCount = 0;
        double total = 0.0;
        
        for (int i = 0; i < count; i++) {
            if (transactions[i] instanceof Income) {
                String category = transactions[i].getCategory();
                boolean found = false;
                
                // Check if category already exists
                for (int j = 0; j < categoryCount; j++) {
                    if (categories[j].equals(category)) {
                        amounts[j] += transactions[i].getAmount();
                        found = true;
                        break;
                    }
                }
                
                // Add new category if not found
                if (!found && categoryCount < categories.length) {
                    categories[categoryCount] = category;
                    amounts[categoryCount] = transactions[i].getAmount();
                    categoryCount++;
                }
                
                total += transactions[i].getAmount();
            }
        }
        
        // Display pie chart
        for (int i = 0; i < categoryCount; i++) {
            double percentage = (amounts[i] / total) * 100;
            System.out.printf("%-15s: $%-10.2f (%.1f%%)\n", 
                categories[i], amounts[i], percentage);
        }
    }
}
