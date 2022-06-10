import com.Market.Cashing.CashRegister;
import com.Market.Cashing.Cashier;
import com.Market.Exceptions.InvalidChangeException;
import com.Market.Exceptions.InvalidSellException;
import com.Market.Stock.Category;
import com.Market.Stock.Stock;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CashRegisterTest {
    private List<Stock> deliveredStocks;
    private List<Stock> soldStocks;
    private Cashier cashier;

    @Before
    public void init() {
        this.deliveredStocks = new ArrayList<Stock>();
        this.deliveredStocks.add(new Stock("1233-312-3123", "Banana", BigDecimal.valueOf(1.25), 2, BigDecimal.valueOf(2.25), Category.Edible, LocalDate.now()));
        this.deliveredStocks.add(new Stock("1233-312-3123", "Screwdriver", BigDecimal.valueOf(1.25), 1, BigDecimal.valueOf(2.25), Category.NonEdible, LocalDate.now()));

        this.soldStocks = new ArrayList<>();

        this.cashier = new Cashier("12_1234", "John Johnson", BigDecimal.valueOf(1421.512));
    }

    @Test
    public void constructorShouldInstantiateValidObject() {
        CashRegister cashRegister = new CashRegister(deliveredStocks, soldStocks, this.cashier);

        assertEquals(2, this.deliveredStocks.size());
        assertEquals(0, this.soldStocks.size());

        assertSame(cashier, cashRegister.getCashier());
    }

    @Test
    public void setReceiptsCountShouldThrowException() {
        CashRegister cashRegister = new CashRegister(deliveredStocks, soldStocks, this.cashier);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    cashRegister.setReceiptsCount(-1);
                });
    }

    @Test
    public void setReceiptsCountShouldChangeSuccessfully() {
        CashRegister cashRegister = new CashRegister(deliveredStocks, soldStocks, this.cashier);
        cashRegister.setReceiptsCount(3);

        assertEquals(3, cashRegister.getReceiptsCount());
    }

    @Test
    public void sellStockShouldThrowExceptionIfStocksAreNotFound() {
        CashRegister cashRegister = new CashRegister(deliveredStocks, soldStocks, this.cashier);
        List<Stock> requestedStocks = new ArrayList<Stock>();

        requestedStocks.add(new Stock("1233-312-3123", "Orange", BigDecimal.valueOf(1.252), 2, BigDecimal.valueOf(2.25), Category.Edible, LocalDate.now()));

        assertThrows(InvalidSellException.class,
                () -> {
                    cashRegister.sellStock(BigDecimal.valueOf(12.52), requestedStocks);
                });
    }

    @Test
    public void sellStockShouldThrowExceptionIfStocksQuantityIsNotEnough() {
        CashRegister cashRegister = new CashRegister(deliveredStocks, soldStocks, this.cashier);
        List<Stock> requestedStocks = new ArrayList<Stock>();

        requestedStocks.add(new Stock("1233-312-3123", "Banana", BigDecimal.valueOf(1.252), 3, BigDecimal.valueOf(2.25), Category.Edible, LocalDate.now()));

        assertThrows(InvalidSellException.class,
                () -> {
                    cashRegister.sellStock(BigDecimal.valueOf(12.52), requestedStocks);
                });
    }

    @Test
    public void sellStockShouldThrowExceptionIfPaymentIsNotEnough() {
        CashRegister cashRegister = new CashRegister(deliveredStocks, soldStocks, this.cashier);
        List<Stock> requestedStocks = new ArrayList<Stock>();

        requestedStocks.add(new Stock("1233-312-3123", "Banana", BigDecimal.valueOf(1.252), 1, BigDecimal.valueOf(2.25), Category.Edible, LocalDate.now()));

        assertThrows(InvalidChangeException.class,
                () -> {
                    cashRegister.sellStock(BigDecimal.valueOf(1.25), requestedStocks);
                });
    }

    @Test
    public void sellShouldAddProfitSuccessfully() throws InvalidSellException, InvalidChangeException {
        CashRegister cashRegister = new CashRegister(deliveredStocks, soldStocks, this.cashier);
        List<Stock> requestedStocks = new ArrayList<Stock>();

        requestedStocks.add(new Stock("1233-312-3123", "Banana", BigDecimal.valueOf(1.252), 2, BigDecimal.valueOf(2.25), Category.Edible, LocalDate.now()));
        cashRegister.sellStock(BigDecimal.valueOf(5.25), requestedStocks);

        assertTrue(BigDecimal.valueOf(4.50).compareTo(cashRegister.getTotalProfit()) == 0);
        assertEquals(1, cashRegister.getReceiptsCount());
    }

    @Test
    public void sellShouldAddSoldStocks() throws InvalidSellException, InvalidChangeException {
        CashRegister cashRegister = new CashRegister(deliveredStocks, soldStocks, this.cashier);
        List<Stock> requestedStocks = new ArrayList<Stock>();

        requestedStocks.add(new Stock("1233-312-3123", "Banana", BigDecimal.valueOf(1.252), 2, BigDecimal.valueOf(2.25), Category.Edible, LocalDate.now()));
        cashRegister.sellStock(BigDecimal.valueOf(5.25), requestedStocks);

        assertEquals(1, cashRegister.getSoldStocksCount());
    }

}
