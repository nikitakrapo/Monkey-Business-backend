package com.monkeybusiness.plugins

import com.monkeybusiness.finance.transactions.TransactionsRepository
import com.monkeybusiness.finance.transactions.dto.TransactionRequest
import com.monkeybusiness.finance.transactions.dto.TransactionsResponse
import com.monkeybusiness.network.getUid
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.transactionsRouting(
    transactionsRepository: TransactionsRepository,
) {
    routing {
        authenticate {
            post("/transaction") {
                val uuid = getUid()
                val request = call.receive<TransactionRequest>()
                val transaction = request.transaction
                transactionsRepository.insertTransactions(
                    uuid = uuid,
                    transaction = transaction
                )
                call.respond(HttpStatusCode.OK)
            }
            get("/transactions") {
                val uuid = getUid()
                val transactions = transactionsRepository.getAllTransactions(uuid)
                val response = TransactionsResponse(
                    transactionList = transactions
                )
                call.respond(response)
            }
        }
    }
}
