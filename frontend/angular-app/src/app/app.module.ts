import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InvoiceComponent } from './components/invoice/invoice.component';
import { InvoiceEntryComponent } from './components/invoice-entry/invoice-entry.component';
import { CompanyComponent } from './components/company/company.component';
import { BuyerComponent } from './components/buyer/buyer.component';
import { SellerComponent } from './components/seller/seller.component';

@NgModule({
  declarations: [
    AppComponent,
    InvoiceComponent,
    InvoiceEntryComponent,
    CompanyComponent,
    BuyerComponent,
    SellerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
