package com.company.Util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static void validatePrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ONE) < 0) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateStockName(String stockName) {
        if (!matchPattern(Expressions.stockNameExpression, stockName)) {
            throw new IllegalArgumentException(String.format(Messages.invalidStockName, stockName));
        }
    }

    public static void validateStockId(String stockId) {
        if (!matchPattern(Expressions.stockIdExpression, stockId)) {
            throw new IllegalArgumentException(String.format(Messages.invalidStockId, stockId));
        }
    }
    public static void validateCashierId(String cashierId){
        if (!matchPattern(Expressions.cashierIdExpression, cashierId)) {
            throw new IllegalArgumentException(String.format(Messages.invalidCashierId, cashierId));
        }
    }
    public static void validateCashierName(String cashierName){
        if (!matchPattern(Expressions.cashierNameExpression, cashierName)) {
            throw new IllegalArgumentException(String.format(Messages.invalidCashierName, cashierName));
        }
    }
    private static boolean matchPattern(String expression, String input) {
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }
}
