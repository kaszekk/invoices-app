import {Component, Input, OnInit} from '@angular/core';
import {Company} from "../invoice/invoice";

@Component({
  selector: 'app-seller',
  templateUrl: './seller.component.html',
  styleUrls: ['./seller.component.css']
})
export class SellerComponent implements OnInit {
  @Input()
  seller: Company;

  constructor() {
  }

  ngOnInit(): void {
  }

}
