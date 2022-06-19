package com.Market.Util.Engine;

public class MarketLoader {

    public static void writeMessage(String message) throws InterruptedException{
        System.out.print(message);
        loadAnimation();
    }

    private static void loadAnimation() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Thread.sleep(500);
            System.out.print(".");
        }
        System.out.println();
    }
}
