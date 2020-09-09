package pl.lukasz.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.lukasz.controller.InvoiceControllerApi.FROM_DATE;
import static pl.lukasz.controller.InvoiceControllerApi.TO_DATE;
import static pl.lukasz.service.InvoiceService.FROM_DATE_CANNOT_BE_AFTER_TO_DATE;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.lukasz.model.Invoice;
import pl.lukasz.repository.InvoiceRepository;
import pl.lukasz.service.generators.InvoiceGenerator;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

  public static final String IS_MARKED_NON_NULL_BUT_IS_NULL = " is marked non-null but is null";

  @InjectMocks
  private InvoiceService invoiceService;

  @Mock
  private InvoiceRepository invoiceRepository;
  private Invoice invoice1;
  private Invoice invoice2;

  @BeforeEach
  void setUp() {
    invoice1 = InvoiceGenerator.getRandomInvoice();
    invoice2 = InvoiceGenerator.getRandomInvoice();
  }

  @Test
  void shouldThrowExceptionForNullDatabase() {
    final Throwable exception = assertThrows(NullPointerException.class, () -> new InvoiceService(null));
    assertEquals("invoiceRepository" + IS_MARKED_NON_NULL_BUT_IS_NULL, exception.getMessage());
  }

  @Test
  void shouldReturnAllInvoices() {
    // Given
    List<Invoice> expectedInvoiceList = List.of(invoice1, invoice2);
    when(invoiceRepository.findAll()).thenReturn(expectedInvoiceList);

    // When
    Collection<Invoice> resultInvoiceList = invoiceService.getAllInvoices();

    // Then
    assertEquals(expectedInvoiceList, resultInvoiceList);
    verify(invoiceRepository).findAll();
  }

  @Test
  void shouldReturnAllInvoicesForGivenBuyer() {
    // Given
    List<Invoice> invoiceList = List.of(invoice1, invoice2, invoice1);
    List<Invoice> expectedInvoiceList = List.of(invoice1, invoice1);
    when(invoiceRepository.findAll()).thenReturn(invoiceList);

    // When
    Collection<Invoice> resultInvoiceList = invoiceService.getAllInvoicesByBuyer(invoice1.getBuyer().getId());

    // Then
    assertEquals(expectedInvoiceList, resultInvoiceList);
    verify(invoiceRepository).findAll();
  }

  @Test
  void shouldThrowExceptionForNullIdWhileGettingInvoicesByBuyer() {
    final Throwable exception = assertThrows(NullPointerException.class, () -> invoiceService.getAllInvoicesByBuyer(null));
    assertEquals("id" + IS_MARKED_NON_NULL_BUT_IS_NULL, exception.getMessage());
  }

  @Test
  void shouldReturnAllInvoicesForGivenSeller() {
    // Given
    List<Invoice> invoiceList = List.of(invoice1, invoice2, invoice1);
    List<Invoice> expectedInvoiceList = List.of(invoice1, invoice1);
    when(invoiceRepository.findAll()).thenReturn(invoiceList);

    // When
    Collection<Invoice> resultInvoiceList = invoiceService.getAllInvoicesBySeller(invoice1.getSeller().getId());

    // Then
    assertEquals(expectedInvoiceList, resultInvoiceList);
    verify(invoiceRepository).findAll();
  }

  @Test
  void shouldThrowExceptionForNullIdWhileGettingInvoicesBySeller() {
    final Throwable exception = assertThrows(NullPointerException.class, () -> invoiceService.getAllInvoicesBySeller(null));
    assertEquals("id" + IS_MARKED_NON_NULL_BUT_IS_NULL, exception.getMessage());
  }

  @Test
  void shouldReturnAllInvoicesFromGivenDateRage() {
    // Given
    Invoice invoice3 = new Invoice(invoice1.getId(), invoice1.getNumber(), LocalDate.of(2016, 9, 13), invoice1.getDueDate(), invoice1.getSeller(),
        invoice1.getBuyer(), invoice1.getEntries());
    Invoice invoice4 = new Invoice(invoice2.getId(), invoice2.getNumber(), LocalDate.of(2017, 5, 23), invoice2.getDueDate(), invoice2.getSeller(),
        invoice2.getBuyer(), invoice1.getEntries());
    List<Invoice> invoiceList = List.of(invoice3, invoice4, invoice3);
    List<Invoice> expectedInvoiceList = List.of(invoice3, invoice3);

    when(invoiceRepository.findAll()).thenReturn(invoiceList);

    // When
    Collection<Invoice> resultInvoiceList = invoiceService.getAllInvoicesByDate(LocalDate.of(2016, 1, 1), LocalDate.of(2016, 12, 31));

    // Then
    assertEquals(expectedInvoiceList, resultInvoiceList);
    verify(invoiceRepository).findAll();
  }

  @Test
  void shouldThrowExceptionForNullAsFromDate() {
    final Throwable exception = assertThrows(NullPointerException.class,
        () -> invoiceService.getAllInvoicesByDate(null, LocalDate.of(2018, 4, 11)));
    assertEquals(FROM_DATE + IS_MARKED_NON_NULL_BUT_IS_NULL, exception.getMessage());
  }

  @Test
  void shouldThrowExceptionForNullAsToDate() {
    final Throwable exception = assertThrows(NullPointerException.class,
        () -> invoiceService.getAllInvoicesByDate(LocalDate.of(2016, 4, 21), null));
    assertEquals(TO_DATE + IS_MARKED_NON_NULL_BUT_IS_NULL, exception.getMessage());
  }

  @Test
  void shouldThrowExceptionForInvalidDates() {
    final Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> invoiceService.getAllInvoicesByDate(LocalDate.of(2017, 1, 20), LocalDate.of(2016, 6, 13)));
    assertEquals(FROM_DATE_CANNOT_BE_AFTER_TO_DATE, exception.getMessage());
  }

  @Test
  void shouldReturnInvoice() {
    // Given
    when(invoiceRepository.findById(1L)).thenReturn(Optional.ofNullable(invoice1));

    // When
    Optional<Invoice> resultInvoice = invoiceService.getInvoice(1L);

    // Then
    assertTrue(resultInvoice.isPresent());
    assertEquals(invoice1, resultInvoice.get());
  }

  @Test
  void shouldThrowExceptionForNullAsIdWhileGettingInvoice() {
    final Throwable exception = assertThrows(NullPointerException.class, () -> invoiceService.getInvoice(null));
    assertEquals("id" + IS_MARKED_NON_NULL_BUT_IS_NULL, exception.getMessage());
  }

  @Test
  void shouldSaveInvoice() {
    // Given
    when(invoiceRepository.save(invoice1)).thenReturn(invoice2);

    // When
    Invoice resultInvoice = invoiceService.saveInvoice(invoice1);

    // Then
    assertEquals(invoice2, resultInvoice);
    verify(invoiceRepository).save(invoice1);
  }

  @Test
  void shouldThrowExceptionForNullAsInvoiceWhileSavingNullInvoice() {
    final Throwable exception = assertThrows(NullPointerException.class, () -> invoiceService.saveInvoice(null));
    assertEquals("invoice" + IS_MARKED_NON_NULL_BUT_IS_NULL, exception.getMessage());
  }

  @Test
  void shouldDeleteInvoice() {
    // When
    invoiceService.deleteInvoice(1L);

    // Then
    verify(invoiceRepository).deleteById(1L);
  }

  @Test
  void shouldThrowExceptionForNullAsIdWhileDeletingInvoice() {
    final Throwable exception = assertThrows(NullPointerException.class, () -> invoiceService.deleteInvoice(null));
    assertEquals("id" + IS_MARKED_NON_NULL_BUT_IS_NULL, exception.getMessage());
  }
}
