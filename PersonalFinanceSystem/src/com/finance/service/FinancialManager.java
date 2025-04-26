package com.finance.service;

import com.finance.model.*;
import com.finance.exceptions.InvalidAmountException;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Manages financial operations for a user
 */
public class FinancialManager {
    private User user;
    private Budget currentBudget;
    
    public FinancialManager(User user) {
        this.user = user;
    }
    
    // Condition I: Overloaded method
    public void addTransaction(Transaction transaction) {
        user.addTransaction(transaction);
    }
    
    // Condition III: Vararg overloading
    public void addTransaction(Transaction... transactions) {
        user.addTransaction(transactions);
    }
    
    public void setBudget(Budget budget) {
        this.currentBudget = budget;
        user.addBudget(budget);
    }
    
    public void displayTransactions() {
        System.out.println("\n--- Transactions ---");
        
        Transaction[] transactions = user.getTransactions();
        int count = user.getTransactionCount();
        
        if (count == 0) {
            System.out.println("No transactions found.");
            return;
        }
        
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + transactions[i]);
        }
    }
    
    // Calculate total income
    public double calculateTotalIncome() {
        Transaction[] transactions = user.getTransactions();
        int count = user.getTransactionCount();
        double total = 0.0;
        
        for (int i = 0; i < count; i++) {
            if (transactions[i] instanceof Income) {
                total += transactions[i].getAmount();
            }
        }
        
        return total;
    }
    
    // Calculate total expense
    public double calculateTotalExpense() {
        Transaction[] transactions = user.getTransactions();
        int count = user.getTransactionCount();
        double total = 0.0;
        
        for (int i = 0; i < count; i++) {
            if (transactions[i] instanceof Expense) {
                total += transactions[i].getAmount();
            }
        }
        
        return total;
    }
    
    // Condition XII: File Handling
    public void saveData(String filename) {
        // Get the user's Desktop path
        String desktopPath = System.getProperty("user.home") + java.io.File.separator + "Desktop";
        String filePath = desktopPath + java.io.File.separator + filename;
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("User: " + user.getName() + "\n");
            writer.write("Balance: $" + user.getBalance() + "\n\n");
            
            writer.write("--- Transactions ---\n");
            Transaction[] transactions = user.getTransactions();
            int count = user.getTransactionCount();
            
            for (int i = 0; i < count; i++) {
                writer.write((i + 1) + ". " + transactions[i] + "\n");
            }
            
            writer.write("\nTotal Income: $" + calculateTotalIncome() + "\n");
            writer.write("Total Expense: $" + calculateTotalExpense() + "\n");

            System.out.println("File saved to: " + filePath); // Show user where the file is saved
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    
    // Check if an expense exceeds budget
    public boolean isExpenseWithinBudget(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Expense amount must be positive!");
        }
        
        if (currentBudget == null) {
            return true; // No budget set
        }
        
        return amount <= currentBudget.getAmount();
    }
}
