import com.Market.Cashing.Cashier;
import com.Market.Market.Market;
import com.Market.Stock.Category;
import com.Market.Stock.Stock;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MarketTest {
    private Market market;
    private List<Stock> deliveredStocks;
    private List<Stock> soldStocks;
    private Cashier cashier;


    @Before
    public void init() {
        market = new Market(12, 5, 2);

        this.deliveredStocks = new ArrayList<Stock>();
        this.deliveredStocks.add(new Stock("1233-312-3123", "Banana", BigDecimal.valueOf(1.25), 2, BigDecimal.valueOf(2.25), Category.Edible, LocalDate.now()));
        this.deliveredStocks.add(new Stock("1233-312-3123", "Screwdriver", BigDecimal.valueOf(1.25), 1, BigDecimal.valueOf(2.25), Category.NonEdible, LocalDate.now()));

        this.soldStocks = new ArrayList<>();

        this.cashier = new Cashier("12_1234", "John Johnson", BigDecimal.valueOf(1421.512));
    }

    @Test
    public void constructorShouldInstantiateValidObject() {

        assertEquals(12, this.market.getMarkupPercentage());
        assertEquals(5, this.market.getDecreasePercentage());
        assertEquals(2, this.market.getDaysToExpire());
    }

    @Test
    public void settersShouldManipulateObjectSuccessfully() {
        this.market.setMarkupPercentage(5);
        assertEquals(5, this.market.getMarkupPercentage());

        this.market.setDecreasePercentage(2);
        assertEquals(2, this.market.getDecreasePercentage());

        this.market.setDaysToExpire(2);
        assertEquals(2, this.market.getDaysToExpire());
    }

    @Test
    public void hireCashierShouldAddToCollectionSuccessfully() {
        this.market.hireCashier(cashier);
        assertEquals(1, this.market.getCashiersCount());
    }

    @Test
    public void deliverStockShouldAddToCollectionSuccessfully() {
        this.market.deliverStock(this.deliveredStocks.get(0));
        assertEquals(1,this.market.getDeliveredStocksCount());
    }
    @Test
    public void deliverStockShouldNotAddToCollectionSuccessfully() {
        var stock=this.deliveredStocks.get(0);
        stock.setExpireDate(LocalDate.of(1999,1,1));

        this.market.deliverStock(stock);
        assertEquals(0,this.market.getDeliveredStocksCount());
    }
    @Test
    public void deliverStockShouldReevaluatePriceOfStock(){
        var stock=this.deliveredStocks.get(0);
        this.market.deliverStock(stock);

        assertEquals(BigDecimal.valueOf(2.1375),stock.getSellPrice());
    }
    @Test
    public void invalidMarkupPercentageShouldInConstructorThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    var market = new Market(-12, 5, 2);
                });
    }

    @Test
    public void invalidDecreasePercentageShouldInConstructorThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    var market = new Market(12, -5, 2);
                });
    }

    @Test
    public void invalidDaysToExpireShouldInConstructorThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    var market = new Market(12, 5, -5);
                });
    }

    @Test
    public void setMarkupPercentageShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.market.setMarkupPercentage(-1);
                });
    }

    @Test
    public void setDecreasePercentageShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.market.setDecreasePercentage(-1);
                });
    }

    @Test
    public void setDaysToExpireShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.market.setDecreasePercentage(-1);
                });
    }
}
