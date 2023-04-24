package com.monkeybusiness.accounts

import com.monkeybusiness.accounts.dto.BankAccount
import com.monkeybusiness.accounts.dto.BankAccountOpeningRequest
import com.monkeybusiness.accounts.dto.BankAccountsResponse
import com.monkeybusiness.accounts.dto.BankCard
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
    bankAccountsRepository: BankAccountsRepository,
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
                bankAccountsRepository.createBankAccount(
                    uid = uid,
                    currency = currency
                )
                call.respond(HttpStatusCode.OK)
            }

            get("/bank-accounts") {
                val uid = getUid()
                val accounts = bankAccountsRepository.getAllBankAccounts(uid = uid)
                val fakeAccounts = BankAccountsResponse(
                    accounts = listOf(
                        BankAccount(
                            iban = "BE123456789101112",
                            name = "Simple name",
                            balance = 90000000,
                            currencyCode = "RUB",
                            cards = listOf(
                                BankCard("2200012345678901"),
                                BankCard("4276123456789012"),
                            )
                        )
                    )
                )
                call.respond(fakeAccounts)
            }
        }
    }
}
