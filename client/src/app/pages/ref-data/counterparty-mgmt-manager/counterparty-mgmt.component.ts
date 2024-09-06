import { Component, Input, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { getUser } from '@genesislcap/foundation-user';
import { ErrorMessageComponent } from '../../../components/error-message/error-message.component';
import { getViewUpdateRightComponent } from '../../../utils';
import { createFormSchema } from './counterparty-mgmt.create.form.schema';
import { updateFormSchema } from './counterparty-mgmt.update.form.schema';
import { columnDefs } from './counterparty-mgmt.column.defs';

@Component({
  selector: 'app-ref-data-counterparty-mgmt-manager',
  templateUrl: './counterparty-mgmt.component.html',
  styleUrl: './counterparty-mgmt.component.css',
  standalone: true,
  imports: [
    ErrorMessageComponent,
    CommonModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class RefDataCounterpartyMgmtManager {
  hasUserPermission = (permissionCode: string) => getViewUpdateRightComponent(getUser(), permissionCode);
  createFormSchema = createFormSchema;
  updateFormSchema = updateFormSchema;
  columnDefs = columnDefs;
  
}