package com.finance.service;

import com.finance.model.User;
import com.finance.model.Transaction;
import com.finance.model.Expense;
import com.finance.model.Budget;

/**
 * Manages notifications for financial events
 * Condition XIII: Multithreading (implements Runnable)
 */
public class Notification implements Runnable {
    private User user;
    
    public Notification(User user) {
        this.user = user;
    }
    
    // Condition I: Overloaded method
    public void sendNotification(String message) {
        System.out.println("\n[NOTIFICATION] " + message);
    }
    
    public void sendNotification(String message, String priority) {
        System.out.println("\n[NOTIFICATION - " + priority + "] " + message);
    }
    
    public void checkBudgetStatus() {
        Budget[] budgets = user.getBudgets();
        int count = user.getBudgetCount();
        
        for (int i = 0; i < count; i++) {
            Budget budget = budgets[i];
            
            // Calculate total expenses for this budget category
            Transaction[] transactions = user.getTransactions();
            int transactionCount = user.getTransactionCount();
            double totalExpense = 0.0;
            
            for (int j = 0; j < transactionCount; j++) {
                if (transactions[j] instanceof Expense && 
                    transactions[j].getCategory().equals(budget.getName())) {
                    totalExpense += transactions[j].getAmount();
                }
            }
            
            // Check if approaching budget limit
            double budgetAmount = budget.getAmount();
            if (totalExpense > budgetAmount * 0.8 && totalExpense < budgetAmount) {
                sendNotification("You're approaching your budget limit for " + budget.getName(), "WARNING");
            } else if (totalExpense >= budgetAmount) {
                sendNotification("You've exceeded your budget for " + budget.getName(), "ALERT");
            }
        }
    }
    
    @Override
    public void run() {
        // Condition XIII: Multithreading
        while (true) {
            try {
                // Check budget status every 10 seconds
                checkBudgetStatus();
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Notification thread interrupted: " + e.getMessage());
                break;
            }
        }
    }
}
