/**
  * This file tests the event handler APIs. 
  
  * The events: INSERT, MODIFY and DELETE are tested
  * Full documentation on event handler tests may be found here >> https://learn.genesis.global/docs/server/event-handler/testing/#simple-test

 */

import global.genesis.db.rx.entity.multi.AsyncEntityDb
import global.genesis.gen.dao.Counterparty
import global.genesis.gen.dao.Instrument
import global.genesis.gen.dao.Order
import global.genesis.gen.dao.OrderStatus
import global.genesis.gen.dao.OrderTotalByInst
import global.genesis.gen.dao.Trade
import global.genesis.gen.dao.enums.OrderMgmt.order.Direction
import global.genesis.message.core.event.EventReply
import global.genesis.testsupport.client.eventhandler.EventClientSync
import global.genesis.testsupport.jupiter.GenesisJunit
import global.genesis.testsupport.jupiter.ScriptFile
import global.genesis.testsupport.jupiter.assertedCast
import javax.inject.Inject
import kotlin.String
import kotlin.Unit
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.joda.time.DateTime.now
import org.joda.time.DateTime.parse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(GenesisJunit::class)
@ScriptFile("OrderMgmt-eventhandler.kts")
class EventHandlerTest {
  @Inject
  lateinit var client: EventClientSync

  @Inject
  lateinit var entityDb: AsyncEntityDb

  private val adminUser: String = "admin"

  @Test
  fun `test insert ORDER`(): Unit = runBlocking {
    val result = client.sendEvent(
      details = Order {
        counterpartyId = 1
        direction = Direction.BUY
        instrumentId = 1
        isAgency = false
        orderDate = now()
        orderDatetime = now()
        price = 0.1
        quantity = 1
      },
      messageType = "EVENT_ORDER_INSERT",
      userName = adminUser
    )
    result.assertedCast<EventReply.EventAck>()
    val order = entityDb.getBulk<Order>().toList()
    assertTrue(order.isNotEmpty())
  }

  @Test
  fun `test modify ORDER`(): Unit = runBlocking {
    val result = entityDb.insert(
      Order {
        counterpartyId = 1
        direction = Direction.BUY
        instrumentId = 1
        isAgency = false
        orderDate = now()
        orderDatetime = now()
        price = 0.1
        quantity = 1
      }
    )
    val orderIdValue = result.record.orderId
    val modifyResult = client.sendEvent(
      details = Order {
        counterpartyId = 2
        direction = Direction.SELL
        instrumentId = 2
        isAgency = true
        orderDate = parse("2024-01-01T00:00:00.000Z")
        orderDatetime = parse("2024-01-01T00:00:00.000Z")
        orderId = orderIdValue
        price = 0.2
        quantity = 2
      },
      messageType = "EVENT_ORDER_MODIFY",
      userName = adminUser
    )
    modifyResult.assertedCast<EventReply.EventAck>()
    val modifiedRecord = entityDb.get(Order.ById(orderIdValue))
    assertEquals(2, modifiedRecord?.counterpartyId)
    assertEquals(Direction.SELL, modifiedRecord?.direction)
    assertEquals(2, modifiedRecord?.instrumentId)
    assertEquals(true, modifiedRecord?.isAgency)
    assertEquals(0, parse("2024-01-01T00:00:00.000Z").compareTo(modifiedRecord?.orderDate))
    assertEquals(0, parse("2024-01-01T00:00:00.000Z").compareTo(modifiedRecord?.orderDatetime))
    assertEquals(0.2, modifiedRecord?.price)
    assertEquals(2, modifiedRecord?.quantity)
  }

  @Test
  fun `test delete ORDER`(): Unit = runBlocking {
    val result = entityDb.insert(
      Order {
        counterpartyId = 1
        direction = Direction.BUY
        instrumentId = 1
        isAgency = false
        orderDate = now()
        orderDatetime = now()
        price = 0.1
        quantity = 1
      }
    )
    val numRecordsBefore = entityDb.getBulk<Order>().toList().size
    val orderIdValue = result.record.orderId
    val deleteResult = client.sendEvent(
      details = Order.ById(orderIdValue),
      messageType = "EVENT_ORDER_DELETE",
      userName = adminUser
    )
    deleteResult.assertedCast<EventReply.EventAck>()
    val numRecordsAfter = entityDb.getBulk<Order>().toList().size
    assertEquals(numRecordsBefore - 1, numRecordsAfter)
  }

  @Test
  fun `test insert COUNTERPARTY`(): Unit = runBlocking {
    val result = client.sendEvent(
      details = Counterparty {
        counterpartyName = "1"
      },
      messageType = "EVENT_COUNTERPARTY_INSERT",
      userName = adminUser
    )
    result.assertedCast<EventReply.EventAck>()
    val counterparty = entityDb.getBulk<Counterparty>().toList()
    assertTrue(counterparty.isNotEmpty())
  }

