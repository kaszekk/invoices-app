export class Invoice {
  id: number;
  number: string;
  issuedDate: Date;
  dueDate: Date;
  seller: Company;
  buyer: Company;
  invoiceEntries: InvoiceEntry[] = [];
}

export class InvoiceEntry {
  id: number;
  productName: string;
  quantity: number;
  unit: string;
  price: number;
  netValue: number;
  grossValue: number;
  vatRate: Vat;
}

export class Company {
  id: number;
  name: string;
  address: string;
  taxId: string;
  accountNumber: string;
  phoneNumber: string;
  email: string;
}

export enum Vat {
  RATE_0,
  RATE_5,
  RATE_8,
  RATE_23
}
