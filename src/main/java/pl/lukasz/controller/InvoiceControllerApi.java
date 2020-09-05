package pl.lukasz.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.lukasz.model.Invoice;

@CrossOrigin
public interface InvoiceControllerApi {

  String CONTAINER_LIST = "List";
  String INTERNAL_SERVER_ERROR = "Internal server error.";

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get a single invoice", notes = "Gets an invoice by id", response = Invoice.class)
  @ApiImplicitParam(name = "id", value = "Only digits possible, e.g. 4321", example = "4321")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Invoice not found for passed id."),
      @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)})
  ResponseEntity<?> getInvoiceById(@PathVariable Long id);

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get all invoices", response = Invoice.class, responseContainer = CONTAINER_LIST)
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)})
  ResponseEntity<?> getAllInvoices();

  @GetMapping("/byDate")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get all invoices by dates", notes = "Gets all invoices issued between specified dates (inclusive) fromDate and toDate.",
      response = Invoice.class, responseContainer = CONTAINER_LIST)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "fromDate", value = "YYYY-MM-DD", example = "2020-03-23"),
      @ApiImplicitParam(name = "toDate", value = "YYYY-MM-DD", example = "2020-05-04")})
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 400, message = "Passed dates are invalid."),
      @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)})
  ResponseEntity<?> getInvoicesByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate);

  @GetMapping("/byBuyer")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get all invoices by buyer", notes = "Gets all invoices issued to specified buyer.", response = Invoice.class,
      responseContainer = CONTAINER_LIST)
  @ApiImplicitParam(name = "id", value = "Only digits possible, e.g. 4321", example = "4321")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 400, message = "Passed buyer id is invalid."),
      @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)})
  ResponseEntity<?> getInvoicesByBuyer(@RequestParam Long id);

  @GetMapping("/bySeller")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get all invoices by seller", notes = "Gets all invoices issued to specified seller.", response = Invoice.class,
      responseContainer = CONTAINER_LIST)
  @ApiImplicitParam(name = "id", value = "Only digits possible, e.g. 4321", example = "4321")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 400, message = "Passed seller id is invalid."),
      @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)})
  ResponseEntity<?> getInvoicesBySeller(@RequestParam Long id);

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Delete an invoice by id", notes = "Deletes invoice by specified id from database.")
  @ApiImplicitParam(name = "id", value = "Only digits possible, e.g. 7565", example = "7865")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Invoice not found for passed id."),
      @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)})
  ResponseEntity<?> deleteInvoice(@PathVariable Long id);

  @DeleteMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Delete ALL invoices", notes = "WARNING!!! This operation deletes ALL available invoices from database.")
  @ApiResponses({
      @ApiResponse(code = 204, message = "OK"),
      @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)})
  ResponseEntity<?> deleteAllInvoices();

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "When invoice id field is not set application saves the invoice to database as new invoice,"
      + " otherwise updates existing invoice.", response = Invoice.class)
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 400, message = "Passed invoice is invalid."),
      @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)})
  ResponseEntity<?> saveInvoice(@RequestBody(required = false) Invoice invoice);
}
