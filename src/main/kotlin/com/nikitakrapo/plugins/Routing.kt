package com.nikitakrapo.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.authentication
import io.ktor.server.response.*
import io.ktor.server.request.*
import kotlinx.serialization.Serializable

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        authenticate {
            get("/balance") {
                call.respondText("0")
            }
        }
    }
}
