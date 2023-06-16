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

        get("/api") {
            val posts = (1..10).map {
                Post(
                    id = it,
                    title = "Title $it",
                    description = "Desc $it",
                )
            }

            call.respond(mapOf("data" to posts))
        }
    }
}

@Serializable
data class Post(
    val id: Int,
    val title: String,
    val description: String,
)
