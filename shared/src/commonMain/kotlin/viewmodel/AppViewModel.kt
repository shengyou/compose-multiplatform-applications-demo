package viewmodel

import client.AuthClient
import data.LoginRequest
import kotlinx.coroutines.launch

class AppViewModel : CommonViewModel() {
    private val client = AuthClient()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val response = client.login(
                LoginRequest(username, password)
            )

            if (response.result) {
                println("logged in")
            } else {
                println("something went wrong")
            }
        }
    }
}
