package com.Market;

import com.Market.Cashing.CashRegister;
import com.Market.Cashing.Cashier;
import com.Market.Market.Market;
import com.Market.Stock.Category;
import com.Market.Stock.Stock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//TO DO: Introduce Engine for User Interaction
public class Main {
    public static void main(String[] args) {
        try {

            Market market = new Market(12, 5, 1);

            deliverStocks(market);
            hireEmployees(market);

            addCashRegister(market, market.getCashiers().get(0));
            addCashRegister(market, market.getCashiers().get(1));


            List<Stock> requestedStocks = new ArrayList<Stock>();
            requestedStocks.add(new Stock("1233-312-3123", "Banana", BigDecimal.valueOf(1.25), 1, BigDecimal.valueOf(2.25), Category.Edible, LocalDate.now()));
            requestedStocks.add(new Stock("1233-312-3123", "Screwdriver", BigDecimal.valueOf(1.25), 1, BigDecimal.valueOf(2.25), Category.Edible, LocalDate.now()));

            CashRegister cashRegister = market.getCashRegisters().get(0);
            cashRegister.sellStock(BigDecimal.valueOf(12.52),requestedStocks);

            System.out.println("Summmary");
            market.getSummary();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void deliverStocks(Market market) {
        market.deliverStock(new Stock("1233-312-3123", "Banana", BigDecimal.valueOf(1.25), 2, BigDecimal.valueOf(2.25), Category.Edible, LocalDate.now()));
        market.deliverStock(new Stock("1233-312-3123", "Screwdriver", BigDecimal.valueOf(1.25), 1, BigDecimal.valueOf(2.25), Category.NonEdible, LocalDate.now()));
    }

    public static void hireEmployees(Market market) {
        market.hireCashier(new Cashier("12_1234", "John Johnson", BigDecimal.valueOf(1421.512)));
        market.hireCashier(new Cashier("12_1334", "Samuel Cranston", BigDecimal.valueOf(1321.512)));
    }

    public static void addCashRegister(Market market, Cashier cashier) {
        market.addCashRegister(cashier);
    }
}
