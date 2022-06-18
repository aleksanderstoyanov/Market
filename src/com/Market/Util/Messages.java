package com.Market.Util;

public class Messages {
    public static final String invalidStockId = "Current input: %s is not a valid stock id! The pattern should be [XXXX-XXX-XXXX]";
    public static final String invalidStockName = "Current input: %s is not a valid stock name!";

    public static final String invalidCashierId = "Current input %s is not a valid cashier id! The pattern should match [XX_XXXX]";
    public static final String invalidCashierName = "Current input %s is not a valid cashier name!";

    public static final String successfulPayment = "Thank you for your payment sir/miss!";
    public static final String unsuccessfulPayment = "I'm sorry sir/miss, payment cannot be processed change %s is not enough for the payment!";

    public static final String unsuccessfulSell = "Sell cannot be processed stock %s needs %s quantity needed!";

    public static final String unprofitableMessage = "Market is not profitable with a debt of $%s precautions need to be taken !";
    public static final String profitableMessage = "Market is profitable with a total profit $%s time for celebration !";

    public static final String deliveredProductMessage = "Product %s with quantity %s has been delivered !";
    public static final String hiredCashierMessage = "Cashier %s with salary %s has been hired !";
}
