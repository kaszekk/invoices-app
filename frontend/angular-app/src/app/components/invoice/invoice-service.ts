import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable, Subject} from "rxjs";
import {Invoice} from "./invoice";
import {ServiceBase} from "../../helpers/service-base";

const servicePath = 'invoices';

@Injectable({
  providedIn: 'root'
})

export class InvoiceService extends ServiceBase {

  constructor(private http: HttpClient) {
    super(http);
  }

  public getInvoice(id: number): Observable<Invoice> {
    return this.http.get<Invoice>(ServiceBase.apiUrl(servicePath, id));
  }

  public getInvoices(): Observable<Invoice[]> {
    return this.http.get<Invoice[]>(servicePath);
  }
}
