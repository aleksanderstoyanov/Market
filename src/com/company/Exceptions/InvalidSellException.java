package com.company.Exceptions;

import com.company.Util.Messages;

public class InvalidSellException extends Exception {
    String stockName;
    int requiredQuantity;

    public InvalidSellException(String stockName, int requiredQuantity) {
        this.stockName = stockName;
        this.requiredQuantity = requiredQuantity;
    }

    public String toString() {
        return String.format(Messages.unsuccessfulSell, stockName, requiredQuantity);
    }
}
