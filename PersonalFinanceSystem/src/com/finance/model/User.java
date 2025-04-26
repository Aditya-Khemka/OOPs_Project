package com.finance.model;

import com.finance.interfaces.Authentication;

/**
 * Represents a user of the finance system
 * Condition VIII: Multiple Inheritance (implements Authentication)
 */
public class User implements Authentication {
    private String name;
    private String username;
    private String password;
    private Double balance; // Condition IX: Wrapper
    private Transaction[] transactions; // Using array instead of List as specified
    private int transactionCount;
    private Budget[] budgets;
    private int budgetCount;
    private boolean authenticated;
    
    // Condition II: Overloaded constructors
    public User(String name) {
        this.name = name;
        this.balance = 0.0;
        this.transactions = new Transaction[100]; // Fixed size array
        this.transactionCount = 0;
        this.budgets = new Budget[10];
        this.budgetCount = 0;
        this.authenticated = false;
    }
    
    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.transactions = new Transaction[100]; // Fixed size array
        this.transactionCount = 0;
        this.budgets = new Budget[10];
        this.budgetCount = 0;
        this.authenticated = false;
    }
    
    // Condition I: Overloaded method
    public void addTransaction(Transaction transaction) {
        if (transactionCount < transactions.length) {
            transactions[transactionCount++] = transaction;
            
            // Update balance
            if (transaction instanceof Income) {
                balance += transaction.getAmount();
            } else if (transaction instanceof Expense) {
                balance -= transaction.getAmount();
            }
        }
    }
    
    // Condition III: Vararg overloading
    public void addTransaction(Transaction... transactions) {
        for (Transaction transaction : transactions) {
            addTransaction(transaction);
        }
    }
    
    public void addBudget(Budget budget) {
        if (budgetCount < budgets.length) {
            budgets[budgetCount++] = budget;
        }
    }
    
    // Getters and setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Double getBalance() {
        return balance;
    }
    
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
    public Transaction[] getTransactions() {
        return transactions;
    }
    
    public int getTransactionCount() {
        return transactionCount;
    }
    
    public Budget[] getBudgets() {
        return budgets;
    }
    
    public int getBudgetCount() {
        return budgetCount;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    // Implementation of Authentication interface methods
    @Override
    public boolean authenticate(String username, String password) {
        if (this.username != null && this.username.equals(username) && 
            this.password != null && this.password.equals(password)) {
            authenticated = true;
            return true;
        }
        return false;
    }
    
    @Override
    public void logout() {
        authenticated = false;
    }
    
    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }
    
    @Override
    public String toString() {
        return "User: " + name + " - Balance: $" + balance;
    }
}
