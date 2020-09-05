package pl.lukasz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukasz.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
