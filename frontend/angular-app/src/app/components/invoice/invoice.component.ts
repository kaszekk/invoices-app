import {Component, Input, OnInit} from '@angular/core';
import {Invoice} from "./invoice";
import {MockInvoice} from "../../helpers/MockInvoice";

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {
  @Input() invoice: Invoice;

  constructor() {
  }

  ngOnInit(): void {
  }

}
