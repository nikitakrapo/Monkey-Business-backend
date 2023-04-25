package com.monkeybusiness.plugins.routing

import com.monkeybusiness.finance.FinancesRepository
import com.monkeybusiness.finance.dto.BankCardOpeningRequest
import com.monkeybusiness.network.getUid
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.bankCardsRouting(
    financesRepository: FinancesRepository,
) {
    routing {
        authenticate {
            post("/bank-card") {
                val uid = getUid()
                val request = call.receive<BankCardOpeningRequest>()
                financesRepository.createBankCard(iban = request.iban)
            }
        }
    }
}