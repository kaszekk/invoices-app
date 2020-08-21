import {Component, Injectable, OnInit} from '@angular/core';
import {MockInvoice} from "./helpers/MockInvoice";
import {Invoice} from "./components/invoice/invoice";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-app';
  mockInvoice: Invoice = MockInvoice.getMockInvoice();
}

