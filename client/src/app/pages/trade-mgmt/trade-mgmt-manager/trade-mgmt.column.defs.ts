import { ColDef } from '@ag-grid-community/core';
import { getNumberFormatter, getDateFormatter } from '@genesislcap/foundation-utils';

export const columnDefs: ColDef[] = [
  {
    field: "TRADE_ID",
  },
  {
    field: "TRADED_PRICE",
    valueFormatter: getNumberFormatter("0,0.00", null),
  },
  {
    field: "TRADED_QUANTITY",
    valueFormatter: getNumberFormatter("0,0", null),
  },
  {
    field: "ORDER_ID",
    hide: true,
  }
]
