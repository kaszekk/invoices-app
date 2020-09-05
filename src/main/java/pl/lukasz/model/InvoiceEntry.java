package pl.lukasz.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
@ApiModel(value = "Invoice entry")
@Entity
@SuppressWarnings("PMD.NullAssignment")
public final class InvoiceEntry {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "The id of invoice entry.", dataType = "Long", position = -1)
  private final Long id;

  @ApiModelProperty(value = "product name", example = "soap")
  private final String productName;

  @ApiModelProperty(value = "product quantity", example = "2")
  private final double quantity;

  @ApiModelProperty(value = "unit of quantity ie. kg, pc", example = "1")
  private final String unit;

  @ApiModelProperty(value = "product net price per unit", example = "29.99")
  private final BigDecimal price;

  @ApiModelProperty(value = "total net value = (net price per unit)*quantity ", example = "29.99")
  private final BigDecimal netValue;

  @ApiModelProperty(value = "total gross value (total net value after appropriate VAT rate applied)", example = "39.99")
  private final BigDecimal grossValue;

  @ApiModelProperty(value = "VAT rate to be applied to net value ie. 0.23 means 23% VAT", example = "RATE_23")
  private final Vat vatRate;

  InvoiceEntry() {
    this.id = null;
    this.productName = null;
    this.quantity = 0;
    this.unit = null;
    this.price = null;
    this.netValue = null;
    this.grossValue = null;
    this.vatRate = null;
  }
}
