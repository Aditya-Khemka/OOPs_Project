# Personal Finance Management System
## CSF231 - Object Oriented Programming Project

## Project Overview
A console-based Personal Finance Management System developed in Java that helps users track their income, expenses, and budgets efficiently. The system provides comprehensive financial tracking capabilities with features for managing multiple income sources, categorizing expenses, setting budgets, and generating reports.

## Academic Requirements Implementation

| Requirement | Implementation | Count | File/Location |
|------------|---------------|-------|---------------|
| I. Overloaded Methods | `User.addTransaction()`, `FinancialManager.addTransaction()`, `Budget.setAmount()` | 3 | User.java, FinancialManager.java, Budget.java |
| II. Overloaded Constructors | `User()`, `Budget()`, `Transaction()` | 3 | User.java, Budget.java, Transaction.java |
| III. Vararg Overloading | `User.addTransaction(Transaction...)`, `FinancialManager.addTransaction(Transaction...)` | 2 | User.java, FinancialManager.java |
| IV. Nested Classes | `Budget.BudgetCalculator`, `User.UserPreferences` | 2 | Budget.java, User.java |
| V. Abstract Class | `Transaction` | 1 | Transaction.java |
| VI. Interface | `Authentication`, `Exportable` | 2 | Authentication.java, Exportable.java |
| VII. Hierarchical Inheritance | `Transaction` → `Income`/`Expense` | 1 | Transaction.java, Income.java, Expense.java |
| VIII. Multiple Inheritance | `User implements Authentication`, `FinancialManager implements Exportable` | 2 | User.java, FinancialManager.java |
| IX. Wrappers | `Double`, `Integer` | 2 | Various files |
| X. Package | `com.finance` | 1 | All files |
| XI. Exception Handling | `InvalidAmountException`, `BudgetExceededException` | 2 | exceptions/ |
| XII. I/O Operations | File handling, Scanner class | 2 | FinancialManager.java, Main.java |
| XIII. Multithreading | `Notification implements Runnable` | 1 | Notification.java |

## Core Features Implementation

### 1. Overloaded Methods
```java
// In User.java
public class User {
    // Method overloading for adding transactions
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        updateBalance();
    }
    
    public void addTransaction(Transaction... transactions) {
        for (Transaction t : transactions) {
            this.transactions.add(t);
        }
        updateBalance();
    }
    
    // Method overloading for setting preferences
    public void setPreferences(String currency) {
        preferences.setCurrency(currency);
    }
    
    public void setPreferences(String currency, String dateFormat) {
        preferences.setCurrency(currency);
        preferences.setDateFormat(dateFormat);
    }
}
```

### 2. Overloaded Constructors
```java
// In Budget.java
public class Budget {
    private String name;
    private Double amount;
    private String period;
    
    // Constructor overloading
    public Budget() {
        this("Default Budget", 0.0, "Monthly");
    }
    
    public Budget(String name) {
        this(name, 0.0, "Monthly");
    }
    
    public Budget(String name, double amount) {
        this(name, amount, "Monthly");
    }
    
    public Budget(String name, double amount, String period) {
        this.name = name;
        this.amount = amount;
        this.period = period;
    }
}
```

### 3. Vararg Overloading
```java
// In FinancialManager.java
public class FinancialManager {
    // Vararg method for adding multiple transactions
    public void addTransactions(Transaction... transactions) {
        for (Transaction t : transactions) {
            this.transactions.add(t);
        }
    }
    
    // Vararg method for generating reports
    public void generateReports(String... reportTypes) {
        for (String type : reportTypes) {
            generateReport(type);
        }
    }
}
```

### 4. Nested Classes
```java
// In User.java
public class User {
    // Nested class for user preferences
    public class UserPreferences {
        private String currency;
        private String dateFormat;
        private boolean notificationsEnabled;
        
        public UserPreferences() {
            this.currency = "USD";
            this.dateFormat = "MM/dd/yyyy";
            this.notificationsEnabled = true;
        }
    }
    
    // Nested interface for authentication
    public interface Authentication {
        boolean authenticate(String username, String password);
    }
}
```

### 5. Abstract Class
```java
// In Transaction.java
public abstract class Transaction {
    protected Double amount;
    protected String category;
    protected Date date;
    
    public Transaction(double amount, String category) {
        this.amount = amount;
        this.category = category;
        this.date = new Date();
    }
    
    public abstract String getType();
    public abstract void process();
}
```

### 6. Interface
```java
// In Exportable.java
public interface Exportable {
    void exportToFile(String filename) throws IOException;
    String generateExportData();
}

// In Authentication.java
public interface Authentication {
    boolean authenticate(String username, String password);
    void logout();
}
```

### 7. Hierarchical Inheritance
```java
// In Transaction.java (Abstract class)
public abstract class Transaction {
    protected Double amount;
    protected String category;
    protected Date date;
}

// In Income.java
public class Income extends Transaction {
    private String source;
    
    public Income(double amount, String category, String source) {
        super(amount, category);
        this.source = source;
    }
    
    @Override
    public String getType() {
        return "Income";
    }
}

// In Expense.java
public class Expense extends Transaction {
    private String paymentMethod;
    
    public Expense(double amount, String category, String paymentMethod) {
        super(amount, category);
        this.paymentMethod = paymentMethod;
    }
    
    @Override
    public String getType() {
        return "Expense";
    }
}
```

### 8. Multiple Inheritance
```java
// In User.java
public class User implements Authentication, Exportable {
    // Implementation of Authentication interface
    @Override
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    
    // Implementation of Exportable interface
    @Override
    public void exportToFile(String filename) throws IOException {
        // Implementation
    }
}
```

### 9. Wrappers
```java
// In Budget.java
public class Budget {
    private Double amount;  // Wrapper class
    private Integer periodDays;  // Wrapper class
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public void setPeriodDays(Integer days) {
        this.periodDays = days;
    }
}
```

### 10. Package
```java
// All files are in the com.finance package
package com.finance;

// Package structure
// src/com/finance/
// ├── model/
// ├── service/
// ├── interfaces/
// ├── exceptions/
// └── util/
```

### 11. Exception Handling
```java
// In exceptions/InvalidAmountException.java
public class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

// In exceptions/BudgetExceededException.java
public class BudgetExceededException extends Exception {
    public BudgetExceededException(String message) {
        super(message);
    }
}

// Usage in FinancialManager.java
public void addTransaction(Transaction transaction) throws InvalidAmountException {
    if (transaction.getAmount() <= 0) {
        throw new InvalidAmountException("Amount must be positive");
    }
    // Process transaction
}
```

### 12. I/O Operations
```java
// In FinancialManager.java
public class FinancialManager {
    // File handling
    public void saveToFile(String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(generateExportData());
        }
    }
    
    // Scanner usage
    public void readUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        // Process input
    }
}
```

### 13. Multithreading
```java
// In Notification.java
public class Notification implements Runnable {
    private User user;
    private boolean running;
    
    public Notification(User user) {
        this.user = user;
        this.running = true;
    }
    
    @Override
    public void run() {
        while (running) {
            try {
                checkBudgetAlerts();
                Thread.sleep(60000); // Check every minute
            } catch (InterruptedException e) {
                System.out.println("Notification thread interrupted");
                running = false;
            }
        }
    }
    
    private void checkBudgetAlerts() {
        // Implementation for budget alerts
    }
}

// Usage in Main.java
public class Main {
    public static void main(String[] args) {
        User user = new User();
        Thread notificationThread = new Thread(new Notification(user));
        notificationThread.start();
    }
}
```