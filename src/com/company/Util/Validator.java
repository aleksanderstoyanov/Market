package com.company.Util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    static void validatePrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ONE) < 0) {
            throw new IllegalArgumentException();
        }
    }

    static void validateStockName(String stockName) {
        if (!matchPattern(Expressions.stockNameExpression, stockName)) {
            throw new IllegalArgumentException(String.format(Messages.invalidStockName, stockName));
        }
    }

    static void validateStockId(String stockId) {
        if (!matchPattern(Expressions.stockIdExpression, stockId)) {
            throw new IllegalArgumentException(String.format(Messages.invalidStockId, stockId));
        }
    }

    private static boolean matchPattern(String expression, String input) {
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
