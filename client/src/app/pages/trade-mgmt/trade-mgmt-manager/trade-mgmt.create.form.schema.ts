import { UiSchema } from '@genesislcap/foundation-forms';

export const createFormSchema: UiSchema = {
  "type": "VerticalLayout",
  "elements": [
    {
      "type": "Control",
      "label": "Trade Id",
      "scope": "#/properties/TRADE_ID",
      "options": {
        "hidden": true
      }
    },
    {
      "type": "Control",
      "label": "Traded Price",
      "scope": "#/properties/TRADED_PRICE",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Traded Quantity",
      "scope": "#/properties/TRADED_QUANTITY",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Order Id",
      "scope": "#/properties/ORDER_ID",
      "options": {
        "allOptionsResourceName": "ORDER",
        "valueField": "ORDER_ID",
        "labelField": "ORDER_ID"
      }
    }
  ]
}
