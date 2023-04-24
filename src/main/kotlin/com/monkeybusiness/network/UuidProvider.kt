package com.monkeybusiness.network

import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.authentication
import io.ktor.util.pipeline.PipelineContext

fun PipelineContext<Unit, ApplicationCall>.getUid(): String =
    requireNotNull(call.authentication.principal<UserIdPrincipal>()).name
