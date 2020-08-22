import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoiceCountComponent } from './invoice-preview.component';

describe('InvoicePreviewComponent', () => {
  let component: InvoiceCountComponent;
  let fixture: ComponentFixture<InvoiceCountComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvoiceCountComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvoiceCountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
