import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import client.AuthClient
import data.LoginRequest
import kotlinx.coroutines.launch
import ui.LoginScreen
import ui.Screen
import ui.WelcomeScreen
import viewmodel.AppViewModel

expect fun getPlatformName(): String

@Composable
fun App() {
    val screenState: MutableState<Screen> = remember { mutableStateOf(Screen.LOGIN) }
    val appViewModel = AppViewModel()
    val client = AuthClient()

    MaterialTheme {
        when (screenState.value) {
            Screen.LOGIN -> LoginScreen(onLogin = { username, password ->
                appViewModel.viewModelScope.launch {
                    val response = client.login(
                        LoginRequest(username, password)
                    )

                    if (response.result) {
                        screenState.value = Screen.WELCOME
                    }
                }
            })
            Screen.WELCOME -> WelcomeScreen()
        }
    }
}
