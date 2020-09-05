package pl.lukasz.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@ApiModel(value = "Company")
public final class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "The id of company.", dataType = "Long", position = -1)
  private final Long id;

  @ApiModelProperty(value = "Invoice issuer", example = "InvoiceSoft Ltd.")
  private final String name;

  @ApiModelProperty(value = "Invoice issuer address", example = "Clock Street, 12-345, Invoicetown, Co. Wexford, Ireland")
  private final String address;

  @ApiModelProperty(value = "Tax id", example = "342-456-345")
  private final String taxId;

  @ApiModelProperty(value = "Account number", example = "45-56-5676")
  private final String accountNumber;

  @ApiModelProperty(value = "Phone number", example = "(12)345-456-887")
  private final String phoneNumber;

  @ApiModelProperty(value = "Email address", example = "jsmith@mail.com")
  private final String email;

}
