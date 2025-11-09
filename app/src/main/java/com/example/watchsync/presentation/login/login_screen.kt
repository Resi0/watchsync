package com.example.watchsync.presentation.login

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.watchsync.ui.theme.ElectricBlue
import com.example.watchsync.ui.theme.NightBlue
import com.example.watchsync.ui.theme.Turquoise
import com.example.watchsync.ui.theme.WatchSyncTheme

@Composable
fun LoginScreen(
    onStartClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        // Sinematik gradient arka plan
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            NightBlue,
                            Color(0xFF000000),
                            Color(0xFF0A0A1A)
                        )
                    )
                )
        )

        // Işık efektleri (sinema projektör ışıkları)
        CinemaLightEffects()

        // Ana içerik
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))

            // Logo - Sinema ve arkadaşlık temalı
            CinemaFriendshipLogo(
                modifier = Modifier
                    .size(140.dp)
                    .padding(bottom = 32.dp)
            )

            // Marka ismi
            Text(
                text = "WatchSync",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 52.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Turquoise,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Tanıtıcı cümle
            Text(
                text = "Aynı zevke sahip insanları bul,\nberaber izle, anılar biriktir",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = Color.White.copy(alpha = 0.85f),
                textAlign = TextAlign.Center,
                lineHeight = 26.sp,
                modifier = Modifier.padding(bottom = 56.dp)
            )

            // Şimdi Başla butonu
            StartButton(
                onClick = onStartClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun CinemaLightEffects() {
    val infiniteTransition = rememberInfiniteTransition(label = "lights")
    
    val light1Alpha by infiniteTransition.animateFloat(
        initialValue = 0.1f,
        targetValue = 0.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "light1"
    )

    val light2Alpha by infiniteTransition.animateFloat(
        initialValue = 0.15f,
        targetValue = 0.25f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "light2"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Sol üst projektör ışığı
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Turquoise.copy(alpha = light1Alpha),
                                Color.Transparent
                            )
                        ),
                        radius = size.width * 0.8f,
                        center = Offset(size.width * 0.2f, size.height * 0.2f)
                    )
                }
        )

        // Sağ üst projektör ışığı
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                ElectricBlue.copy(alpha = light2Alpha),
                                Color.Transparent
                            )
                        ),
                        radius = size.width * 0.7f,
                        center = Offset(size.width * 0.8f, size.height * 0.25f)
                    )
                }
        )
    }
}

