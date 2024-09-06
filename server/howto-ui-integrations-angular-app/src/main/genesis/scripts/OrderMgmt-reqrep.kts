/**
  * This file defines request-replies of the application. 
  * Request-Replies provide snapshot data from a table or view in response to a request from the front end.
  * Once the response is received, the transaction is over (unlike a Data Server, which stays connected to the client and pushes updates)

  * Full documentation on request server may be found here >> https://docs.genesis.global/docs/develop/server-capabilities/snapshot-queries-request-server/

 */

requestReplies {
  requestReply(ORDER)
  requestReply(ORDER_AUDIT)
  requestReply(COUNTERPARTY)
  requestReply(COUNTERPARTY_AUDIT)
  requestReply(ORDER_STATUS)
  requestReply(ORDER_STATUS_AUDIT)
  requestReply(INSTRUMENT)
  requestReply(INSTRUMENT_AUDIT)
  requestReply(TRADE)
  requestReply(TRADE_AUDIT)
  requestReply(ORDER_TOTAL_BY_INST)
  requestReply(ORDER_TOTAL_BY_INST_AUDIT)
  requestReply(ORDER_VIEW)
  requestReply(ORDER_TOTAL_BY_INST_VIEW)

  //TODO 4.a Add authorisation, where clauses, indices and restrict fields on the generated request server queries as required by the application.
  //TODO 4.b Add further request server queries (including custom request servers) as needed by the application here. See the comments at the top of this file for learning and guidance.
}
