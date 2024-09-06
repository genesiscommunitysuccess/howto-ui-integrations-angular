/**
  * This file defines the views for this application. Views provide a way to
  * create a view across entities which are more friendly, for example joining a
  * trade to an instrument to provide the details of the instrument for
  * presentation next to the trade.
  *
  * Views also allow for the addition of dynamic columns calculated in real time.
  * 
  * Views may be used across the system including as inputs to consolidators,
  * expose as APIs in the request response server as snapshots of data or as real
  * time ticking APIs in the data server.

  * Full documentation on views may be found here >> https://docs.genesis.global/docs/develop/server-capabilities/data-model/#views

 */

views {
  view("ORDER_VIEW", ORDER) {
    joins {
      //TODO Add custom joins here to other tables if needed
      /**
        * In below example backward join indicates that updates to the joined entity should also update values in this view
        * joining(OTHER_ENTITY, backwardsJoin = true) {
        *     on( THIS_ENTITY { THIS_ENTITY_FOREIGN_KEY } to OTHER_ENTITY { PRIMARY_KEY })
        * }
        */
      joining(COUNTERPARTY, backwardsJoin = true) {
        on(ORDER { COUNTERPARTY_ID } to COUNTERPARTY { COUNTERPARTY_ID })
      }
      joining(INSTRUMENT, backwardsJoin = true) {
        on(ORDER { INSTRUMENT_ID } to INSTRUMENT { INSTRUMENT_ID })
      }
      joining(ORDER_STATUS, backwardsJoin = true) {
        on(ORDER { ORDER_ID } to ORDER_STATUS { ORDER_ID })
      }
    }
    fields {
      ORDER.DIRECTION
      ORDER.IS_AGENCY
      ORDER.ORDER_ID
      ORDER.QUANTITY
      ORDER.PRICE
      ORDER.ORDER_DATE
      ORDER.COUNTERPARTY_ID
      ORDER.INSTRUMENT_ID
      COUNTERPARTY.COUNTERPARTY_NAME
      INSTRUMENT.SYMBOL
      ORDER_STATUS.AVG_PRICE
      ORDER_STATUS.QUANTITY_FILLED
      ORDER.ORDER_DATETIME
      //TODO - add custom derived fields here using the following syntax
      /**
        * You can expand the list of inputs from all joined entities to perform more  * complex calculations as needed
        *   derivedField("FIELD_NAME", DOUBLE) {
        *     withInput(THIS_ENTITY.ATTR1, OTHER_ENTITY.ATTR2) {
        *         attr1, attr2 -> attr1 * attr2
        *     }
        *   }
        *
        *   Find more : https://docs.genesis.global/docs/develop/server-capabilities/data-model/#viewsviews-advanced/#derived-fields
        */
    }
  }
  view("ORDER_TOTAL_BY_INST_VIEW", ORDER_TOTAL_BY_INST) {
    joins {
      //TODO Add custom joins here to other tables if needed
      /**
        * In below example backward join indicates that updates to the joined entity should also update values in this view
        * joining(OTHER_ENTITY, backwardsJoin = true) {
        *     on( THIS_ENTITY { THIS_ENTITY_FOREIGN_KEY } to OTHER_ENTITY { PRIMARY_KEY })
        * }
        */
      joining(INSTRUMENT, backwardsJoin = true) {
        on(ORDER_TOTAL_BY_INST { INSTRUMENT_ID } to INSTRUMENT { INSTRUMENT_ID })
      }
    }
    fields {
      INSTRUMENT.SYMBOL
      ORDER_TOTAL_BY_INST.TOTAL_QUANTITY_ORDERED
      ORDER_TOTAL_BY_INST.INSTRUMENT_ID
      //TODO - add custom derived fields here using the following syntax
      /**
        * You can expand the list of inputs from all joined entities to perform more  * complex calculations as needed
        *   derivedField("FIELD_NAME", DOUBLE) {
        *     withInput(THIS_ENTITY.ATTR1, OTHER_ENTITY.ATTR2) {
        *         attr1, attr2 -> attr1 * attr2
        *     }
        *   }
        *
        *   Find more : https://docs.genesis.global/docs/develop/server-capabilities/data-model/#viewsviews-advanced/#derived-fields
        */
    }
  }

  //TODO add additional views here
}
