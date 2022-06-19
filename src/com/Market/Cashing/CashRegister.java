package com.Market.Cashing;

import com.Market.Exceptions.InvalidChangeException;
import com.Market.Exceptions.InvalidSellException;
import com.Market.Stock.Stock;
import com.Market.Util.Validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CashRegister {
    //Fields

    private List<Stock> soldStocks;
    private List<Stock> deliveredStocks;
    int receiptsCount;
    BigDecimal totalProfit;
    Cashier cashier;

    //Constructors

    public CashRegister(List<Stock> deliveredStocks, List<Stock> soldStocks, Cashier cashier) {
        this.deliveredStocks = deliveredStocks;
        this.soldStocks = soldStocks;
        this.cashier = cashier;
        this.totalProfit = BigDecimal.valueOf(0);
        this.receiptsCount = 0;
    }

    //Getters and setters

    public int getReceiptsCount() {
        return this.receiptsCount;
    }

    public int getSoldStocksCount() {
        return this.soldStocks.size();
    }

    public int getDeliveredStocksCount() {
        return this.deliveredStocks.size();
    }

    public Cashier getCashier() {
        return this.cashier;
    }

    public BigDecimal getTotalProfit() {
        return this.totalProfit;
    }

    public void setReceiptsCount(int receiptsCount) {
        Validator.isNegativeInteger(receiptsCount);
        this.receiptsCount = receiptsCount;
    }

    //Business logic

    public void sellStock(BigDecimal payment, List<Stock> stocks) {
        try {
            areAvailable(stocks);
            List<Stock> availableStocks = getAvailableStocks(stocks);

            BigDecimal totalPaymentAmount = this.getTotalPaymentAmount(availableStocks);

            this.cashier.processPayment(payment, totalPaymentAmount, availableStocks);
            this.totalProfit = this.totalProfit.add(totalPaymentAmount);

            decreaseStocks(availableStocks);
            this.receiptsCount += 1;
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void decreaseStocks(List<Stock> stocks) {
        for (Stock stock : stocks) {
            var currentStock = findStockByName(stock.getName());
            currentStock.setQuantity(currentStock.getQuantity() - stock.getQuantity());
            soldStocks.add(stock);
        }
    }

    private Stock findStockByName(String name) {
        for (Stock stock : this.deliveredStocks) {
            if (stock.getName().equals(name)) {
                return stock;
            }
        }
        return null;
    }

    private void areAvailable(List<Stock> stocks) throws InvalidSellException {
        for (Stock stock : stocks) {
            Stock currentStock = findStockByName(stock.getName());
            if (currentStock == null) {
                throw new InvalidSellException(stock.getName(), stock.getQuantity());
            }
            if (currentStock.getQuantity() < stock.getQuantity()) {
                int requiredQuantity = Math.abs(stock.getQuantity() - currentStock.getQuantity());
                throw new InvalidSellException(stock.getName(), requiredQuantity);
            }
        }
    }

    private List<Stock> getAvailableStocks(List<Stock> stocks) {
        List<Stock> availableStocks = new ArrayList<>();
        for (Stock stock : stocks) {
            Stock currentStock = new Stock(findStockByName(stock.getName()));
            currentStock.setQuantity(stock.getQuantity());
            availableStocks.add(currentStock);
        }
        return availableStocks;
    }

    private BigDecimal getTotalPaymentAmount(List<Stock> stocks) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Stock stock : stocks) {
            Stock currentStock = findStockByName(stock.getName());
            BigDecimal currentPrice = currentStock.getSellPrice().multiply(BigDecimal.valueOf(stock.getQuantity()));
            totalPrice = totalPrice.add(currentPrice);
        }
        return totalPrice;
    }


}
