package com.company.Cashing;

import com.company.Stock.Stock;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

public class CashRegister {
    private List<Stock> stocks;
    int receiptsCount;
    BigDecimal totalProfit;
    Cashier cashier;

    public CashRegister(List<Stock> stocks, Cashier cashier) {
        this.stocks = stocks;
        this.cashier = cashier;
    }

    public void sellStock(BigDecimal payment, List<Stock> stocks) {
        areAvailable(stocks);
        BigDecimal totalPaymentAmount = this.getTotalPaymentAmount(stocks);
        this.cashier.processPayment(payment, totalPaymentAmount, stocks);
    }

    private void areAvailable(List<Stock> stocks) {
        for (Stock stock : stocks) {
            if (!this.stocks.contains(stock)) {
                //throw exception
            }
            var stockIndex = this.stocks.indexOf(stock);
            if (this.stocks.get(stockIndex).getQuantity() < stock.getQuantity()) {
                //throw exception
            }
        }
    }

    private BigDecimal getTotalPaymentAmount(List<Stock> stocks) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Stock stock : stocks) {
            totalPrice.add(stock.getSellPrice());
        }
        return totalPrice;
    }


}