  @Test
  fun `test modify COUNTERPARTY`(): Unit = runBlocking {
    val result = entityDb.insert(
      Counterparty {
        counterpartyName = "1"
      }
    )
    val counterpartyIdValue = result.record.counterpartyId
    val modifyResult = client.sendEvent(
      details = Counterparty {
        counterpartyId = counterpartyIdValue
        counterpartyName = "2"
      },
      messageType = "EVENT_COUNTERPARTY_MODIFY",
      userName = adminUser
    )
    modifyResult.assertedCast<EventReply.EventAck>()
    val modifiedRecord = entityDb.get(Counterparty.ById(counterpartyIdValue))
    assertEquals("2", modifiedRecord?.counterpartyName)
  }

  @Test
  fun `test delete COUNTERPARTY`(): Unit = runBlocking {
    val result = entityDb.insert(
      Counterparty {
        counterpartyName = "1"
      }
    )
    val numRecordsBefore = entityDb.getBulk<Counterparty>().toList().size
    val counterpartyIdValue = result.record.counterpartyId
    val deleteResult = client.sendEvent(
      details = Counterparty.ById(counterpartyIdValue),
      messageType = "EVENT_COUNTERPARTY_DELETE",
      userName = adminUser
    )
    deleteResult.assertedCast<EventReply.EventAck>()
    val numRecordsAfter = entityDb.getBulk<Counterparty>().toList().size
    assertEquals(numRecordsBefore - 1, numRecordsAfter)
  }

  @Test
  fun `test insert ORDER_STATUS`(): Unit = runBlocking {
    val result = client.sendEvent(
      details = OrderStatus {
        avgPrice = 0.1
        orderId = 1
        quantityFilled = 1
      },
      messageType = "EVENT_ORDER_STATUS_INSERT",
      userName = adminUser
    )
    result.assertedCast<EventReply.EventAck>()
    val orderStatus = entityDb.getBulk<OrderStatus>().toList()
    assertTrue(orderStatus.isNotEmpty())
  }

  @Test
  fun `test modify ORDER_STATUS`(): Unit = runBlocking {
    val result = entityDb.insert(
      OrderStatus {
        avgPrice = 0.1
        orderId = 1
        quantityFilled = 1
      }
    )
    val orderIdValue = result.record.orderId
    val modifyResult = client.sendEvent(
      details = OrderStatus {
        avgPrice = 0.2
        orderId = orderIdValue
        quantityFilled = 2
      },
      messageType = "EVENT_ORDER_STATUS_MODIFY",
      userName = adminUser
    )
    modifyResult.assertedCast<EventReply.EventAck>()
    val modifiedRecord = entityDb.get(OrderStatus.ByOrderId(orderIdValue))
    assertEquals(0.2, modifiedRecord?.avgPrice)
    assertEquals(2, modifiedRecord?.quantityFilled)
  }

  @Test
  fun `test delete ORDER_STATUS`(): Unit = runBlocking {
    val result = entityDb.insert(
      OrderStatus {
        avgPrice = 0.1
        orderId = 1
        quantityFilled = 1
      }
    )
    val numRecordsBefore = entityDb.getBulk<OrderStatus>().toList().size
    val orderIdValue = result.record.orderId
    val deleteResult = client.sendEvent(
      details = OrderStatus.ByOrderId(orderIdValue),
      messageType = "EVENT_ORDER_STATUS_DELETE",
      userName = adminUser
    )
    deleteResult.assertedCast<EventReply.EventAck>()
    val numRecordsAfter = entityDb.getBulk<OrderStatus>().toList().size
    assertEquals(numRecordsBefore - 1, numRecordsAfter)
  }

  @Test
  fun `test insert INSTRUMENT`(): Unit = runBlocking {
    val result = client.sendEvent(
      details = Instrument {
        symbol = "1"
      },
      messageType = "EVENT_INSTRUMENT_INSERT",
      userName = adminUser
    )
    result.assertedCast<EventReply.EventAck>()
    val instrument = entityDb.getBulk<Instrument>().toList()
    assertTrue(instrument.isNotEmpty())
  }

  @Test
  fun `test modify INSTRUMENT`(): Unit = runBlocking {
    val result = entityDb.insert(
      Instrument {
        symbol = "1"
      }
    )
    val instrumentIdValue = result.record.instrumentId
    val modifyResult = client.sendEvent(
      details = Instrument {
        instrumentId = instrumentIdValue
        symbol = "2"
      },
      messageType = "EVENT_INSTRUMENT_MODIFY",
      userName = adminUser
    )
    modifyResult.assertedCast<EventReply.EventAck>()
    val modifiedRecord = entityDb.get(Instrument.ById(instrumentIdValue))
    assertEquals("2", modifiedRecord?.symbol)
  }

