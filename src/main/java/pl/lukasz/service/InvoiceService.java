package pl.lukasz.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.lukasz.model.Invoice;
import pl.lukasz.repository.InvoiceRepository;

@Slf4j
@AllArgsConstructor
@Service
public class InvoiceService {

  private final InvoiceRepository invoiceRepository;

  public Collection<Invoice> getAllInvoices() {
    log.debug("Getting all invoices");
    return invoiceRepository.findAll();
  }

  public Collection<Invoice> getAllInvoicesByDate(@NonNull LocalDate fromDate, @NonNull LocalDate toDate) {
    if (fromDate.isAfter(toDate)) {
      String message = "fromDate cannot be after toDate.";
      log.error(message);
      throw new IllegalArgumentException(message);
    }
    log.debug("Getting all invoices by dates: from {} to {}", fromDate, toDate);
    // TODO: 20/08/2020 try to build query to get result straight from database
    return invoiceRepository.findAll()
        .stream()
        .filter(invoice -> (invoice.getIssuedDate().compareTo(fromDate) >= 0 && invoice.getIssuedDate().compareTo(toDate) <= 0))
        .collect(Collectors.toList());
  }

  public Collection<Invoice> getAllInvoicesByBuyer(@NonNull Long id) {
    log.debug("Getting all invoices by buyer: {}", id);
    return invoiceRepository.findAll()
        .stream()
        .filter(invoice -> (invoice.getBuyer().getId().equals(id)))
        .collect(Collectors.toList());
  }

  public Collection<Invoice> getAllInvoicesBySeller(@NonNull Long id) {
    log.debug("Getting all invoices by seller: {}", id);
    return invoiceRepository.findAll()
        .stream()
        .filter(invoice -> (invoice.getSeller().getId().equals(id)))
        .collect(Collectors.toList());
  }

  public Optional<Invoice> getInvoice(@NonNull Long id) {
    return invoiceRepository.findById(id);
  }

  public Invoice saveInvoice(@NonNull Invoice invoice) {
    log.debug("Saving invoice: {}", invoice);
    return invoiceRepository.save(invoice);
  }

  public void deleteInvoice(@NonNull Long id) {
    log.debug("Deleting invoice by id: {}", id);
    invoiceRepository.deleteById(id);
  }

  public void deleteAllInvoices() {
    log.debug("Deleting all invoices");
    invoiceRepository.deleteAll();
  }
}
