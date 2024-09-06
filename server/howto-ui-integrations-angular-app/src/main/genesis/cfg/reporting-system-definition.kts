/**
 * System              : Genesis Business Library
 * Sub-System          : reporting Configuration
 * Version             : 1.0
 * Copyright           : (c) Genesis
 * Date                : 25 January 2022
 * Function : Provide system definition config for reporting.
 *
 * Modification History
 */
systemDefinition {
    global {

        //List out the datasource names that users can use to create reports
        item("REPORTING_DATASOURCE_LIST", listOf("ALL_COUNTERPARTIES", "ALL_INSTRUMENTS", "ALL_TRADES", "ALL_ORDERS", "ALL_ORDER_TOTAL_BY_INSTS", "ORDER", "ORDER_AUDIT", "COUNTERPARTY", "COUNTERPARTY_AUDIT", "ORDER_STATUS", "ORDER_STATUS_AUDIT", "INSTRUMENT", "INSTRUMENT_AUDIT", "TRADE", "TRADE_AUDIT", "ORDER_TOTAL_BY_INST", "ORDER_TOTAL_BY_INST_AUDIT", "ORDER_VIEW", "ORDER_TOTAL_BY_INST_VIEW"))
    }

    systems {

    }

}