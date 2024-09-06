import { UiSchema } from '@genesislcap/foundation-forms';

export const createFormSchema: UiSchema = {
  "type": "VerticalLayout",
  "elements": [
    {
      "type": "Control",
      "label": "Counterparty Id",
      "scope": "#/properties/COUNTERPARTY_ID",
      "options": {
        "hidden": true
      }
    },
    {
      "type": "Control",
      "label": "Counterparty Name",
      "scope": "#/properties/COUNTERPARTY_NAME",
      "options": {}
    }
  ]
}