  @Test
  fun `test delete INSTRUMENT`(): Unit = runBlocking {
    val result = entityDb.insert(
      Instrument {
        symbol = "1"
      }
    )
    val numRecordsBefore = entityDb.getBulk<Instrument>().toList().size
    val instrumentIdValue = result.record.instrumentId
    val deleteResult = client.sendEvent(
      details = Instrument.ById(instrumentIdValue),
      messageType = "EVENT_INSTRUMENT_DELETE",
      userName = adminUser
    )
    deleteResult.assertedCast<EventReply.EventAck>()
    val numRecordsAfter = entityDb.getBulk<Instrument>().toList().size
    assertEquals(numRecordsBefore - 1, numRecordsAfter)
  }

  @Test
  fun `test insert TRADE`(): Unit = runBlocking {
    val result = client.sendEvent(
      details = Trade {
        orderId = 1
        tradedPrice = 0.1
        tradedQuantity = 1
      },
      messageType = "EVENT_TRADE_INSERT",
      userName = adminUser
    )
    result.assertedCast<EventReply.EventAck>()
    val trade = entityDb.getBulk<Trade>().toList()
    assertTrue(trade.isNotEmpty())
  }

  @Test
  fun `test modify TRADE`(): Unit = runBlocking {
    val result = entityDb.insert(
      Trade {
        orderId = 1
        tradedPrice = 0.1
        tradedQuantity = 1
      }
    )
    val tradeIdValue = result.record.tradeId
    val modifyResult = client.sendEvent(
      details = Trade {
        orderId = 2
        tradeId = tradeIdValue
        tradedPrice = 0.2
        tradedQuantity = 2
      },
      messageType = "EVENT_TRADE_MODIFY",
      userName = adminUser
    )
    modifyResult.assertedCast<EventReply.EventAck>()
    val modifiedRecord = entityDb.get(Trade.ById(tradeIdValue))
    assertEquals(2, modifiedRecord?.orderId)
    assertEquals(0.2, modifiedRecord?.tradedPrice)
    assertEquals(2, modifiedRecord?.tradedQuantity)
  }

  @Test
  fun `test delete TRADE`(): Unit = runBlocking {
    val result = entityDb.insert(
      Trade {
        orderId = 1
        tradedPrice = 0.1
        tradedQuantity = 1
      }
    )
    val numRecordsBefore = entityDb.getBulk<Trade>().toList().size
    val tradeIdValue = result.record.tradeId
    val deleteResult = client.sendEvent(
      details = Trade.ById(tradeIdValue),
      messageType = "EVENT_TRADE_DELETE",
      userName = adminUser
    )
    deleteResult.assertedCast<EventReply.EventAck>()
    val numRecordsAfter = entityDb.getBulk<Trade>().toList().size
    assertEquals(numRecordsBefore - 1, numRecordsAfter)
  }

  @Test
  fun `test insert ORDER_TOTAL_BY_INST`(): Unit = runBlocking {
    val result = client.sendEvent(
      details = OrderTotalByInst {
        instrumentId = 1
        totalQuantityOrdered = 1
      },
      messageType = "EVENT_ORDER_TOTAL_BY_INST_INSERT",
      userName = adminUser
    )
    result.assertedCast<EventReply.EventAck>()
    val orderTotalByInst = entityDb.getBulk<OrderTotalByInst>().toList()
    assertTrue(orderTotalByInst.isNotEmpty())
  }

  @Test
  fun `test modify ORDER_TOTAL_BY_INST`(): Unit = runBlocking {
    val result = entityDb.insert(
      OrderTotalByInst {
        instrumentId = 1
        totalQuantityOrdered = 1
      }
    )
    val instrumentIdValue = result.record.instrumentId
    val modifyResult = client.sendEvent(
      details = OrderTotalByInst {
        instrumentId = instrumentIdValue
        totalQuantityOrdered = 2
      },
      messageType = "EVENT_ORDER_TOTAL_BY_INST_MODIFY",
      userName = adminUser
    )
    modifyResult.assertedCast<EventReply.EventAck>()
    val modifiedRecord = entityDb.get(OrderTotalByInst.ByInstrumentId(instrumentIdValue))
    assertEquals(2, modifiedRecord?.totalQuantityOrdered)
  }

  @Test
  fun `test delete ORDER_TOTAL_BY_INST`(): Unit = runBlocking {
    val result = entityDb.insert(
      OrderTotalByInst {
        instrumentId = 1
        totalQuantityOrdered = 1
      }
    )
    val numRecordsBefore = entityDb.getBulk<OrderTotalByInst>().toList().size
    val instrumentIdValue = result.record.instrumentId
    val deleteResult = client.sendEvent(
      details = OrderTotalByInst.ByInstrumentId(instrumentIdValue),
      messageType = "EVENT_ORDER_TOTAL_BY_INST_DELETE",
      userName = adminUser
    )
    deleteResult.assertedCast<EventReply.EventAck>()
    val numRecordsAfter = entityDb.getBulk<OrderTotalByInst>().toList().size
    assertEquals(numRecordsBefore - 1, numRecordsAfter)
  }
}
