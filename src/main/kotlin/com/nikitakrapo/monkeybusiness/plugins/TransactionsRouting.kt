package com.nikitakrapo.monkeybusiness.plugins

import com.nikitakrapo.monkeybusiness.finance.transactions.TransactionsRepository
import com.nikitakrapo.monkeybusiness.finance.transactions.dto.TransactionRequest
import com.nikitakrapo.monkeybusiness.finance.transactions.dto.TransactionsResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.authentication
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.ktor.util.pipeline.PipelineContext

fun Application.transactionsRouting(
    transactionsRepository: TransactionsRepository,
) {
    routing {
        authenticate {
            post("/transaction") {
                val uuid = getUuid()
                val request = call.receive<TransactionRequest>()
                val transaction = request.transaction
                transactionsRepository.insertTransactions(
                    uuid = uuid,
                    transaction = transaction
                )
                call.respond(HttpStatusCode.OK)
            }
            get("/transactions") {
                val uuid = getUuid()
                val transactions = transactionsRepository.getAllTransactions(uuid)
                val response = TransactionsResponse(
                    transactionList = transactions
                )
                call.respond(response)
            }
        }
    }
}

private fun PipelineContext<Unit, ApplicationCall>.getUuid(): String =
    requireNotNull(call.authentication.principal<UserIdPrincipal>()).name
