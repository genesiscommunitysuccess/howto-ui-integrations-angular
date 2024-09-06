import { Component, Input, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { getUser } from '@genesislcap/foundation-user';
import { ErrorMessageComponent } from '../../../components/error-message/error-message.component';
import { getViewUpdateRightComponent } from '../../../utils';
import { createFormSchema } from './instrument-mgmt.create.form.schema';
import { updateFormSchema } from './instrument-mgmt.update.form.schema';
import { columnDefs } from './instrument-mgmt.column.defs';

@Component({
  selector: 'app-ref-data-instrument-mgmt-manager',
  templateUrl: './instrument-mgmt.component.html',
  styleUrl: './instrument-mgmt.component.css',
  standalone: true,
  imports: [
    ErrorMessageComponent,
    CommonModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class RefDataInstrumentMgmtManager {
  hasUserPermission = (permissionCode: string) => getViewUpdateRightComponent(getUser(), permissionCode);
  createFormSchema = createFormSchema;
  updateFormSchema = updateFormSchema;
  columnDefs = columnDefs;
  
}