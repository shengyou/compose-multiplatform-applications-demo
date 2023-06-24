package ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


val lightBlue = Color(0xFFbdcff6)

@OptIn(ExperimentalResourceApi::class, ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen() {
    val infiniteTransition = rememberInfiniteTransition()
    Column(
        modifier = Modifier.fillMaxSize().background(lightBlue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val position by infiniteTransition.animateFloat(
            initialValue = -60f, targetValue = 60f, animationSpec = infiniteRepeatable(
                animation = tween(
                    800, easing = FastOutSlowInEasing
                ), repeatMode = RepeatMode.Reverse
            )
        )
        val density = LocalDensity.current

        Image(
            modifier = Modifier.size(300.dp).offset(x = position.dp),
            painter = painterResource("icon_kodee.xml"),
            contentDescription = null
        )

        AnimatedVisibility(
            visible = true,
            enter = slideInVertically {
                with(density) { 200.dp.roundToPx() }
            } +
                    expandVertically(animationSpec = spring(stiffness = Spring.DampingRatioHighBouncy)) +
                    fadeIn(
                        initialAlpha = 0.3f
                    )
        ) {
            Text(
                textAlign = TextAlign.Center,
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                text = "Welcome,\nLet's Kotlin!",
                color = Color.Black,
            )
        }

    }

}


