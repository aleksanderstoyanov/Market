package com.company.Cashier;

import com.company.Util.Validator;

import java.math.BigDecimal;

public class Cashier {
    String id;
    String name;
    BigDecimal Salary;

    public Cashier(String id, String name, BigDecimal salary) {
        Validator.validateCashierId(id);
        this.id = id;
        Validator.validateCashierName(name);
        this.name = name;
        Validator.validatePrice(salary);
        Salary = salary;
    }
}
