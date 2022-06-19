package com.Market.Cashing;

import com.Market.Contracts.Cashable;
import com.Market.Exceptions.InvalidChangeException;
import com.Market.Stock.Stock;
import com.Market.Util.Expressions;
import com.Market.Util.Messages;
import com.Market.Util.Validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Cashier implements Cashable {
    //Fields
    String id;
    String name;
    BigDecimal salary;
    BigDecimal currentPayment;

    //Constructors

    public Cashier(String id, String name, BigDecimal salary) {
        Validator.validatePattern(id, Expressions.cashierIdExpression, Messages.invalidCashierId);
        this.id = id;
        Validator.validatePattern(name, Expressions.cashierNameExpression, Messages.invalidCashierName);
        this.name = name;
        Validator.isNegativeDecimal(salary);
        this.salary = salary;
    }

    //Getters and setters

    public BigDecimal getCurrentPayment() {
        return currentPayment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        Validator.validatePattern(id, Expressions.cashierIdExpression, Messages.invalidCashierId);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Validator.validatePattern(name, Expressions.cashierNameExpression, Messages.invalidCashierName);
        this.name = name;
    }


    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        Validator.isNegativeDecimal(salary);
        this.salary = salary;
    }

    //Business logic

    @Override
    public void processPayment(BigDecimal payment, BigDecimal totalPaymentAmount, List<Stock> stocks) {
        try{
            enterPayment(payment);
            giveChange(totalPaymentAmount);
            createReceipt(totalPaymentAmount, stocks);
        }
        catch (InvalidChangeException ex){
            System.out.println(ex);
        }
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
            sb.append(stock.toString() + "\n");
        }
        return sb.toString().trim();
    }

    private void createReceipt(BigDecimal totalPaymentAmount, List<Stock> stocks) {
        String stocksInformation = getStockInformation(stocks);
        Receipt receipt = new Receipt(this.name, LocalDate.now(), stocksInformation, totalPaymentAmount);

        receipt.writeFile();
        System.out.println(receipt.toString());
    }
}
