package io.kraftsman.server.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable

fun Application.configureHome() {

    routing {
        get("/") {
            call.respondText("Kotlin Multiplatform Demo")
        }
    }
}
