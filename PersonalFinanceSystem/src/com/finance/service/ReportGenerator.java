package com.finance.service;

import com.finance.model.*;
import com.finance.interfaces.Exportable;

/**
 * Generates financial reports
 * Condition VIII: Multiple Inheritance (implements Exportable)
 */
public class ReportGenerator implements Exportable {
    
    // Condition I: Overloaded method
    public void generateReport(User user) {
        System.out.println("\n--- Financial Report ---");
        System.out.println("User: " + user.getName());
        System.out.println("Current Balance: $" + user.getBalance());
        
        // Calculate totals
        double totalIncome = 0.0;
        double totalExpense = 0.0;
        
        Transaction[] transactions = user.getTransactions();
        int count = user.getTransactionCount();
        
        for (int i = 0; i < count; i++) {
            if (transactions[i] instanceof Income) {
                totalIncome += transactions[i].getAmount();
            } else if (transactions[i] instanceof Expense) {
                totalExpense += transactions[i].getAmount();
            }
        }
        
        System.out.println("Total Income: $" + totalIncome);
        System.out.println("Total Expense: $" + totalExpense);
        System.out.println("Net Savings: $" + (totalIncome - totalExpense));
    }
    
    public void generateReport(User user, String period) {
        System.out.println("\n--- Financial Report (" + period + ") ---");
        // Additional period-specific report logic would go here
        generateReport(user);
    }
    
    // Implementation of Exportable interface methods
    public boolean exportToPDF(String filename) {
        System.out.println("Exporting report to PDF: " + filename);
        // PDF export logic would go here
        return true;
    }
    
    
    public boolean exportToExcel(String filename) {
        System.out.println("Exporting report to Excel: " + filename);
        // Excel export logic would go here
        return true;
    }
    
    // Condition III: Vararg overloading
    public void printCategorySummary(String... categories) {
        System.out.println("\n--- Category Summary ---");
        for (String category : categories) {
            System.out.println("Category: " + category);
            // Summary calculation logic would go here
        }
    }
}
