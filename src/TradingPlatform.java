// Stock Trading Platform Project --TradingPlatform.java
//This is part of stock platform project
// Author: Rimjhim

package src;

import java.util.*;

public class TradingPlatform {
    private Map<String, Stock> stockMarket;
    private User currentUser;

    public TradingPlatform() {
        stockMarket = new HashMap<>();
        loadStocks();
        currentUser = new User("demo_user", 10000.0);
    }

    private void loadStocks() {
        stockMarket.put("AAPL", new Stock("AAPL", "Apple Inc.", 150.00));
        stockMarket.put("GOOGL", new Stock("GOOGL", "Alphabet Inc.", 2800.00));
        stockMarket.put("TSLA", new Stock("TSLA", "Tesla Inc.", 700.00));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String choice;
        do {
            System.out.println("\n--- Stock Trading Menu ---");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewMarketData();
                    break;
                case "2":
                    buyStock(scanner);
                    break;
                case "3":
                    sellStock(scanner);
                    break;
                case "4":
                    currentUser.displayPortfolio(stockMarket);
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (!choice.equals("0"));
    }

    private void viewMarketData() {
        System.out.println("--- Market Data ---");
        for (Stock stock : stockMarket.values()) {
            System.out.println(stock);
        }
    }

    private void buyStock(Scanner scanner) {
        System.out.print("Enter stock symbol: ");
        String symbol = scanner.nextLine().toUpperCase();
        Stock stock = stockMarket.get(symbol);
        if (stock != null) {
            System.out.print("Enter quantity to buy: ");
            int qty = Integer.parseInt(scanner.nextLine());
            currentUser.buyStock(stock, qty);
        } else {
            System.out.println("Stock not found.");
        }
    }

    private void sellStock(Scanner scanner) {
        System.out.print("Enter stock symbol: ");
        String symbol = scanner.nextLine().toUpperCase();
        Stock stock = stockMarket.get(symbol);
        if (stock != null) {
            System.out.print("Enter quantity to sell: ");
            int qty = Integer.parseInt(scanner.nextLine());
            currentUser.sellStock(stock, qty);
        } else {
            System.out.println("Stock not found.");
        }
    }
}