package com.Market.Contracts;

import com.Market.Cashing.Cashier;
import com.Market.Stock.Stock;

public interface Marketable {
    void hireCashier(Cashier cashier);
    void evaluateStockPrice(Stock stock);
    void getTotalReceipts();
    void addCashRegister(Cashier cashier);
    void deliverStock(Stock stock);
    void getSummary();
}
