package com.company.Stock;

import com.company.Util.Validator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Stock {
    String id;
    String name;
    BigDecimal deliveryPrice;
    Type type;
    LocalDate expireDate;

    public Stock(String id, String name, BigDecimal deliveryPrice, Type type, LocalDate expireDate) throws IllegalArgumentException {
        Validator.validateStockId(id);
        this.id = id;

        Validator.validateStockName(name);
        this.name = name;

        Validator.validatePrice(deliveryPrice);
        this.deliveryPrice = deliveryPrice;

        this.type = type;
        this.expireDate = expireDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        Validator.validateStockId(id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Validator.validateStockName(name);
        this.name = name;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        Validator.validatePrice(deliveryPrice);
        this.deliveryPrice = deliveryPrice;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deliveryPrice=" + deliveryPrice +
                ", type=" + type +
                ", expireDate=" + expireDate +
                '}';
    }
}
