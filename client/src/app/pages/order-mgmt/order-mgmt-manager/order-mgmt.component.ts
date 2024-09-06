import { Component, Input, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { getUser } from '@genesislcap/foundation-user';
import { ErrorMessageComponent } from '../../../components/error-message/error-message.component';
import { getViewUpdateRightComponent } from '../../../utils';
import { createFormSchema } from './order-mgmt.create.form.schema';
import { updateFormSchema } from './order-mgmt.update.form.schema';
import { columnDefs } from './order-mgmt.column.defs';

@Component({
  selector: 'app-order-mgmt-order-mgmt-manager',
  templateUrl: './order-mgmt.component.html',
  styleUrl: './order-mgmt.component.css',
  standalone: true,
  imports: [
    ErrorMessageComponent,
    CommonModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class OrderMgmtOrderMgmtManager {
  hasUserPermission = (permissionCode: string) => getViewUpdateRightComponent(getUser(), permissionCode);
  createFormSchema = createFormSchema;
  updateFormSchema = updateFormSchema;
  columnDefs = columnDefs;
  
}