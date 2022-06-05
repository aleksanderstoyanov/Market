package com.company.Contracts;

import com.company.Stock.Stock;

import java.math.BigDecimal;
import java.util.List;

public interface Cashable {
    public void processPayment(BigDecimal payment, BigDecimal totalPaymentAmount, List<Stock> stocks);
}
