import { UiSchema } from '@genesislcap/foundation-forms';

export const updateFormSchema: UiSchema = {
  "type": "VerticalLayout",
  "elements": [
    {
      "type": "Control",
      "label": "Direction",
      "scope": "#/properties/DIRECTION",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Is Agency",
      "scope": "#/properties/IS_AGENCY",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Order Id",
      "scope": "#/properties/ORDER_ID",
      "options": {
        "readonly": true
      }
    },
    {
      "type": "Control",
      "label": "Quantity",
      "scope": "#/properties/QUANTITY",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Price",
      "scope": "#/properties/PRICE",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Order Date",
      "scope": "#/properties/ORDER_DATE",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Counterparty Id",
      "scope": "#/properties/COUNTERPARTY_ID",
      "options": {
        "allOptionsResourceName": "COUNTERPARTY",
        "valueField": "COUNTERPARTY_ID",
        "labelField": "COUNTERPARTY_ID"
      }
    },
    {
      "type": "Control",
      "label": "Instrument Id",
      "scope": "#/properties/INSTRUMENT_ID",
      "options": {
        "allOptionsResourceName": "INSTRUMENT",
        "valueField": "INSTRUMENT_ID",
        "labelField": "INSTRUMENT_ID"
      }
    },
    {
      "type": "Control",
      "label": "Order Datetime",
      "scope": "#/properties/ORDER_DATETIME",
      "options": {}
    }
  ]
}
