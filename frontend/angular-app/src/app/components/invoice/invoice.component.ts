import {Component, Input, OnInit} from '@angular/core';
import {Invoice} from "./invoice";
import {InvoiceService} from "./invoice-service";
import {Subject} from "rxjs";

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {
  @Input()
  invoice: Invoice;

  ngOnInit(): void {
  }

}

