/**
 * System              : Genesis Business Library
 * Sub-System          : multi-pro-code-test Configuration
 * Version             : 1.0
 * Copyright           : (c) Genesis
 * Date                : 2022-03-18
 * Function : Provide system definition config for multi-pro-code-test.
 *
 * Modification History
 */
systemDefinition {
    global {
        item("STORAGE_STRATEGY", "LOCAL")
        item("LOCAL_STORAGE_FOLDER", "LOCAL_STORAGE")

        //List out the tables and view names that users can use to create notification rules
        item("NOTIFY_ENTITY_LIST", listOf("ORDER", "COUNTERPARTY", "ORDER_STATUS", "INSTRUMENT", "TRADE", "ORDER_TOTAL_BY_INST", "ORDER_VIEW", "ORDER_TOTAL_BY_INST_VIEW"))
    }

    systems {

    }

}