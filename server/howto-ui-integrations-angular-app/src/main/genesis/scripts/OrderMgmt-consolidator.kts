/**
  * This file defines the consolidators for this application. Consolidators take
  * multiple input objects such as trades and aggregate them to output objects
  * such as positions using the configured aggregation keys defined in the group by blocks.

  * Full documentation on consolidator may be found here >> https://docs.genesis.global/docs/develop/server-capabilities/real-time-aggregation-consolidator/

 */

consolidators {
  consolidator("ORDER_STATUS_CONS", TRADE, ORDER_STATUS) {
    select {
      // Here the records of the input object are aggregated to perform different aggregations and added into output field
      ORDER_STATUS {
        avg { tradedPrice } into AVG_PRICE
        sum { tradedQuantity } into QUANTITY_FILLED
      }
    }
    groupBy {
      /* Here you provide the aggregation key, which is the index of output object,
       * index-fields of input object are used to group the records of input object and perform necessary aggregations mentioned above in select block
       * Returns `groupId`(index-fields) which is used in building the output object in into block
       * Note: new indices may be required in order to change this aggregation key
      */
      OrderStatus.ByOrderId(orderId)
    } into {
      // indexScan is needed when a Consolidator needs to re-read previously consolidated rows in order to calculate the correct value
      indexScan { Trade.ByOrderId(groupId.orderId) }
      build {
        // Describe how the output object should be initialized on creation
        OrderStatus {
          orderId = groupId.orderId
        }
      }
    }
  }
  consolidator("ORDER_TOTAL_BY_INST_CONS", ORDER, ORDER_TOTAL_BY_INST) {
    select {
      // Here the records of the input object are aggregated to perform different aggregations and added into output field
      ORDER_TOTAL_BY_INST {
        sum { quantity } into TOTAL_QUANTITY_ORDERED
      }
    }
    groupBy {
      /* Here you provide the aggregation key, which is the index of output object,
       * index-fields of input object are used to group the records of input object and perform necessary aggregations mentioned above in select block
       * Returns `groupId`(index-fields) which is used in building the output object in into block
       * Note: new indices may be required in order to change this aggregation key
      */
      OrderTotalByInst.ByInstrumentId(instrumentId)
    }
  }

  // TODO - add new consolidators here
}
