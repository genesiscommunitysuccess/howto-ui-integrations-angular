import { UiSchema } from '@genesislcap/foundation-forms';

export const updateFormSchema: UiSchema = {
  "type": "VerticalLayout",
  "elements": [
    {
      "type": "Control",
      "label": "Instrument Id",
      "scope": "#/properties/INSTRUMENT_ID",
      "options": {
        "readonly": true
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
