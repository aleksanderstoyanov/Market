package com.Market.Exceptions;

import com.Market.Util.Messages;

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
