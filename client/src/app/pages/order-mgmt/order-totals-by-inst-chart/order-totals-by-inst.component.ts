import { Component, Input, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { getUser } from '@genesislcap/foundation-user';
import { ErrorMessageComponent } from '../../../components/error-message/error-message.component';
import { getViewUpdateRightComponent } from '../../../utils';

@Component({
  selector: 'app-order-mgmt-order-totals-by-inst-chart',
  templateUrl: './order-totals-by-inst.component.html',
  styleUrl: './order-totals-by-inst.component.css',
  standalone: true,
  imports: [
    ErrorMessageComponent,
    CommonModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class OrderMgmtOrderTotalsByInstChart {
  hasUserPermission = (permissionCode: string) => getViewUpdateRightComponent(getUser(), permissionCode);
  chartConfig = { 
      "radius": 0.75,
      "angleField": "value",
      "colorField": "groupBy",
  };
  
}