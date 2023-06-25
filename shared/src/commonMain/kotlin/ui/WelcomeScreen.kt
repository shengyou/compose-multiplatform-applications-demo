package ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


val lightBlue = Color(0xFFbdcff6)

@OptIn(
    ExperimentalResourceApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun WelcomeScreen() {
    val infiniteTransition = rememberInfiniteTransition()

    Box {
        Column(
            modifier = Modifier.fillMaxSize().background(
                brush = Brush.verticalGradient(
                    listOf(
                        lightBlue,
                        Color.White
                    )
                )
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // kodee
            val position by infiniteTransition.animateFloat(
                initialValue = -60f, targetValue = 60f, animationSpec = infiniteRepeatable(
                    animation = tween(
                        800, easing = FastOutSlowInEasing
                    ), repeatMode = RepeatMode.Reverse
                )
            )
            Image(
                modifier = Modifier.size(300.dp).offset(x = position.dp),
                painter = painterResource("icon_kodee.xml"),
                contentDescription = null
            )

            // welcome headline
            var isAnimated by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) {
                delay(200)
                isAnimated = true
            }
            AnimatedVisibility(
                visible = isAnimated,
                enter = scaleIn(animationSpec = spring(stiffness = Spring.DampingRatioMediumBouncy))
            ) {
                Text(
                    modifier = Modifier .alpha(alpha = 0.25f)
                        .offset(x = 2.dp, y = (-4).dp)
                        .blur(radius = 2.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Bold,
                    text = "Welcome,\nLet's Kotlin!",
                    color = Color.Black,
                )
                Text(
                    textAlign = TextAlign.Center,
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Bold,
                    text = "Welcome,\nLet's Kotlin!",
                    color = Color.Black,
                )
            }
        }

        // snowflake
        var snowInvoke by remember { mutableStateOf(0) }
        LaunchedEffect(Unit) {
            repeat(15) {
                delay((500..1000).random().toLong())
                snowInvoke = it
            }
        }
        Box(Modifier.fillMaxSize()) {
            repeat(snowInvoke) {
                FallingSnowFlake()
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun FallingSnowFlake() {
    val infiniteTransition = rememberInfiniteTransition()
    val initialY = remember {
        (-600..-60).random().toFloat()
    }
    val targetY = remember {
        1000f
    }
    val y by infiniteTransition.animateFloat(
        initialValue = initialY, targetValue = targetY, animationSpec = infiniteRepeatable(
            animation = tween(
                8000, easing = FastOutSlowInEasing
            ), repeatMode = RepeatMode.Restart
        )
    )

    val initialX = remember {
        (-10..500).random().toFloat()
    }
    val targetX = remember {
        (-10..500).random().toFloat()
    }
    val x by infiniteTransition.animateFloat(
        initialValue = initialX, targetValue = targetX, animationSpec = infiniteRepeatable(
            animation = tween(
                3000, easing = LinearEasing
            ), repeatMode = RepeatMode.Reverse
        )
    )
    val id = remember {
        SnowFlake.pickup().id
    }
    Image(
        modifier = Modifier.offset(x = x.dp, y = y.dp),
        painter = painterResource(id),
        contentDescription = null
    )
}

private enum class SnowFlake(val id: String) {
    SMALL("snowflake01.xml"),
    BIG("snowflake02.xml");

    companion object {
        fun pickup() = values().random()
    }
}





