package com.company.Util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static void validatePattern(String input, String expression, String message){
        if (!matchPattern(expression, input)) {
            throw new IllegalArgumentException(String.format(message, input));
        }
    }
    public static void isNegativeDecimal(BigDecimal input) {
        if (input.compareTo(BigDecimal.ONE) < 0.0) {
            throw new IllegalArgumentException();
        }
    }
    public static void isNegativeInteger(int input){
        if (input < 0){
            throw new IllegalArgumentException();
        }
    }
    private static boolean matchPattern(String expression, String input) {
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }
}
