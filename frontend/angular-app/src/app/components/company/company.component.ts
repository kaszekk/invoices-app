import {Component, Input, OnInit} from '@angular/core';
import {Company} from "../invoice/invoice";

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {
  @Input()
  company: Company;

  constructor() {
  }

  ngOnInit(): void {
  }

}
