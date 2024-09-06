import { Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrderMgmtOrderMgmtManager } from './order-mgmt-manager';
import { OrderMgmtOrderTotalsByInstChart } from './order-totals-by-inst-chart';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-order-mgmt',
  standalone: true,
  imports: [
    OrderMgmtOrderMgmtManager,
    OrderMgmtOrderTotalsByInstChart,
    CommonModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  templateUrl: './order-mgmt.component.html',
  styleUrls: ['./order-mgmt.component.css'],
})
export class OrderMgmtComponent {
  environment = environment;
}
