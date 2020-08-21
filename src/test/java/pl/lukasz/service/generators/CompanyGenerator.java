package pl.lukasz.service.generators;

import java.util.Random;
import pl.lukasz.model.Company;

public class CompanyGenerator {

  private static final Random random = new Random();

  public static Company getRandomCompany() {
    Long id = IdGenerator.getNextId();
    String name = WordGenerator.getRandomWord();
    String address = WordGenerator.getRandomWord();
    String taxId = getRandomNumberAsString();
    String accountId = getRandomNumberAsString();
    String phoneNumber = getRandomNumberAsString();
    return new Company(id, name, address, taxId, accountId, phoneNumber, name + "@company.com");
  }

  public static Company getRandomCompanyWithSpecificId(Long id) {
    String name = WordGenerator.getRandomWord();
    String address = WordGenerator.getRandomWord();
    String taxId = getRandomNumberAsString();
    String accountId = getRandomNumberAsString();
    String phoneNumber = getRandomNumberAsString();
    return new Company(id, name, address, taxId, accountId, phoneNumber, name + "@company.com");
  }

  public static String getRandomNumberAsString() {
    return String.format("%05d%05d", random.nextInt(99999), random.nextInt(99999));
  }
}
