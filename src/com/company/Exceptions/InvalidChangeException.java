package com.company.Exceptions;

import com.company.Util.Messages;

import java.math.BigDecimal;

public class InvalidChangeException extends Exception {
    BigDecimal change;

    public InvalidChangeException(BigDecimal change) {
        this.change = change;
    }

    public String toString() {
        return String.format(Messages.unsuccessfulPayment, change);
    }
}
