import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import client.AuthClient
import data.LoginRequest
import data.LoginResponse
import kotlinx.coroutines.launch
import ui.LoginScreen
import ui.Screen
import ui.WelcomeScreen
import viewmodel.AppViewModel

expect fun getPlatformName(): String

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App() {
    var screenState by remember { mutableStateOf(Screen.LOGIN) }
    val appViewModel = AppViewModel()
    val client = AuthClient()

    MaterialTheme {
        AnimatedContent(
            targetState = screenState,
            transitionSpec = {
                (slideInHorizontally { it } + fadeIn(animationSpec = tween(durationMillis = 2000)) with
                        slideOutHorizontally { -it })
                    .using(
                    SizeTransform(clip = false)
                )
            }
        ) { screen ->
            when (screen) {
                Screen.LOGIN -> LoginScreen(onLogin = { username, password ->
                    appViewModel.viewModelScope.launch {
                        val response = client.login(
                            LoginRequest(username, password)
                        )
                        if (response.result) {
                            screenState = Screen.WELCOME
                        }
                    }
                })

                Screen.WELCOME -> WelcomeScreen()
            }
        }
    }
}
