package com.Market.Cashing;

import com.Market.Contracts.Fileable;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Receipt implements Fileable {
    String cashierName;
    LocalDate createdOn;
    String stockInformation;
    BigDecimal totalPrice;

    public Receipt(String cashierName, LocalDate createdOn, String stockInformation, BigDecimal totalPrice) {
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
    public void writeFile() {
        String fileName = String.format("receipt no_%s", UUID.randomUUID());
        String receiptsFolder = new File(".").getAbsolutePath() + "//receipts//";
        try (FileWriter fileWriter = new FileWriter(receiptsFolder + fileName + ".txt", true)) {
            fileWriter.write(this.toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("[Cashier_%s]\n", this.cashierName));
        sb.append("-------------------" + '\n');
        sb.append(String.format("[Stocks]") + '\n');
        sb.append("-------------------" + '\n');
        sb.append(this.stockInformation + '\n');
        sb.append(String.format("-------------------") + '\n');
        sb.append(String.format("[Total Price]") + '\n');
        sb.append("-------------------" + '\n');
        sb.append(String.format("$%s", this.totalPrice) + '\n');
        sb.append("-------------------" + '\n');
        sb.append(String.format("Date created: %s ", this.createdOn) + '\n');
        return sb.toString().trim();
    }
}
