package com.company.Cashier;

import com.company.Util.Expressions;
import com.company.Util.Messages;
import com.company.Util.Validator;

import java.math.BigDecimal;

public class Cashier {
    String id;
    String name;
    BigDecimal Salary;

    public Cashier(String id, String name, BigDecimal salary) {
        Validator.validatePattern(id, Expressions.cashierIdExpression, Messages.invalidCashierId);
        this.id = id;
        Validator.validatePattern(name, Expressions.cashierNameExpression, Messages.invalidCashierName);
        this.name = name;
        Validator.isNegativeDecimal(salary);
        Salary = salary;
    }
}
