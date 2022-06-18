package com.Market;

import com.Market.Cashing.CashRegister;
import com.Market.Cashing.Cashier;
import com.Market.Market.Market;
import com.Market.Stock.Category;
import com.Market.Stock.Stock;
import com.Market.Util.Engine.MarketEngine;
import com.Market.Util.Engine.MarketLoader;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MarketEngine engine = new MarketEngine();
        engine.run();
    }

}
