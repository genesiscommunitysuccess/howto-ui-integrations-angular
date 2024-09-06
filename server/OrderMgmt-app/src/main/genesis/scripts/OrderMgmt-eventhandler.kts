/**
  * This file defines the event handler APIs. These APIs (modeled after CQRS
  * commands) allow callers to manipulate the data in the database. By default,
  * insert, update and delete event handlers (or commands) have been created.
  * These result in the data being written to the database and messages to be
  * published for the rest of the platform to by notified.
  * 
  * Custom event handlers may be added to extend the functionality of the
  * application as well as custom logic added to existing event handlers.
  *
  * The following objects are visible in each eventhandler
  * `event.details` which holds the entity that this event handler is acting upon
  * `entityDb` which is database object used to perform INSERT, MODIFY and UPDATE the records
  * Full documentation on event handler may be found here >> https://learn.genesis.global/docs/server/event-handler/introduction/

 */

eventHandler {
  eventHandler<Order>("ORDER_INSERT", transactional = true) {
    onCommit { event ->
      val details = event.details
      val insertedRow = entityDb.insert(details)
      // return an ack response which contains a list of record IDs
      ack(listOf(mapOf(
        "ORDER_ID" to insertedRow.record.orderId,
      )))
    }
  }
  eventHandler<Order>("ORDER_MODIFY", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.modify(details)
      ack()
    }
  }
  eventHandler<Order.ById>("ORDER_DELETE", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.delete(details)
      ack()
    }
  }
  eventHandler<Counterparty>("COUNTERPARTY_INSERT", transactional = true) {
    onCommit { event ->
      val details = event.details
      val insertedRow = entityDb.insert(details)
      // return an ack response which contains a list of record IDs
      ack(listOf(mapOf(
        "COUNTERPARTY_ID" to insertedRow.record.counterpartyId,
      )))
    }
  }
  eventHandler<Counterparty>("COUNTERPARTY_MODIFY", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.modify(details)
      ack()
    }
  }
  eventHandler<Counterparty.ById>("COUNTERPARTY_DELETE", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.delete(details)
      ack()
    }
  }
  eventHandler<OrderStatus>("ORDER_STATUS_INSERT", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.insert(details)
      ack()
    }
  }
  eventHandler<OrderStatus>("ORDER_STATUS_MODIFY", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.modify(details)
      ack()
    }
  }
  eventHandler<OrderStatus.ByOrderId>("ORDER_STATUS_DELETE", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.delete(details)
      ack()
    }
  }
  eventHandler<Instrument>("INSTRUMENT_INSERT", transactional = true) {
    onCommit { event ->
      val details = event.details
      val insertedRow = entityDb.insert(details)
      // return an ack response which contains a list of record IDs
      ack(listOf(mapOf(
        "INSTRUMENT_ID" to insertedRow.record.instrumentId,
      )))
    }
  }
  eventHandler<Instrument>("INSTRUMENT_MODIFY", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.modify(details)
      ack()
    }
  }
  eventHandler<Instrument.ById>("INSTRUMENT_DELETE", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.delete(details)
      ack()
    }
  }
  eventHandler<Trade>("TRADE_INSERT", transactional = true) {
    onCommit { event ->
      val details = event.details
      val insertedRow = entityDb.insert(details)
      // return an ack response which contains a list of record IDs
      ack(listOf(mapOf(
        "TRADE_ID" to insertedRow.record.tradeId,
      )))
    }
  }
  eventHandler<Trade>("TRADE_MODIFY", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.modify(details)
      ack()
    }
  }
  eventHandler<Trade.ById>("TRADE_DELETE", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.delete(details)
      ack()
    }
  }
  eventHandler<OrderTotalByInst>("ORDER_TOTAL_BY_INST_INSERT", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.insert(details)
      ack()
    }
  }
  eventHandler<OrderTotalByInst>("ORDER_TOTAL_BY_INST_MODIFY", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.modify(details)
      ack()
    }
  }
  eventHandler<OrderTotalByInst.ByInstrumentId>("ORDER_TOTAL_BY_INST_DELETE", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.delete(details)
      ack()
    }
  }

  //TODO - add new or customize event handlers to add validation, access permission checks
  /**
    * If you want to provide some validation before the action, you need to have an onValidate block before the onCommit. The last value of the code block must always be the return message type.
    * eventHandler<THIS_ENTITY>(name = "THIS_ENTITY_INSERT") {
    *      onValidate { event ->
    *          val thisEntity = event.details
    *          require(thisEntity.name != null) { "ThisEntity should have a name" }
    *          ack()
    *      }
    *      onCommit { event ->
    *          ...
    *      }
    *  }
    * You can add permission to the query by using permission codes like below
    * permissioning {
    *     // 'permission Code' list, users must have the permission to access the enclosing resource
    *     permissionCodes = listOf("PERMISSION1", "PERMISSION2")
    * }
    * Full documentation on permissions may be found here https://learn.genesis.global/docs/server/access-control/authorisation-overview/#authorisation
    */
}
