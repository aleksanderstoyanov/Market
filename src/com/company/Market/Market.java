package com.company.Market;

import com.company.Cashing.CashRegister;
import com.company.Stock.Stock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Market {
    private List<Stock> stocks;
    private List<CashRegister> cashRegisters;
    int markupPercentage;
    int decreasePercentage;
    int daysToExpire;

    public Market() {
        this.stocks = new ArrayList<Stock>();
        this.cashRegisters = new ArrayList<CashRegister>();
    }

    public Market(int markupPercentage, int decreasePercentage, int daysToExpire) {
        this.markupPercentage = markupPercentage;
        this.decreasePercentage = decreasePercentage;
        this.daysToExpire = daysToExpire;
        this.stocks = new ArrayList<Stock>();

        this.cashRegisters = new ArrayList<CashRegister>();
    }

    public void addStock(Stock stock) {
        int daysLeft = stock.getExpireDate().getDayOfMonth();

        if (!isExpired(daysLeft)) {
            evaluateStockPrice(stock);
            this.stocks.add(stock);
        }
    }
    private boolean isExpired(int days) {
        return days - this.daysToExpire > 0 ? true : false;
    }

    private void evaluateStockPrice(Stock stock) {
        BigDecimal price = stock.getSellPrice();
        double percentage = (double) this.decreasePercentage / 100;
        BigDecimal decreasePrice = price.multiply(BigDecimal.valueOf(percentage));

        stock.setSellPrice(price.subtract(decreasePrice));
    }


}
