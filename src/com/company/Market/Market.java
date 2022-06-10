package com.company.Market;

import com.company.Cashing.CashRegister;
import com.company.Cashing.Cashier;
import com.company.Contracts.Marketable;
import com.company.Stock.Stock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Market implements Marketable {
    private List<Stock> deliveredStocks;
    private List<Stock> soldStocks;
    private List<CashRegister> cashRegisters;
    private List<Cashier> cashiers;

    int markupPercentage;
    int decreasePercentage;
    int daysToExpire;
    int totalReceiptsCount;

    public Market() {
        this.deliveredStocks = new ArrayList<Stock>();
        this.soldStocks = new ArrayList<Stock>();
        this.cashRegisters = new ArrayList<CashRegister>();
        this.cashiers = new ArrayList<Cashier>();
    }

    public Market(int markupPercentage, int decreasePercentage, int daysToExpire) {
        this.markupPercentage = markupPercentage;
        this.decreasePercentage = decreasePercentage;
        this.daysToExpire = daysToExpire;

        this.deliveredStocks = new ArrayList<Stock>();
        this.soldStocks = new ArrayList<Stock>();
        this.cashRegisters = new ArrayList<CashRegister>();
        this.cashiers = new ArrayList<Cashier>();
    }

    @Override
    public void hireCashier(Cashier cashier) {
        this.cashiers.add(cashier);
    }

    @Override
    public void deliverStock(Stock stock) {
        int daysLeft = stock.getExpireDate().getDayOfMonth();

        if (!isExpired(daysLeft)) {
            evaluateStockPrice(stock);
            this.deliveredStocks.add(stock);
        }
    }

    @Override
    public void getSummary() {
        BigDecimal totalCosts = this.getSalariesCost().add(this.getDeliveryStocksCost());
        BigDecimal totalProfit = this.getSoldStocksProfit();
        System.out.println("Total Profit of the Market is: " + totalProfit.subtract(totalCosts));
    }

    @Override
    public void evaluateStockPrice(Stock stock) {
        BigDecimal price = stock.getSellPrice();
        double percentage = (double) this.decreasePercentage / 100;
        BigDecimal decreasePrice = price.multiply(BigDecimal.valueOf(percentage));

        stock.setSellPrice(price.subtract(decreasePrice));
    }

    @Override
    public void getTotalReceipts() {
        for (CashRegister cashRegister : this.cashRegisters) {
            this.totalReceiptsCount += cashRegister.getReceiptsCount();
        }
    }

    @Override
    public void addCashRegister(Cashier cashier) {
        CashRegister cashRegister = new CashRegister(this.deliveredStocks, this.soldStocks, cashier);
    }

    private boolean isExpired(int days) {
        return days - this.daysToExpire > 0 ? true : false;
    }

    private BigDecimal getSalariesCost() {
        BigDecimal totalCosts = BigDecimal.ZERO;
        for (Cashier cashier : this.cashiers) {
            totalCosts.add(cashier.getSalary());
        }
        return totalCosts;
    }

    private BigDecimal getDeliveryStocksCost() {
        BigDecimal totalCosts = BigDecimal.ZERO;
        for (Stock stock : this.deliveredStocks) {
            totalCosts.add(stock.getDeliveryPrice().multiply(BigDecimal.valueOf(stock.getQuantity())));
        }
        return totalCosts;
    }

    private BigDecimal getSoldStocksProfit() {
        BigDecimal totalProfit = BigDecimal.ZERO;
        for (Stock stock : this.soldStocks) {
            totalProfit.add(stock.getSellPrice().multiply(BigDecimal.valueOf(stock.getQuantity())));
        }
        return totalProfit;
    }
}
