package com.nikitakrapo.monkeybusiness

import com.google.firebase.FirebaseApp
import com.nikitakrapo.monkeybusiness.plugins.configureAuthentication
import com.nikitakrapo.monkeybusiness.plugins.configureRouting
import com.nikitakrapo.monkeybusiness.plugins.configureSerialization
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty


fun main() {
    initializeApp()
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

private fun Application.module() {
    configureSerialization()
    configureAuthentication()
    configureRouting()
}

private fun initializeApp() {
    FirebaseApp.initializeApp();
}