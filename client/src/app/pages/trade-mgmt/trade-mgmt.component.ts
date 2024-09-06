import { Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TradeMgmtTradeMgmtManager } from './trade-mgmt-manager';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-trade-mgmt',
  standalone: true,
  imports: [
    TradeMgmtTradeMgmtManager,
    CommonModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  templateUrl: './trade-mgmt.component.html',
  styleUrls: ['./trade-mgmt.component.css'],
})
export class TradeMgmtComponent {
  environment = environment;
}
