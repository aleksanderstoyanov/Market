package com.company.Cashing;

import com.company.Contracts.Cashable;
import com.company.Exceptions.InvalidChangeException;
import com.company.Stock.Stock;
import com.company.Util.Expressions;
import com.company.Util.Messages;
import com.company.Util.Validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Cashier implements Cashable {
    String id;
    String name;
    BigDecimal salary;
    BigDecimal currentPayment;

    public Cashier(String id, String name, BigDecimal salary) {
        Validator.validatePattern(id, Expressions.cashierIdExpression, Messages.invalidCashierId);
        this.id = id;
        Validator.validatePattern(name, Expressions.cashierNameExpression, Messages.invalidCashierName);
        this.name = name;
        Validator.isNegativeDecimal(salary);
        this.salary = salary;
    }

    private void enterPayment(BigDecimal payment) {
        this.currentPayment = payment;
    }

    private void giveChange(BigDecimal totalPaymentAmount) throws InvalidChangeException {
        if (this.currentPayment.compareTo(totalPaymentAmount) == -1) {
            throw new InvalidChangeException(this.currentPayment);
        } else if (this.currentPayment.compareTo(totalPaymentAmount) == 0) {
            System.out.println(Messages.successfulPayment + ", you gave me the exact amount");
        } else {
            BigDecimal change = this.currentPayment.subtract(totalPaymentAmount);
            System.out.println(Messages.successfulPayment + ", your change is: " + change);
        }
    }

    private String getStockInformation(List<Stock> stocks) {
        StringBuffer sb = new StringBuffer();
        for (Stock stock : stocks) {
            sb.append(stock.toString());
        }
        return sb.toString().trim();
    }

    private void createReceipt(BigDecimal totalPaymentAmount, List<Stock> stocks) {
        String stocksInformation = getStockInformation(stocks);
        Receipt receipt = new Receipt(1, this.name, LocalDate.now(), stocksInformation, totalPaymentAmount);
        System.out.println(receipt.toString());
    }

    @Override
    public void processPayment(BigDecimal payment, BigDecimal totalPaymentAmount, List<Stock> stocks) {
        enterPayment(payment);
        createReceipt(totalPaymentAmount, stocks);
        try {
            giveChange(totalPaymentAmount);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
