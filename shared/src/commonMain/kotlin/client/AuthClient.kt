package client

import data.LoginRequest
import data.LoginResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json

class AuthClient {
    private val urlString: String = "https://8270-118-160-5-151.jp.ngrok.io"
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            url(urlString)
        }
        expectSuccess = true
    }

    suspend fun login(loginRequest: LoginRequest): LoginResponse =
        httpClient.post("/users/login") {
            setBody(loginRequest)
            contentType(ContentType.Application.Json)
        }.body()
}
