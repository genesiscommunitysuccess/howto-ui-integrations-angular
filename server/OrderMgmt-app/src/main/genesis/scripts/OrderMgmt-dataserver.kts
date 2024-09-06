/**
  * This file defines the data server queries for the application. Data server
  * will load the data defined here and expose APIs for the clients including
  * Genesis UI Components to present this data as well as keep it up to date as
  * the data set changes underneath.
  *
  * Data server queries also allow for the definition of dynamic columns as well
  * as rich access controls definitions.

  * Full documentation on dataserver may be found here >> https://learn.genesis.global/docs/server/data-server/introduction/

 */

dataServer {
  query("ALL_COUNTERPARTIES", COUNTERPARTY) {
    fields {
      COUNTERPARTY_ID
      COUNTERPARTY_NAME
    }
  }
  query("ALL_INSTRUMENTS", INSTRUMENT) {
    fields {
      INSTRUMENT_ID
      SYMBOL
    }
  }
  query("ALL_TRADES", TRADE) {
    fields {
      TRADE_ID
      TRADED_PRICE
      TRADED_QUANTITY
      ORDER_ID
    }
  }
  query("ALL_ORDERS", ORDER_VIEW) {
    fields {
      DIRECTION
      IS_AGENCY
      ORDER_ID
      QUANTITY
      PRICE
      ORDER_DATE
      COUNTERPARTY_ID
      INSTRUMENT_ID
      COUNTERPARTY_NAME
      SYMBOL
      AVG_PRICE
      QUANTITY_FILLED
      ORDER_DATETIME
    }
  }
  query("ALL_ORDER_TOTAL_BY_INSTS", ORDER_TOTAL_BY_INST_VIEW) {
    fields {
      SYMBOL
      TOTAL_QUANTITY_ORDERED
      INSTRUMENT_ID
    }
  }

  //TODO - add new queries or update existing queries to add authorization
  /**
    * You can add derived field like below
    * derivedField("FIELD_NAME", BOOLEAN) {
    *                 THIS_ENTITY.ATTR1 == "USD"
    *             }
    * You can add permission to the query by using permission codes like below
    * permissioning {
    *     // 'permission Code' list, users must have the permission to access the enclosing resource
    *     permissionCodes = listOf("PERMISSION1", "PERMISSION2")
    * }
    * Full documentation on permissions may be found here https://learn.genesis.global/docs/server/access-control/authorisation-overview/#authorisation  */

}
