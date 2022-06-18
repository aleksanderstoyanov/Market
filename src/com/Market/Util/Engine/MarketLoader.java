package com.Market.Util.Engine;

public class MarketLoader {

    public static void loadingDeliveredProducts() throws InterruptedException {
        System.out.print("Delivering products");
        loadAnimation();
    }

    public static void loadingHiredCashiers() throws InterruptedException {
        System.out.print("Hiring cashiers");
        loadAnimation();
    }
    public static void loadingCashRegisters() throws InterruptedException{
        System.out.print("Adding cash registers");
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
