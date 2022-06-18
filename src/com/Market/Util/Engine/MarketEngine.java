package com.Market.Util.Engine;

import com.Market.Cashing.Cashier;
import com.Market.Market.Market;
import com.Market.Stock.Stock;
import com.Market.Util.Messages;
import com.Market.Util.Repository;

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
            answer = scanner.nextLine();
        } while (!answer.toUpperCase(Locale.ROOT).equals("Y") && !answer.toUpperCase(Locale.ROOT).equals("N"));

        if (answer.toUpperCase(Locale.ROOT).equals("Y")) {
            start();
        } else {
            System.out.println("Goodbye !");
        }
    }

    private static void start() {
        Market market = new Market(12, 5, 1);

        try {
            MarketLoader.loadingDeliveredProducts();
            deliverStocks(market);

            MarketLoader.loadingHiredCashiers();
            hireEmployees(market);

            MarketLoader.loadingCashRegisters();
            addCashRegisters(market);

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
            System.out.println(String.format(Messages.createdCashRegister,cashier.getName()));
        }
    }

}
