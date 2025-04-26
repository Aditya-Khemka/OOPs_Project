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

### 1. Transaction Management
```java
// In Transaction.java (Abstract Class)
public abstract class Transaction {
    protected Double amount;  // Wrapper class usage
    protected String category;
    protected Date date;
    
    // Overloaded constructors
    public Transaction() {
        this(0.0, "Uncategorized");
    }
    
    public Transaction(double amount) {
        this(amount, "Uncategorized");
    }
    
    public Transaction(double amount, String category) {
        this.amount = amount;
        this.category = category;
        this.date = new Date();
    }
    
    public abstract String getType();
}
```

### 2. Budget Management with Nested Class
```java
// In Budget.java
public class Budget {
    private String name;
    private Double amount;
    private String period;  // Monthly, Weekly, Daily
    
    // Nested static class for budget calculations
    public static class BudgetCalculator {
        public static double calculatePeriodAmount(Budget budget, String targetPeriod) {
            double amount = budget.getAmount();
            String currentPeriod = budget.getPeriod();
            
            // Convert to daily amount first
            double dailyAmount = switch (currentPeriod.toLowerCase()) {
                case "monthly" -> amount / 30.0;
                case "weekly" -> amount / 7.0;
                case "daily" -> amount;
                default -> amount;
            };
            
            // Convert to target period
            return switch (targetPeriod.toLowerCase()) {
                case "monthly" -> dailyAmount * 30;
                case "weekly" -> dailyAmount * 7;
                case "daily" -> dailyAmount;
                default -> amount;
            };
        }
    }
}
```

### 3. User Authentication with Multiple Inheritance
```java
// In User.java
public class User implements Authentication {
    private String name;
    private String username;
    private String password;
    private Double balance;
    private boolean authenticated;
    
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
    
    @Override
    public boolean authenticate(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            authenticated = true;
            return true;
        }
        return false;
    }
}
```

### 4. Exception Handling
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
```

### 5. Multithreading Implementation
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
```

### 6. File Operations and Data Export
```java
// In FinancialManager.java
public class FinancialManager implements Exportable {
    public void exportToFile(String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(generateExportData());
        }
    }
    
    private String generateExportData() {
        StringBuilder sb = new StringBuilder();
        // Implementation for data export
        return sb.toString();
    }
}
```

## Project Structure
```
src/com/finance/
├── model/
│   ├── Transaction.java (Abstract class)
│   ├── Income.java
│   ├── Expense.java
│   ├── User.java
│   └── Budget.java
├── service/
│   ├── FinancialManager.java
│   ├── ReportGenerator.java
│   └── Notification.java
├── interfaces/
│   ├── Authentication.java
│   └── Exportable.java
├── exceptions/
│   ├── InvalidAmountException.java
│   └── BudgetExceededException.java
├── util/
│   └── DataVisualizer.java
└── Main.java
```

## Usage Instructions

1. **Compilation**
   ```bash
   javac -d . src/com/finance/*.java src/com/finance/**/*.java
   ```

2. **Execution**
   ```bash
   java com.finance.Main
   ```

## Development Guidelines
- Follow Java coding conventions
- Maintain proper documentation
- Use meaningful variable names
- Implement proper error handling
- Follow OOP principles
- Ensure code reusability

## Dependencies
- Java SE 8 or higher
- Standard Java libraries

## License
This project is for educational purposes only.