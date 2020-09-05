package pl.lukasz.controller;

import io.swagger.annotations.Api;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.lukasz.model.Invoice;
import pl.lukasz.service.InvoiceService;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/invoices")
@Api(value = "/invoices", description = "Available operations for invoice application", tags = {"Invoices"})
public class InvoiceController implements InvoiceControllerApi {

  @NonNull
  private final InvoiceService invoiceService;

  @Override
  public ResponseEntity<?> getInvoiceById(@PathVariable Long id) {
    log.debug("Getting an invoice by id: {}", id);
    Optional<Invoice> invoice = invoiceService.getInvoice(id);
    if (invoice.isEmpty()) {
      log.error("An error occurred during getting an invoice, invoice not found for passed id: {}", id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.ok().body(invoice.get());
  }

  @Override
   public ResponseEntity<?> getAllInvoices() {
    log.debug("Getting all invoices");
    Collection<Invoice> invoices = invoiceService.getAllInvoices();
    return ResponseEntity.status(HttpStatus.OK).body(invoices);
  }

  @Override
  public ResponseEntity<?> getInvoicesByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
    if (fromDate == null) {
      String message = "fromDate parameter cannot be null.";
      log.error(message);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    if (toDate == null) {
      String message = "toDate parameter cannot be null.";
      log.error(message);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    if (fromDate.isAfter(toDate)) {
      String message = "fromDate cannot be after toDate.";
      log.error(message);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    log.debug("Getting all invoices by dates: from {} to {}", fromDate, toDate);
    Collection<Invoice> invoices = invoiceService.getAllInvoicesByDate(fromDate, toDate);
    return ResponseEntity.status(HttpStatus.OK).body(invoices);
  }

  @Override
  public ResponseEntity<?> getInvoicesByBuyer(@RequestParam Long id) {
    if (id == null) {
      String message = "Buyer id cannot be null.";
      log.error(message);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    log.debug("Getting all invoices by buyer: {}", id);
    Collection<Invoice> invoices = invoiceService.getAllInvoicesByBuyer(id);
    return ResponseEntity.status(HttpStatus.OK).body(invoices);
  }

  @Override
  public ResponseEntity<?> getInvoicesBySeller(@RequestParam Long id) {
    if (id == null) {
      String message = "Seller id cannot be null.";
      log.error(message);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    log.debug("Getting all invoices by seller: {}", id);
    Collection<Invoice> invoices = invoiceService.getAllInvoicesBySeller(id);
    return ResponseEntity.status(HttpStatus.OK).body(invoices);
  }

  @Override
  public ResponseEntity<?> deleteInvoice(@PathVariable Long id) {
    log.debug("Deleting invoice by id: {}", id);
    Optional<Invoice> invoice = invoiceService.getInvoice(id);
    if (invoice.isEmpty()) {
      log.error("An error occurred during deleting an invoice, invoice not found for passed id: {}", id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    invoiceService.deleteInvoice(id);
    return ResponseEntity.status(HttpStatus.OK).body(invoice.get());
  }

  @Override
  public ResponseEntity<?> deleteAllInvoices() {
    log.debug("Deleting all invoices");
    invoiceService.deleteAllInvoices();
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<?> saveInvoice(@RequestBody(required = false) Invoice invoice) {
    // TODO: 21/08/2020  add validation  against:  max length, empty string etc
    if (invoice == null) {
      String message = "Invoice cannot be null.";
      log.error(message);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    log.debug("Saving invoice: {}", invoice);
    Invoice savedInvoice = invoiceService.saveInvoice(invoice);
    return ResponseEntity.status(HttpStatus.OK).body(savedInvoice);
  }
}
