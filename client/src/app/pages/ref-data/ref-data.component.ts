import { Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RefDataInstrumentMgmtManager } from './instrument-mgmt-manager';
import { RefDataCounterpartyMgmtManager } from './counterparty-mgmt-manager';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-ref-data',
  standalone: true,
  imports: [
    RefDataInstrumentMgmtManager,
    RefDataCounterpartyMgmtManager,
    CommonModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  templateUrl: './ref-data.component.html',
  styleUrls: ['./ref-data.component.css'],
})
export class RefDataComponent {
  environment = environment;
}
