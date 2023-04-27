package com.monkeybusiness.finance

import com.monkeybusiness.finance.dto.BankAccountOpeningRequest
import com.monkeybusiness.finance.dto.BankAccountsResponse
import com.monkeybusiness.finance.models.Currency
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

fun Application.bankAccountsRouting(
    financesRepository: FinancesRepository,
) {
    routing {
        authenticate {
            post("/bank-account") {
                val uid = getUid()
                val request = call.receive<BankAccountOpeningRequest>()
                val currency = try {
                    Currency.fromCode(request.currencyCode)
                } catch (e: IllegalArgumentException) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }
                financesRepository.createBankAccount(
                    uid = uid,
                    currency = currency
                )
                call.respond(HttpStatusCode.OK)
            }

            get("/bank-accounts") {
                val uid = getUid()
                val accounts = financesRepository.getAllBankAccounts(uid = uid)
                val response = BankAccountsResponse(accounts = accounts)
                call.respond(response)
            }
        }
    }
}
