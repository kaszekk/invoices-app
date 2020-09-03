package pl.lukasz.service.generators;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import pl.lukasz.model.InvoiceEntry;
import pl.lukasz.model.Vat;

public class InvoiceEntryGenerator {

  private static Random random = new Random();

  private static InvoiceEntry getRandomEntry() {
    Long id = IdGenerator.getNextId();
    String productName = WordGenerator.getRandomWord();
    double quantity = random.nextInt(10);
    String unit = "szt.";
    BigDecimal price = BigDecimal.valueOf(random.nextInt(2000));
    Vat vatRate = Vat.RATE_23;
    BigDecimal nettValue = BigDecimal.valueOf(quantity).multiply(price);
    BigDecimal grossValue = nettValue.multiply(BigDecimal.valueOf(1 + vatRate.getValue())).setScale(2, RoundingMode.HALF_EVEN);
    return new InvoiceEntry(id, productName, quantity, unit, price, nettValue, grossValue, vatRate);
  }

  public static List<InvoiceEntry> getRandomEntries(int count) {
    List<InvoiceEntry> invoiceEntryList = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      invoiceEntryList.add(getRandomEntry());
    }
    return invoiceEntryList;
  }
}
