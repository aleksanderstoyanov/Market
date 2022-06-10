package com.company.Contracts;

import com.company.Cashing.Cashier;
import com.company.Stock.Stock;

public interface Marketable {
    void hireCashier(Cashier cashier);
    void evaluateStockPrice(Stock stock);
    void getTotalReceipts();
    void addCashRegister(Cashier cashier);
    void deliverStock(Stock stock);
    void getSummary();
}
