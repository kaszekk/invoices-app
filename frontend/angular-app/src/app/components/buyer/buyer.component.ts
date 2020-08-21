import {Component, Input, OnInit} from '@angular/core';
import {Company} from "../invoice/invoice";

@Component({
  selector: 'app-buyer',
  templateUrl: './buyer.component.html',
  styleUrls: ['./buyer.component.css']
})
export class BuyerComponent implements OnInit {
  @Input()
  buyer: Company;

  constructor() {
  }

  ngOnInit(): void {
  }

}
