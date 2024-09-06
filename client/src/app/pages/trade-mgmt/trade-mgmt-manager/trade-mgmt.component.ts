import { Component, Input, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { getUser } from '@genesislcap/foundation-user';
import { ErrorMessageComponent } from '../../../components/error-message/error-message.component';
import { getViewUpdateRightComponent } from '../../../utils';
import { createFormSchema } from './trade-mgmt.create.form.schema';
import { updateFormSchema } from './trade-mgmt.update.form.schema';
import { columnDefs } from './trade-mgmt.column.defs';

@Component({
  selector: 'app-trade-mgmt-trade-mgmt-manager',
  templateUrl: './trade-mgmt.component.html',
  styleUrl: './trade-mgmt.component.css',
  standalone: true,
  imports: [
    ErrorMessageComponent,
    CommonModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class TradeMgmtTradeMgmtManager {
  hasUserPermission = (permissionCode: string) => getViewUpdateRightComponent(getUser(), permissionCode);
  createFormSchema = createFormSchema;
  updateFormSchema = updateFormSchema;
  columnDefs = columnDefs;
  
}