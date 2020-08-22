import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './components/app/app.component';
import {InvoiceComponent} from './components/invoice/invoice.component';
import {InvoiceEntryComponent} from './components/invoice-entry/invoice-entry.component';
import {CompanyComponent} from './components/company/company.component';
import {BuyerComponent} from './components/buyer/buyer.component';
import {SellerComponent} from './components/seller/seller.component';
import {InvoiceService} from "./components/invoice/invoice-service";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {InvoicePreviewComponent} from './components/invoice-preview/invoice-preview.component';


@NgModule({
  declarations: [
    AppComponent,
    InvoiceComponent,
    InvoiceEntryComponent,
    CompanyComponent,
    BuyerComponent,
    SellerComponent,
    InvoicePreviewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    InvoiceService
  ],

  bootstrap: [AppComponent]
})
export class AppModule {
}
