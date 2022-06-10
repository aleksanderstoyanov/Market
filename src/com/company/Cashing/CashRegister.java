package com.company.Cashing;

import com.company.Exceptions.InvalidSellException;
import com.company.Stock.Stock;

import java.math.BigDecimal;
import java.util.List;

public class CashRegister {
    private List<Stock> soldStocks;
    private List<Stock> deliveredStocks;
    int receiptsCount;
    BigDecimal totalProfit;
    Cashier cashier;

    public CashRegister(List<Stock> deliveredStocks, List<Stock> soldStocks, Cashier cashier) {
        this.deliveredStocks = deliveredStocks;
        this.soldStocks = soldStocks;
        this.cashier = cashier;
        this.totalProfit = BigDecimal.valueOf(0);
        this.receiptsCount = 0;
    }

    public int getReceiptsCount() {
        return receiptsCount;
    }

    public void setReceiptsCount(int receiptsCount) {
        this.receiptsCount = receiptsCount;
    }

    public void sellStock(BigDecimal payment, List<Stock> stocks) throws InvalidSellException {
        areAvailable(stocks);
        BigDecimal totalPaymentAmount = this.getTotalPaymentAmount(stocks);

        this.cashier.processPayment(payment, totalPaymentAmount, stocks);
        this.totalProfit.add(totalPaymentAmount);
        this.receiptsCount += 1;
    }

    private void areAvailable(List<Stock> stocks) throws InvalidSellException {
        try {
            for (Stock stock : stocks) {
                var stockIndex = this.deliveredStocks.indexOf(stock);
                if (this.deliveredStocks.get(stockIndex).getQuantity() < stock.getQuantity()) {
                    int requiredQuantity = Math.abs(stock.getQuantity() - this.deliveredStocks.get(stockIndex).getQuantity());
                    throw new InvalidSellException(stock.getName(), requiredQuantity);
                }
            }
        } catch (InvalidSellException ex) {
            System.out.println(ex);
        }
    }
    //TO DO: Remove stock

    private BigDecimal getTotalPaymentAmount(List<Stock> stocks) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Stock stock : stocks) {
            BigDecimal currentPrice = stock.getSellPrice().multiply(BigDecimal.valueOf(stock.getQuantity()));
            totalPrice.add(currentPrice);
        }
        return totalPrice;
    }


}
