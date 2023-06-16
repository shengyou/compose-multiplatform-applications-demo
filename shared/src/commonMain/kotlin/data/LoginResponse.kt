package data

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val result: Boolean,
    val message: String,
)
