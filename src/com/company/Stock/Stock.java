package com.company.Stock;

import com.company.Util.Validator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Stock {
    String id;
    String name;
    BigDecimal deliveryPrice;
    BigDecimal sellPrice;
    Category category;
    LocalDate expireDate;

    public Stock(String id, String name, BigDecimal deliveryPrice,BigDecimal sellPrice, Category category, LocalDate expireDate) throws IllegalArgumentException {
        Validator.validateStockId(id);
        this.id = id;

        Validator.validateStockName(name);
        this.name = name;

        Validator.validatePrice(deliveryPrice);
        this.deliveryPrice = deliveryPrice;

        Validator.validatePrice(sellPrice);
        this.sellPrice = sellPrice;

        this.category = category;
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
    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        Validator.validatePrice(sellPrice);
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
        return "Stock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deliveryPrice=" + deliveryPrice +
                ", Category=" + category +
                ", expireDate=" + expireDate +
                ", sell price=" + sellPrice +
                '}';
    }
}
