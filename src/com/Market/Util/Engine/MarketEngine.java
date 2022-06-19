package com.Market.Util.Engine;

import com.Market.Cashing.CashRegister;
import com.Market.Cashing.Cashier;
import com.Market.Market.Market;
import com.Market.Stock.Category;
import com.Market.Stock.Stock;
import com.Market.Util.Messages;
import com.Market.Util.Repository;
import com.Market.Util.Validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MarketEngine extends Engine {

    @Override
    public void run() throws InterruptedException {
        System.out.println("Greetings, welcome to our new Market !");
        Thread.sleep(1000);
        Scanner scanner = new Scanner(System.in);
        String answer;

        do {
            System.out.println("Would you like to start the application ? Y/N");
            answer = scanner.nextLine().toUpperCase(Locale.ROOT);
        } while (!answer.equals("Y") && !answer.equals("N"));

        if (answer.equals("Y")) {
            start();
        } else {
            System.out.println("Goodbye !");
        }
    }

    private static void start() {
        Market market = new Market(12, 5, 1);

        try {
            MarketLoader.writeMessage("Delivering products");
            deliverStocks(market);

            MarketLoader.writeMessage("Hiring cashiers");
            hireEmployees(market);

            MarketLoader.writeMessage("Adding cash registers");
            addCashRegisters(market);

            sellStock(market.getCashRegisters().get(0));

            MarketLoader.writeMessage("Summary");
            market.getSummary();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void sellStock(CashRegister cashRegister) {
        try {
            List<Stock> stocks = new ArrayList<Stock>();
            Scanner scanner = new Scanner(System.in);
            String answer;
            do {
                String productName;
                int quantity;
                MarketLoader.writeMessage("Choosing products");
                System.out.println("Enter product name:");
                productName = scanner.nextLine();
                System.out.println("Enter product quantity:");
                quantity = Integer.parseInt(scanner.nextLine());

                Stock stock = new Stock("1111-111-1111", productName, BigDecimal.ZERO, quantity, BigDecimal.ZERO, Category.NonEdible, LocalDate.now());
                stocks.add(stock);
                System.out.println("Do you wish to purchase more stocks ? Y/N");
                answer = scanner.nextLine().toUpperCase(Locale.ROOT);

            } while (!answer.equals("N"));

            if (stocks.size() > 0) {
                BigDecimal payment;
                System.out.println("Enter payment:");
                payment = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));

                cashRegister.sellStock(payment, stocks);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deliverStocks(Market market) throws InterruptedException {
        for (Stock stock : Repository.stocks) {
            market.deliverStock(stock);
            Thread.sleep(1000);
            System.out.println(String.format(Messages.deliveredProductMessage, stock.getName(), stock.getQuantity()));
        }
    }

    private static void hireEmployees(Market market) throws InterruptedException {
        for (Cashier cashier : Repository.cashiers) {
            market.hireCashier(cashier);
            Thread.sleep(1000);
            System.out.println(String.format(Messages.hiredCashierMessage, cashier.getName(), cashier.getSalary()));
        }
    }

    private static void addCashRegisters(Market market) throws InterruptedException {
        for (Cashier cashier : market.getCashiers()) {
            market.addCashRegister(cashier);
            Thread.sleep(1000);
            System.out.println(String.format(Messages.createdCashRegister, cashier.getName()));
        }
    }


}
