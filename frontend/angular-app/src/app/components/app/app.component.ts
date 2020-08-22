import {Component, Injectable, OnInit} from '@angular/core';
import {Invoice} from "../invoice/invoice";
import {InvoiceService} from "../invoice/invoice-service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  invoices: Invoice[] = [];

  constructor(private invoiceService: InvoiceService) {
  }

  ngOnInit(): void {
    // this.invoiceService.getInvoice(2).subscribe(returnedInvoice => {
    //   this.invoice = returnedInvoice;
    // });

    this.invoiceService.getInvoices().subscribe(invoices => {
      this.invoices = invoices;
    });
  }
}

