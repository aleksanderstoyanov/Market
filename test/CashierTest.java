import com.Market.Cashing.Cashier;
import com.Market.Exceptions.InvalidChangeException;
import com.Market.Exceptions.InvalidSellException;
import com.Market.Stock.Category;
import com.Market.Stock.Stock;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CashierTest {

    private Cashier cashier;
    private List<Stock> stocks;

    @Before
    public void init() {
        cashier = new Cashier("12_1234", "John Johnson", BigDecimal.valueOf(1421.512));
        stocks = new ArrayList<Stock>();
        stocks.add(new Stock("1233-312-3123", "Banana", BigDecimal.valueOf(1.25), 2, BigDecimal.valueOf(2.25), Category.Edible, LocalDate.now()));
        stocks.add(new Stock("1233-312-3123", "Screwdriver", BigDecimal.valueOf(1.25), 1, BigDecimal.valueOf(2.25), Category.NonEdible, LocalDate.now()));
    }

    @Test
    public void constructorShouldReturnValidObject() {
        assertEquals("12_1234", cashier.getId());
        assertEquals("John Johnson", cashier.getName());
        assertEquals(BigDecimal.valueOf(1421.512), cashier.getSalary());
    }

    @Test
    public void processPaymentSetEnterPaymentFieldSuccessfully() throws InvalidChangeException {
        cashier.processPayment(BigDecimal.valueOf(15.50), BigDecimal.valueOf(15.25), stocks);
        assertEquals(BigDecimal.valueOf(15.50), cashier.getCurrentPayment());
    }

    @Test
    public void processPaymentShouldThrowExceptionIfPaymentIsNotEnough() {
        assertThrows(InvalidChangeException.class,
                () -> {
                    cashier.processPayment(BigDecimal.valueOf(15.10), BigDecimal.valueOf(15.25), stocks);
                });
    }

    @Test
    public void processPaymentShouldCreateFileReceipt() throws InvalidChangeException {
        int numberOfFiles = 1;
        cashier.processPayment(BigDecimal.valueOf(15.50), BigDecimal.valueOf(15.25), stocks);
        List<String> textFiles = new ArrayList<String>();
        File dir = new File(".\\receipts");
        for (File file : dir.listFiles()) {
            if (file.getName().endsWith((".txt"))) {
                textFiles.add(file.getName());
            }
        }
        assertTrue(textFiles.size() > 0);
    }

    @Test
    public void settersShouldManipulateObjectSuccessfully() {
        cashier.setId("79_1423");
        assertEquals("79_1423", cashier.getId());

        cashier.setName("Johny Johny");
        assertEquals("Johny Johny", cashier.getName());

        cashier.setSalary(BigDecimal.valueOf(1723.42));
        assertEquals(BigDecimal.valueOf(1723.42), cashier.getSalary());
    }

    @Test
    public void invalidIdInConstructorShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    var cashier = new Cashier("12_Invalid", "John Johnson", BigDecimal.valueOf(1421.512));
                });
    }

    @Test
    public void invalidNameInConstructorShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    var cashier = new Cashier("12_1234", "J@hn John@123son", BigDecimal.valueOf(1421.512));
                });
    }

    @Test
    public void setInvalidIdShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    cashier.setId("Invalid Id");
                });
    }

    @Test
    public void setInvalidNameShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    cashier.setName("J@hn Bravo");
                });
    }

    @Test
    public void setInvalidSalaryShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    cashier.setSalary(BigDecimal.valueOf(-1.25));
                });
    }

}
