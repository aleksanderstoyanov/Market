package com.company.Cashing;

import com.company.Exceptions.InvalidSellException;
import com.company.Stock.Stock;

import java.math.BigDecimal;
import java.util.List;

public class CashRegister {
    private List<Stock> stocks;
    int receiptsCount;
    BigDecimal totalProfit;
    Cashier cashier;

    public CashRegister(List<Stock> stocks, Cashier cashier) {
        this.stocks = stocks;
        this.cashier = cashier;
    }

    public void sellStock(BigDecimal payment, List<Stock> stocks) throws InvalidSellException {
        areAvailable(stocks);
        BigDecimal totalPaymentAmount = this.getTotalPaymentAmount(stocks);
        this.cashier.processPayment(payment, totalPaymentAmount, stocks);
    }

    private void areAvailable(List<Stock> stocks) throws InvalidSellException {
        try{
            for (Stock stock : stocks) {
                var stockIndex = this.stocks.indexOf(stock);
                if (this.stocks.get(stockIndex).getQuantity() < stock.getQuantity()) {
                    int requiredQuantity = Math.abs(stock.getQuantity() - this.stocks.get(stockIndex).getQuantity());
                    throw new InvalidSellException(stock.getName(), requiredQuantity);
                }
            }
        }
        catch(InvalidSellException ex){
            System.out.println(ex);
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
