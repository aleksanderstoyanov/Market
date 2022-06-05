package com.company.Stock;

import com.company.Util.Expressions;
import com.company.Util.Messages;
import com.company.Util.Validator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Stock {
    String id;
    String name;
    BigDecimal deliveryPrice;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        Validator.isNegativeInteger(quantity);
        this.quantity = quantity;
    }

    int quantity;
    BigDecimal sellPrice;
    Category category;
    LocalDate expireDate;

    public Stock(String id, String name, BigDecimal deliveryPrice, int quantity, BigDecimal sellPrice, Category category, LocalDate expireDate) throws IllegalArgumentException {
        Validator.validatePattern(id, Expressions.stockIdExpression, Messages.invalidStockId);
        this.id = id;

        Validator.validatePattern(name, Expressions.stockNameExpression, Messages.invalidStockName);
        this.name = name;

        Validator.isNegativeDecimal(deliveryPrice);
        this.deliveryPrice = deliveryPrice;

        Validator.isNegativeInteger(quantity);
        this.quantity = quantity;

        Validator.isNegativeDecimal(sellPrice);
        this.sellPrice = sellPrice;

        this.category = category;
        this.expireDate = expireDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        Validator.validatePattern(id, Expressions.stockIdExpression, Messages.invalidStockId);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Validator.validatePattern(name, Expressions.stockNameExpression, Messages.invalidStockName);
        this.name = name;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        Validator.isNegativeDecimal(deliveryPrice);
        this.deliveryPrice = deliveryPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        //Validator.isNegativeDecimal(sellPrice);
        this.sellPrice = sellPrice;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        
        sb.append(String.format("Stock ID: %s ", this.id));
        sb.append(String.format("Stock Name: %s ", this.name));
        sb.append(String.format("Stock Price: $%s ", this.sellPrice));
        sb.append(String.format("Quantity: $%s " + this.quantity));

        return sb.toString().trim();
    }
}
