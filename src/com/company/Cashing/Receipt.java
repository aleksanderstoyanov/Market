package com.company.Cashing;

import com.company.Contracts.Fileable;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Receipt implements Fileable {
    int receiptNo;
    String cashierName;
    LocalDate createdOn;
    String stockInformation;
    BigDecimal totalPrice;

    public Receipt(int receiptNo, String cashierName, LocalDate createdOn, String stockInformation, BigDecimal totalPrice) {
        this.receiptNo = receiptNo;
        this.cashierName = cashierName;
        this.createdOn = createdOn;
        this.stockInformation = stockInformation;
        this.totalPrice = totalPrice;
    }

    @Override
    public void readFile(String path) {
        try (FileReader fileReader = new FileReader(path)) {
            int ch;
            while ((ch = fileReader.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void writeFile(int id) {
        String path = String.format("receipt no_%s", id);
        try (FileWriter fileWriter = new FileWriter("path")) {
            fileWriter.write(this.toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
