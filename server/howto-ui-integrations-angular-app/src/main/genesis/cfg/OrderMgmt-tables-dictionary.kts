/**
  * This file defines the entities (or tables) for the application.  
  * Entities aggregation a selection of the universe of fields defined in 
  * {app-name}-fields-dictionary.kts file into a business entity.  
  *
  * Note: indices defined here control the APIs available to the developer.
  * For example, if an entity requires lookup APIs by one or more of its attributes, 
  * be sure to define either a unique or non-unique index.

  * Full documentation on tables may be found here >> https://docs.genesis.global/docs/develop/server-capabilities/data-model/

 */

tables {
  table(name = "ORDER", id = 11_000, audit = details(id = 11_500, sequence = "OA")) {
    field("ORDER_ID", LONG).autoIncrement()
    field("COUNTERPARTY_ID", LONG).notNull()
    field("DIRECTION", ENUM("SELL","SHORT_SELL","BUY")).default("BUY").notNull()
    field("INSTRUMENT_ID", LONG).notNull()
    field("IS_AGENCY", BOOLEAN).default(false).notNull()
    field("ORDER_DATE", DATE).notNull()
    field("ORDER_DATETIME", DATETIME).notNull()
    field("PRICE", DOUBLE).notNull()
    field("QUANTITY", INT).notNull()

    primaryKey("ORDER_ID")

  }
  table(name = "COUNTERPARTY", id = 11_001, audit = details(id = 11_501, sequence = "CA")) {
    field("COUNTERPARTY_ID", LONG).autoIncrement()
    field("COUNTERPARTY_NAME", STRING).notNull()

    primaryKey("COUNTERPARTY_ID")

  }
  table(name = "ORDER_STATUS", id = 11_002, audit = details(id = 11_502, sequence = "OS")) {
    field("AVG_PRICE", DOUBLE)
    field("ORDER_ID", LONG).notNull()
    field("QUANTITY_FILLED", INT)

    primaryKey("ORDER_ID")

  }
  table(name = "INSTRUMENT", id = 11_003, audit = details(id = 11_503, sequence = "IA")) {
    field("INSTRUMENT_ID", LONG).autoIncrement()
    field("SYMBOL", STRING).notNull()

    primaryKey("INSTRUMENT_ID")

  }
  table(name = "TRADE", id = 11_004, audit = details(id = 11_504, sequence = "TA")) {
    field("TRADE_ID", LONG).autoIncrement()
    field("ORDER_ID", LONG).notNull()
    field("TRADED_PRICE", DOUBLE).notNull()
    field("TRADED_QUANTITY", INT).notNull()

    primaryKey("TRADE_ID")

    indices {
      // auto-generated index needed for consolidator index scan
      nonUnique("ORDER_ID")
    }
  }
  table(name = "ORDER_TOTAL_BY_INST", id = 11_005, audit = details(id = 11_505, sequence = "OT")) {
    field("INSTRUMENT_ID", LONG).notNull()
    field("TOTAL_QUANTITY_ORDERED", INT)

    primaryKey("INSTRUMENT_ID")

  }

  // TODO 2. Add further tables you wish to add to the application here. See the comments at the top of this file for learning and guidance.
}
