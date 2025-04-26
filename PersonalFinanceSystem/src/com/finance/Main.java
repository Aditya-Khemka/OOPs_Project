package com.finance;

import com.finance.model.*;
import com.finance.service.*;
import com.finance.util.DataVisualizer;
import com.finance.exceptions.*;
import java.util.Scanner;


/**
 * Main entry point for the Personal Finance Management System
 */
public class Main {
    public static void main(String[] args) {
        // Condition XII: Scanner class usage
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Personal Finance Management System!");
        
        // Get user details
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        
        // Create a new user
        User user = new User(name, initialBalance);
        
        // Create financial manager
        FinancialManager manager = new FinancialManager(user);
        
        // Condition XIII: Multithreading
        Thread notificationThread = new Thread(new Notification(user));
        notificationThread.start();
        
        boolean running = true;
        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. Create Budget");
            System.out.println("4. View Transactions");
            System.out.println("5. Generate Report");
            System.out.println("6. Visualize Data");
            System.out.println("7. Save Data");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter income amount: ");
                    double incomeAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    
                    System.out.print("Enter category: ");
                    String incomeCategory = scanner.nextLine();
                    
                    System.out.print("Enter source: ");
                    String incomeSource = scanner.nextLine();
                    
                    try {
                        // Condition XI: Exception handling
                        if (incomeAmount <= 0) {
                            throw new InvalidAmountException("Income amount must be positive!");
                        }
                        
                        Income income = new Income(incomeAmount, incomeCategory, incomeSource);
                        manager.addTransaction(income);
                        System.out.println("Income added successfully!");
                    } catch (InvalidAmountException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                    
                case 2:
                    System.out.print("Enter expense amount: ");
                    double expenseAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    
                    System.out.print("Enter category: ");
                    String expenseCategory = scanner.nextLine();
                    
                    try {
                        // Condition XI: Exception handling
                        if (expenseAmount <= 0) {
                            throw new InvalidAmountException("Expense amount must be positive!");
                        }
                        
                        Expense expense = new Expense(expenseAmount, expenseCategory);
                        manager.addTransaction(expense);
                        System.out.println("Expense added successfully!");
                    } catch (InvalidAmountException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                    
                case 3:
                    System.out.print("Enter budget name: ");
                    String budgetName = scanner.nextLine();
                    
                    System.out.print("Enter budget amount: ");
                    double budgetAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    
                    System.out.print("Enter budget period (Monthly/Weekly/Daily): ");
                    String budgetPeriod = scanner.nextLine();
                    
                    Budget budget = new Budget(budgetName, budgetAmount, budgetPeriod);
                    manager.setBudget(budget);
                    System.out.println("Budget created successfully!");
                    break;
                    
                case 4:
                    manager.displayTransactions();
                    break;
                    
                case 5:
                    ReportGenerator reportGen = new ReportGenerator();
                    reportGen.generateReport(user);
                    break;
                    
                case 6:
                    // Create data visualizer to show charts
                    DataVisualizer visualizer = new DataVisualizer();
                    visualizer.visualizeExpenses(user);
                    visualizer.displayIncomePieChart(user);
                    break;
                    
                case 7:
                    // Condition XII: File handling
                    manager.saveData("finance_data.txt");
                    System.out.println("Data saved successfully!");
                    break;
                    
                case 8:
                    running = false;
                    System.out.println("Thank you for using Personal Finance Management System!");
                    break;
                    
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        
        scanner.close();
    }
}
