import { ColDef } from '@ag-grid-community/core';
import { getNumberFormatter, getDateFormatter } from '@genesislcap/foundation-utils';

export const columnDefs: ColDef[] = [
  {
    field: "DIRECTION",
  },
  {
    field: "IS_AGENCY",
  },
  {
    field: "ORDER_ID",
  },
  {
    field: "QUANTITY",
    valueFormatter: getNumberFormatter("0,0", null),
  },
  {
    field: "PRICE",
    valueFormatter: getNumberFormatter("0,0.00", null),
  },
  {
    field: "ORDER_DATE",
  },
  {
    field: "COUNTERPARTY_ID",
    hide: true,
  },
  {
    field: "INSTRUMENT_ID",
    hide: true,
  },
  {
    field: "COUNTERPARTY_NAME",
  },
  {
    field: "SYMBOL",
  },
  {
    field: "AVG_PRICE",
    valueFormatter: getNumberFormatter("0,0.00", null),
  },
  {
    field: "QUANTITY_FILLED",
    valueFormatter: getNumberFormatter("0,0", null),
  },
  {
    field: "ORDER_DATETIME",
  }
]
