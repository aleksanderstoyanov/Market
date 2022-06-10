package com.Market.Util;

public class Expressions {
    public static final String stockNameExpression = "^[A-Za-z ]*$";
    public static final String stockIdExpression = "[\\d]{4}-[\\d]{3}-[\\d]{4}";

    public static final String cashierIdExpression = "^\\d{2}_\\d{4}$";
    public static final String cashierNameExpression = "^[A-Z]{1}[a-z]+ [A-Z]{1}[a-z]+$";
}
