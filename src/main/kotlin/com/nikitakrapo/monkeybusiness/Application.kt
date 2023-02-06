package com.nikitakrapo.monkeybusiness

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.nikitakrapo.monkeybusiness.finance.transactions.TransactionsRepository
import com.nikitakrapo.monkeybusiness.plugins.basicRouting
import com.nikitakrapo.monkeybusiness.plugins.configureAuthentication
import com.nikitakrapo.monkeybusiness.plugins.transactionsRouting
import com.nikitakrapo.monkeybusiness.plugins.configureSerialization
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty


fun main() {
    initializeApp()
    embeddedServer(Netty, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

// TODO: handle dependencies properly
private fun Application.module() {
    configureSerialization()

    val firebaseAuth = FirebaseAuth.getInstance()
    configureAuthentication(firebaseAuth)

    basicRouting()

    val transactionsRepository = TransactionsRepository()
    transactionsRouting(transactionsRepository = transactionsRepository)
}

private fun initializeApp() {
    FirebaseApp.initializeApp()
}