@Composable
private fun CinemaFriendshipLogo(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "logo")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.96f,
        targetValue = 1.04f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    val glowIntensity by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow"
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // Dış glow
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Turquoise.copy(alpha = glowIntensity * 0.2f),
                                Color.Transparent
                            )
                        ),
                        radius = size.width * 0.7f
                    )
                }
        )

        // Ana logo çizimi
        Box(
            modifier = Modifier
                .size(120.dp)
                .scale(pulseScale)
                .drawBehind {
                    val centerX = size.width / 2
                    val centerY = size.height / 2
                    val baseRadius = size.width * 0.35f

                    // Sinema perdesi (üst kısım)
                    val curtainHeight = baseRadius * 0.4f
                    drawRect(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Turquoise.copy(alpha = 0.8f),
                                Turquoise.copy(alpha = 0.4f)
                            )
                        ),
                        topLeft = Offset(centerX - baseRadius * 1.2f, centerY - baseRadius * 0.8f),
                        size = Size(baseRadius * 2.4f, curtainHeight)
                    )

                    // Perde kıvrımları (3 adet)
                    for (i in 0..2) {
                        val xPos = centerX - baseRadius * 0.8f + (i * baseRadius * 0.8f)
                        drawArc(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Turquoise.copy(alpha = 0.6f),
                                    Turquoise.copy(alpha = 0.3f)
                                )
                            ),
                            startAngle = 0f,
                            sweepAngle = 180f,
                            useCenter = false,
                            topLeft = Offset(
                                xPos - baseRadius * 0.2f,
                                centerY - baseRadius * 0.8f
                            ),
                            size = Size(baseRadius * 0.4f, curtainHeight * 0.6f)
                        )
                    }

                    // İki kişi silueti (arkadaşlık) - Sol kişi
                    val person1X = centerX - baseRadius * 0.5f
                    val person1Y = centerY + baseRadius * 0.2f
                    
                    // Sol kişi - vücut
                    drawCircle(
                        color = Turquoise,
                        radius = baseRadius * 0.18f,
                        center = Offset(person1X, person1Y - baseRadius * 0.15f)
                    )
                    // Sol kişi - gövde
                    drawRect(
                        color = Turquoise,
                        topLeft = Offset(
                            person1X - baseRadius * 0.12f,
                            person1Y - baseRadius * 0.05f
                        ),
                        size = Size(baseRadius * 0.24f, baseRadius * 0.3f)
                    )
                    // Sol kişi - bacaklar
                    drawLine(
                        color = Turquoise,
                        start = Offset(person1X - baseRadius * 0.08f, person1Y + baseRadius * 0.25f),
                        end = Offset(person1X - baseRadius * 0.08f, person1Y + baseRadius * 0.45f),
                        strokeWidth = baseRadius * 0.12f
                    )
                    drawLine(
                        color = Turquoise,
                        start = Offset(person1X + baseRadius * 0.08f, person1Y + baseRadius * 0.25f),
                        end = Offset(person1X + baseRadius * 0.08f, person1Y + baseRadius * 0.45f),
                        strokeWidth = baseRadius * 0.12f
                    )

                    // İki kişi silueti - Sağ kişi
                    val person2X = centerX + baseRadius * 0.5f
                    val person2Y = centerY + baseRadius * 0.2f
                    
                    // Sağ kişi - vücut
                    drawCircle(
                        color = ElectricBlue,
                        radius = baseRadius * 0.18f,
                        center = Offset(person2X, person2Y - baseRadius * 0.15f)
                    )
                    // Sağ kişi - gövde
                    drawRect(
                        color = ElectricBlue,
                        topLeft = Offset(
                            person2X - baseRadius * 0.12f,
                            person2Y - baseRadius * 0.05f
                        ),
                        size = Size(baseRadius * 0.24f, baseRadius * 0.3f)
                    )
                    // Sağ kişi - bacaklar
                    drawLine(
                        color = ElectricBlue,
                        start = Offset(person2X - baseRadius * 0.08f, person2Y + baseRadius * 0.25f),
                        end = Offset(person2X - baseRadius * 0.08f, person2Y + baseRadius * 0.45f),
                        strokeWidth = baseRadius * 0.12f
                    )
                    drawLine(
                        color = ElectricBlue,
                        start = Offset(person2X + baseRadius * 0.08f, person2Y + baseRadius * 0.25f),
                        end = Offset(person2X + baseRadius * 0.08f, person2Y + baseRadius * 0.45f),
                        strokeWidth = baseRadius * 0.12f
                    )

                    // İki kişiyi birleştiren çizgi (arkadaşlık bağı)
                    drawLine(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Turquoise.copy(alpha = 0.6f),
                                ElectricBlue.copy(alpha = 0.6f)
                            )
                        ),
                        start = Offset(person1X + baseRadius * 0.2f, person1Y),
                        end = Offset(person2X - baseRadius * 0.2f, person2Y),
                        strokeWidth = 3f
                    )
                }
        )
    }
}

@Composable
private fun StartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "button")
    val glowIntensity by infiniteTransition.animateFloat(
        initialValue = 0.7f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow"
    )

    Box(
        modifier = modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Turquoise,
                        ElectricBlue,
                        Turquoise
                    )
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable(
                onClick = onClick
            )
            .drawBehind {
                // Glow efekti
                drawRoundRect(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Turquoise.copy(alpha = glowIntensity * 0.4f),
                            Color.Transparent
                        )
                    ),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(20f, 20f)
                )
            }
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Şimdi Başla",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    WatchSyncTheme(darkTheme = true) {
        LoginScreen()
    }
}
