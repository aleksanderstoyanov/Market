package com.company.Stock;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Stock {
    String id;
    String name;
    BigDecimal deliveryPrice;
    Type type;
    LocalDate expireDate;

    public Stock(String id, String name, BigDecimal deliveryPrice, Type type, LocalDate expireDate) {
        this.id = id;
        this.name = name;
        this.deliveryPrice = deliveryPrice;
        this.type = type;
        this.expireDate = expireDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
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
