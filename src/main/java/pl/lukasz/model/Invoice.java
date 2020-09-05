package pl.lukasz.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
@ApiModel(value = "Invoice")
@Entity
@SuppressWarnings("PMD.NullAssignment")
public final class Invoice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "The id of invoice.", example = "1234", dataType = "Long", position = -1)
  private final Long id;

  @ApiModelProperty(value = "invoice number", example = "2019/03/2")
  private final String number;

  @ApiModelProperty(value = "Invoice issue date", example = "2019-03-12")
  private final LocalDate issuedDate;

  @ApiModelProperty(value = "Invoice due date", example = "2019-04-12")
  private final LocalDate dueDate;

  @ManyToOne(cascade = CascadeType.ALL)
  private final Company seller;

  @ManyToOne(cascade = CascadeType.ALL)
  private final Company buyer;

  @ManyToMany(cascade = CascadeType.ALL)
  private final List<InvoiceEntry> entries;

  Invoice() {
    this.id = null;
    this.number = null;
    this.issuedDate = null;
    this.dueDate = null;
    this.seller = null;
    this.buyer = null;
    this.entries = null;
  }
}
