package com.Market.Market;

import com.Market.Cashing.CashRegister;
import com.Market.Cashing.Cashier;
import com.Market.Contracts.Marketable;
import com.Market.Stock.Stock;
import com.Market.Util.Validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Market implements Marketable {
    //Fields

    private List<Stock> deliveredStocks;
    private List<Stock> soldStocks;
    private List<CashRegister> cashRegisters;
    private List<Cashier> cashiers;

    int markupPercentage;
    int decreasePercentage;
    int daysToExpire;
    int totalReceiptsCount;

    //Constructors

    public Market() {
        this.deliveredStocks = new ArrayList<Stock>();
        this.soldStocks = new ArrayList<Stock>();
        this.cashRegisters = new ArrayList<CashRegister>();
        this.cashiers = new ArrayList<Cashier>();
    }

    public Market(int markupPercentage, int decreasePercentage, int daysToExpire) {
        Validator.isNegativeInteger(markupPercentage);
        this.markupPercentage = markupPercentage;

        Validator.isNegativeInteger(decreasePercentage);
        this.decreasePercentage = decreasePercentage;

        Validator.isNegativeInteger(daysToExpire);
        this.daysToExpire = daysToExpire;

        this.deliveredStocks = new ArrayList<Stock>();
        this.soldStocks = new ArrayList<Stock>();
        this.cashRegisters = new ArrayList<CashRegister>();
        this.cashiers = new ArrayList<Cashier>();
    }
    //Getters and Setters

    public int getMarkupPercentage() {
        return markupPercentage;
    }

    public void setMarkupPercentage(int markupPercentage) {
        Validator.isNegativeInteger(markupPercentage);
        this.markupPercentage = markupPercentage;
    }

    public int getDecreasePercentage() {
        return decreasePercentage;
    }

    public void setDecreasePercentage(int decreasePercentage) {
        Validator.isNegativeInteger(decreasePercentage);
        this.decreasePercentage = decreasePercentage;
    }

    public int getDaysToExpire() {
        return daysToExpire;
    }

    public int getCashiersCount() {
        return this.cashiers.size();
    }

    public int getDeliveredStocksCount() {
        return this.deliveredStocks.size();
    }

    public int getSoldStocksCount() {
        return this.soldStocks.size();
    }

    public void setDaysToExpire(int daysToExpire) {
        Validator.isNegativeInteger(daysToExpire);
        this.daysToExpire = daysToExpire;
    }

    public int getTotalReceiptsCount() {
        return totalReceiptsCount;
    }

    public void setTotalReceiptsCount(int totalReceiptsCount) {
        this.totalReceiptsCount = totalReceiptsCount;
    }

    //Business Logic

    @Override
    public void hireCashier(Cashier cashier) {
        this.cashiers.add(cashier);
    }

    @Override
    public void deliverStock(Stock stock) {
        int daysLeft = stock.getExpireDate().getDayOfMonth();

        if (!isExpired(daysLeft)) {
            this.deliveredStocks.add(stock);
            evaluateStockPrice(stock);
        }
    }

    @Override
    public void getSummary() {
        BigDecimal totalCosts = this.getSalariesCost().add(this.getDeliveryStocksCost());
        BigDecimal totalProfit = this.getSoldStocksProfit();
        System.out.println("" +
                " " + totalProfit.subtract(totalCosts));
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
        return days - this.daysToExpire > 0 ? false : true;
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
