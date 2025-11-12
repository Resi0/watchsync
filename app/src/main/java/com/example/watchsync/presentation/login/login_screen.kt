package com.example.watchsync.presentation.login

import androidx.compose.foundation.Canvas
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.watchsync.ui.theme.WatchSyncTheme
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

// Tasarımdaki ana renkler
private val TEXT_COLOR = Color.White
private val PRIMARY_GLOW_COLOR = Color(0xFF00F5D4) // Canlı Turkuaz
private val RED_GLOW_COLOR = Color(0xFFFF5E5B)   // Canlı Kırmızı/Koral
private val BACKGROUND_COLOR = Color(0xFF0C0C12) // Çok Koyu Lacivert/Siyah

@Composable
fun LoginScreen(
    onStartClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BACKGROUND_COLOR)
    ) {
        // Arka plan ağ animasyonu
        NetworkBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(64.dp))

            // Ana Başlık
            Text(
                text = "WatchSync",
                color = TEXT_COLOR,
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 38.sp
                )
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Ana İkon
            MainIcon(modifier = Modifier.size(180.dp))

            Spacer(modifier = Modifier.height(48.dp))

            // Slogan
            Text(
                text = "Film & Flört Bir Arada!",
                color = TEXT_COLOR,
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Zevklerine Uygun İnsanlarla Tanış, Senaryonuzu Yazın .",
                color = TEXT_COLOR.copy(alpha = 0.8f),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f)) // Butonu aşağı iter

            // Başla Butonu
            StartButton(onClick = onStartClick)

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun StartButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(28.dp)) // ÖNCE şekli kırpıyoruz
            .background( // SONRA arkaplanı veriyoruz
                Brush.horizontalGradient(
                    colors = listOf(Color(0xFFF96060), RED_GLOW_COLOR, Color(0xFFF96060))
                )
            )
            .clickable(onClick = onClick), // En son tıklanabilirlik
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Şimdi Başla!",
            color = TEXT_COLOR,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
private fun MainIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) { 
        val iconSize = size.minDimension
        val iconCenter = center

        // Film makarası
        drawFilmReel(
            center = Offset(iconCenter.x - iconSize * 0.1f, iconCenter.y),
            size = iconSize * 0.5f,
            color = PRIMARY_GLOW_COLOR
        )

        // Klaket
        drawClapperboard(
            center = Offset(iconCenter.x, iconCenter.y - iconSize * 0.2f),
            width = iconSize * 0.6f,
            height = iconSize * 0.4f,
            color = PRIMARY_GLOW_COLOR
        )

        // Kalp
        val heartCenter = Offset(iconCenter.x + iconSize * 0.25f, iconCenter.y - iconSize * 0.05f)
        drawHeart(
            center = heartCenter,
            size = iconSize * 0.25f,
            color = PRIMARY_GLOW_COLOR
        )

        // Bağlantı Çizgisi
        drawGlowingLine(
            start = Offset(iconCenter.x - iconSize * 0.1f, iconCenter.y + iconSize * 0.25f),
            end = heartCenter,
            color = PRIMARY_GLOW_COLOR
        )
    }
}

@Composable
private fun NetworkBackground() {
    val points = remember {
        (1..20).map {
            Offset(
                x = Random.nextFloat(),
                y = Random.nextFloat()
            )
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) { 
        val width = size.width
        val height = size.height

        // Noktaları Çiz
        points.forEach { point ->
            val screenPos = Offset(point.x * width, point.y * height)
            val isRed = Random.nextFloat() > 0.7f
            val color = if (isRed) RED_GLOW_COLOR else PRIMARY_GLOW_COLOR
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(color.copy(alpha = 0.3f), Color.Transparent),
                    center = screenPos,
                    radius = 20f
                ),
                radius = 20f,
                center = screenPos
            )
            drawCircle(color = color, radius = 5f, center = screenPos)
        }

        // Noktalar Arası Çizgiler
        for (i in points.indices) {
            for (j in (i + 1) until points.size) {
                val dist = (points[i] - points[j]).getDistance()
                if (dist < 0.2f) { // Sadece yakın noktaları bağla
                    drawLine(
                        color = PRIMARY_GLOW_COLOR.copy(alpha = 0.2f),
                        start = Offset(points[i].x * width, points[i].y * height),
                        end = Offset(points[j].x * width, points[j].y * height),
                        strokeWidth = 1f
                    )
                }
            }
        }
    }
}

// --- ÇİZİM YARDIMCI FONKSİYONLARI ---

private fun DrawScope.drawClapperboard(center: Offset, width: Float, height: Float, color: Color) {
    val topHeight = height * 0.3f
    val bottomHeight = height * 0.7f
    val angle = -15f
    
    // Üst parça
    rotate(angle, pivot = center) {
        drawRect(color, topLeft = Offset(center.x - width/2, center.y - height/2), size = Size(width, topHeight), style = Stroke(2.dp.toPx()))
    }

    // Alt parça
    drawRect(color, topLeft = Offset(center.x - width/2, center.y - height/2 + topHeight), size = Size(width, bottomHeight), style = Stroke(2.dp.toPx()))

    // Çizgiler
    for (i in 0..4) {
        val x = (center.x - width/2) + (i * width / 4)
        drawLine(color, start = Offset(x, center.y - height/2), end = Offset(x - 10, center.y - height/2 + topHeight), strokeWidth = 1.dp.toPx())
    }
}

private fun DrawScope.drawFilmReel(center: Offset, size: Float, color: Color) {
    val radius = size / 2
    drawCircle(color, radius = radius, center = center, style = Stroke(2.dp.toPx()))
    drawCircle(color, radius = radius * 0.3f, center = center, style = Stroke(2.dp.toPx()))
    for (i in 0..5) {
        val angle = (i * 60f) * (Math.PI / 180f).toFloat()
        val holeCenter = Offset(center.x + (radius * 0.65f) * cos(angle), center.y + (radius * 0.65f) * sin(angle))
        drawCircle(color, radius = size * 0.08f, center = holeCenter)
    }
}

private fun DrawScope.drawHeart(center: Offset, size: Float, color: Color) {
    val path = Path().apply {
        val halfSize = size / 2
        moveTo(center.x, center.y - halfSize * 0.3f)
        cubicTo(center.x + halfSize * 0.8f, center.y - halfSize * 0.9f, center.x + halfSize, center.y - halfSize * 0.3f, center.x, center.y + halfSize)
        cubicTo(center.x - halfSize, center.y - halfSize * 0.3f, center.x - halfSize * 0.8f, center.y - halfSize * 0.9f, center.x, center.y - halfSize * 0.3f)
        close()
    }
    drawPath(path, color, style = Stroke(2.dp.toPx()))
}

private fun DrawScope.drawGlowingLine(start: Offset, end: Offset, color: Color) {
    drawLine(color, start, end, strokeWidth = 2.dp.toPx())
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    WatchSyncTheme(darkTheme = true) {
        LoginScreen()
    }
}