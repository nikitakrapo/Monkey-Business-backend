package com.nikitakrapo.plugins

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseToken
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.bearer

fun Application.configureAuthentication() {
    install(Authentication) {
        bearer {
            authenticate { bearerTokenCredential ->
                val decodedToken = parseFirebaseToken(bearerTokenCredential.token)
                decodedToken?.let { firebaseToken ->
                    UserIdPrincipal(name = firebaseToken.uid)
                }
            }
        }
    }
}

private fun parseFirebaseToken(token: String): FirebaseToken? {
    return try {
        FirebaseAuth.getInstance().verifyIdToken(token)
    } catch (e: IllegalArgumentException) {
        null
    }
}