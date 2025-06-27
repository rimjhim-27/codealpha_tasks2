// Stock Trading Platform Project --User.java
//This is part of stock platform project
// Author: Rimjhim



package src;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private double balance;
    private Map<String, Integer> portfolio;

    public User(String username, double balance) {
        this.username = username;
        this.balance = balance;
        this.portfolio = new HashMap<>();
    }

    public String getUsername() { return username; }
    public double getBalance() { return balance; }
    public Map<String, Integer> getPortfolio() { return portfolio; }

    public void buyStock(Stock stock, int quantity) {
        double cost = stock.getPrice() * quantity;
        if (cost <= balance) {
            balance -= cost;
            portfolio.put(stock.getSymbol(), portfolio.getOrDefault(stock.getSymbol(), 0) + quantity);
            System.out.println("Bought " + quantity + " shares of " + stock.getSymbol());
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void sellStock(Stock stock, int quantity) {
        int owned = portfolio.getOrDefault(stock.getSymbol(), 0);
        if (quantity <= owned) {
            balance += stock.getPrice() * quantity;
            portfolio.put(stock.getSymbol(), owned - quantity);
            System.out.println("Sold " + quantity + " shares of " + stock.getSymbol());
        } else {
            System.out.println("Not enough shares to sell.");
        }
    }

    public void displayPortfolio(Map<String, Stock> stockMap) {
        System.out.println("Portfolio of " + username + ":");
        for (String symbol : portfolio.keySet()) {
            int qty = portfolio.get(symbol);
            double value = qty * stockMap.get(symbol).getPrice();
            System.out.println(symbol + ": " + qty + " shares, Value: $" + value);
        }
        System.out.println("Remaining Balance: $" + balance);
    }
}