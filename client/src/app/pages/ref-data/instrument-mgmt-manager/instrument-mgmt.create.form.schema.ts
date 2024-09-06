import { UiSchema } from '@genesislcap/foundation-forms';

export const createFormSchema: UiSchema = {
  "type": "VerticalLayout",
  "elements": [
    {
      "type": "Control",
      "label": "Instrument Id",
      "scope": "#/properties/INSTRUMENT_ID",
      "options": {
        "hidden": true
      }
    },
    {
      "type": "Control",
      "label": "Symbol",
      "scope": "#/properties/SYMBOL",
      "options": {}
    }
  ]
}
