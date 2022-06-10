package com.Market.Contracts;

import com.Market.Stock.Stock;

import java.math.BigDecimal;
import java.util.List;

public interface Cashable {
    public void processPayment(BigDecimal payment, BigDecimal totalPaymentAmount, List<Stock> stocks);
}
