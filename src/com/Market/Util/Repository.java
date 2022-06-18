package com.Market.Util;

import com.Market.Cashing.Cashier;
import com.Market.Stock.Category;
import com.Market.Stock.Stock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Repository {

    public static List<Stock> stocks = new ArrayList<>(Arrays.asList(
            new Stock("1233-312-3123", "Banana", BigDecimal.valueOf(1.25), 2, BigDecimal.valueOf(2.25), Category.Edible, LocalDate.now()),
            new Stock("1263-317-3123", "Bread", BigDecimal.valueOf(1.52), 10, BigDecimal.valueOf(1.60), Category.Edible, LocalDate.now()),
            new Stock("1238-312-3723", "Shampoo", BigDecimal.valueOf(3.72), 10, BigDecimal.valueOf(5.60), Category.NonEdible, LocalDate.now()),
            new Stock("1233-712-7892", "Screwdriver", BigDecimal.valueOf(1.25), 1, BigDecimal.valueOf(2.25), Category.NonEdible, LocalDate.now())
    ));

    public static List<Cashier> cashiers = new ArrayList<>(Arrays.asList(
            new Cashier("12_1234", "John Johnson", BigDecimal.valueOf(1421.512)),
            new Cashier("13_1564", "Ivan Ivanov", BigDecimal.valueOf(1321.122)),
            new Cashier("14_1214", "Pesho Peshov", BigDecimal.valueOf(1132.322))
    ));
}
