import {Company, Invoice, InvoiceEntry, Vat} from "../components/invoice/invoice";

export class MockInvoice {

 public static getMockInvoice(): { seller: Company; number: string; issuedDate: Date; dueDate: Date; id: number; invoiceEntries: { unit: string; quantity: number; price: number; netValue: number; grossValue: number; vatRate: Vat; id: number; productName: string }[]; buyer: Company } {
    const testSeller: Company =
      {
        id: 1,
        name: 'test seller',
        address: 'test address1',
        taxId: '123-5432-678',
        accountNumber: '45-67-343',
        phoneNumber: '888 456 327',
        email: ' testEmail@test1.com'
      }

    const testBuyer: Company =
      {
        id: 2,
        name: 'test buyer',
        address: 'test address2',
        taxId: '123-5432-678',
        accountNumber: '45-67-343',
        phoneNumber: '888 456 327',
        email: ' testEmail@test2.com'
      }

    const testInvoiceEntry: { unit: string; quantity: number; price: number; netValue: number; grossValue: number; vatRate: Vat; id: number; productName: string }[] =
      [
        {
          id: 2,
          productName: 'Apple',
          quantity: 3,
          unit: 'kg',
          price: 22,
          netValue: 22,
          grossValue: 22,
          vatRate: Vat.RATE_0,
        }
      ]

    return {
      id: 1,
      number: '123',
      issuedDate: new Date(),
      dueDate: new Date(),
      seller: testSeller,
      buyer: testBuyer,
      invoiceEntries: testInvoiceEntry,
    }

  }
}
