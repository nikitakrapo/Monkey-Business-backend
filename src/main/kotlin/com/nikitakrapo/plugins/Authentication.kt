package com.nikitakrapo.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.application.log
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.bearer

fun Application.configureAuthentication() {
    install(Authentication) {
        bearer {
            realm = "Access to /balance"
            authenticate { bearerTokenCredential ->
                this@configureAuthentication.log.debug("bearerTokenCredential: $bearerTokenCredential")
                if (bearerTokenCredential.token == "test") {
                    UserIdPrincipal("common")
                } else null
            }
        }
    }
}