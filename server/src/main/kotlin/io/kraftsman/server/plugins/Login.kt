package io.kraftsman.server.plugins

import data.LoginRequest
import data.LoginResponse
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureLogin() {

    routing {
        post("/users/login") {
            val loginRequest = call.receive<LoginRequest>()

            if (loginRequest.username == "shengyou" && loginRequest.password == "0000") {
                call.respond(
                    LoginResponse(
                        result = true,
                        message = "login success"
                    )
                )
            } else {
                call.respond(
                    LoginResponse(
                        result = false,
                        message = "wrong username and password"
                    )
                )
            }
        }
    }
}