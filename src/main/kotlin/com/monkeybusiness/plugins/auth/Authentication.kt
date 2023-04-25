package com.monkeybusiness.plugins

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseToken
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.bearer

fun Application.configureAuthentication(firebaseAuth: FirebaseAuth) {
    install(Authentication) {
        bearer {
            authenticate { bearerTokenCredential ->
                val decodedToken = parseFirebaseToken(
                    firebaseAuth = firebaseAuth,
                    token = bearerTokenCredential.token
                )
                decodedToken?.let { firebaseToken ->
                    UserIdPrincipal(name = firebaseToken.uid)
                }
            }
        }
    }
}

private fun parseFirebaseToken(firebaseAuth: FirebaseAuth, token: String): FirebaseToken? {
    return try {
        firebaseAuth.verifyIdToken(token)
    } catch (e: IllegalArgumentException) {
        null
    }
}