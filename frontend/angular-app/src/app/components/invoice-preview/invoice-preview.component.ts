import {Component, Input, OnInit} from '@angular/core';
import {Invoice} from "../invoice/invoice";
import {Subscription} from "rxjs";
import {InvoiceService} from "../invoice/invoice-service";
import {InvoiceComponent} from "../invoice/invoice.component";

@Component({
  selector: 'app-invoice-preview',
  templateUrl: './invoice-preview.component.html',
  styleUrls: ['./invoice-preview.component.css']
})
export class InvoicePreviewComponent implements OnInit {
  @Input()
  invoices: Invoice[];

  constructor(private invoiceService: InvoiceService) {
  }

  ngOnInit(): void {
  }
}

