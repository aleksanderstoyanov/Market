import com.Market.Cashing.Cashier;
import com.Market.Stock.Category;
import com.Market.Stock.Stock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class StockTest {
    private Stock stock;

    @Before
    public void init() {
        stock = new Stock("1233-312-3123", "Banana", BigDecimal.valueOf(1.25), 2, BigDecimal.valueOf(2.25), Category.Edible, LocalDate.now());
    }

    @Test
    public void constructorShouldReturnValidObject() {

        assertEquals("1233-312-3123", stock.getId());
        assertEquals("Banana", stock.getName());
        assertEquals(BigDecimal.valueOf(1.25), stock.getDeliveryPrice());
        assertEquals(2, stock.getQuantity());
        assertEquals(BigDecimal.valueOf(2.25), stock.getSellPrice());
        assertEquals(Category.Edible, stock.getCategory());
        assertEquals(LocalDate.now(), stock.getExpireDate());
    }

    @Test
    public void settersShouldManipulateObjectSuccessfully() {
        stock.setId("1234-321-4444");
        assertEquals("1234-321-4444", stock.getId());

        stock.setName("Orange");
        assertEquals("Orange", stock.getName());

        stock.setQuantity(5);
        assertEquals(5, stock.getQuantity());

        stock.setDeliveryPrice(BigDecimal.valueOf(2.52));
        assertEquals(BigDecimal.valueOf(2.52), stock.getDeliveryPrice());

        stock.setSellPrice(BigDecimal.valueOf(2.52));
        assertEquals(BigDecimal.valueOf(2.52), stock.getSellPrice());

        stock.setExpireDate(LocalDate.now());
        assertEquals(LocalDate.now(), stock.getExpireDate());

        stock.setCategory(Category.NonEdible);
        assertEquals(Category.NonEdible, Category.NonEdible);
    }

    @Test
    public void toStringShouldReturnValidString() {
        StringBuffer sb = new StringBuffer();

        sb.append(String.format("Stock ID: %s ", stock.getId()));
        sb.append(String.format("Stock Name: %s ", stock.getName()));
        sb.append(String.format("Stock Price: $%s ", stock.getSellPrice()));
        sb.append(String.format("Quantity: $%s ", stock.getQuantity()));

        assertEquals(sb.toString().trim(), stock.toString());
    }

    @Test
    public void invalidIdShouldInConstructorThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    var stock = new Stock("InvalidId", "Banana", BigDecimal.valueOf(1), 2, BigDecimal.valueOf(2), Category.Edible, LocalDate.now());
                });
    }

    @Test
    public void invalidNameShouldInConstructorThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    var stock = new Stock("1232-123-5123", "Ban@na", BigDecimal.valueOf(1), 2, BigDecimal.valueOf(2), Category.Edible, LocalDate.now());
                });
    }

    @Test
    public void invalidDeliveryPriceInConstructorShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    var stock = new Stock("1232-123-5123", "Banana", BigDecimal.valueOf(-1), 2, BigDecimal.valueOf(2), Category.Edible, LocalDate.now());
                });
    }

    @Test
    public void invalidQuantityPriceInConstructorShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    var stock = new Stock("1232-123-5123", "Banana", BigDecimal.valueOf(4), -2, BigDecimal.valueOf(2), Category.Edible, LocalDate.now());
                });
    }

    @Test
    public void invalidSellPriceInConstructorShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    var stock = new Stock("1232-123-5123", "Banana", BigDecimal.valueOf(4), 3, BigDecimal.valueOf(-2), Category.Edible, LocalDate.now());
                });
    }

    @Test
    public void setInvalidIdShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    stock.setId("Invalid");
                });
    }

    @Test
    public void setInvalidNameShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    stock.setName("Inv@lidName");
                });
    }

    @Test
    public void setInvalidDeliveryPriceShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    stock.setDeliveryPrice(BigDecimal.valueOf(-1.25));
                });
    }

    @Test
    public void setInvalidSellingPriceShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    stock.setSellPrice(BigDecimal.valueOf(-1.25));
                });
    }

}
