package com.monkeybusiness

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.monkeybusiness.accounts.BankAccountsRepository
import com.monkeybusiness.accounts.bankAccountsRouting
import com.monkeybusiness.database.SqlDriverProvider
import com.monkeybusiness.finance.transactions.TransactionsRepository
import com.monkeybusiness.plugins.basicRouting
import com.monkeybusiness.plugins.configureAuthentication
import com.monkeybusiness.plugins.transactionsRouting
import com.monkeybusiness.plugins.configureSerialization
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty


fun main() {
    initializeApp()
    embeddedServer(
        factory = Netty,
        host = "0.0.0.0",
        module = Application::module
    )
        .start(wait = true)
}

// TODO: handle dependencies properly
private fun Application.module() {
    configureSerialization()

    val firebaseAuth = FirebaseAuth.getInstance()
    configureAuthentication(firebaseAuth)

    basicRouting()

    val sqlDriver = SqlDriverProvider.sqlDriver

    val bankAccountsRepository = BankAccountsRepository(sqlDriver)
    bankAccountsRouting(bankAccountsRepository = bankAccountsRepository)

    val transactionsRepository = TransactionsRepository(sqlDriver)
    transactionsRouting(transactionsRepository = transactionsRepository)
}

private fun initializeApp() {
    FirebaseApp.initializeApp()
}