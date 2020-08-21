import {Component, Input, OnInit} from '@angular/core';
import {InvoiceEntry} from "../invoice/invoice";

@Component({
  selector: 'app-invoice-entry',
  templateUrl: './invoice-entry.component.html',
  styleUrls: ['./invoice-entry.component.css']
})
export class InvoiceEntryComponent implements OnInit {
  @Input()
  invoiceEntry: InvoiceEntry;

  constructor() {
  }

  ngOnInit(): void {
  }

}
