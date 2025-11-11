package com.example.watchsync.presentation.onboarding

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Undo
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.watchsync.data.FakeData
import com.example.watchsync.data.model.Watchable
import com.example.watchsync.ui.theme.ElectricBlue
import com.example.watchsync.ui.theme.NightBlue
import com.example.watchsync.ui.theme.Turquoise
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onComplete: (Map<String, Int>) -> Unit = {}
) {
    val levels = remember { FakeData.getOnboardingLevels() }
    var currentLevelIndex by remember { mutableIntStateOf(0) }
    var currentIndexInLevel by remember { mutableIntStateOf(0) }
    var ratings by remember { mutableStateOf<Map<String, Int>>(emptyMap()) }

    val currentWatchables = levels.getOrNull(currentLevelIndex) ?: emptyList()
    val currentWatchable = currentWatchables.getOrNull(currentIndexInLevel)

    val offsetX = remember { Animatable(0f) }
    val rotation = remember { Animatable(0f) }
    val alpha = remember { Animatable(1f) }
    val scope = rememberCoroutineScope()

    // Kart değiştiğinde animasyonları sıfırla
    LaunchedEffect(currentWatchable) {
        if (currentWatchable != null) {
            offsetX.snapTo(0f)
            rotation.snapTo(0f)
            alpha.snapTo(1f)
        }
    }

    fun handleAction(liked: Boolean?, watchable: Watchable) {
        scope.launch {
            // Rating'i kaydet
            val rating = when (liked) {
                true -> 5
                false -> 1
                null -> 0
            }
            if (rating > 0) {
                ratings = ratings + (watchable.id to rating)
            }

            // Animasyonu başlat
            launch { alpha.animateTo(0f, animationSpec = tween(300)) }
            launch {
                if (liked != null) {
                    offsetX.animateTo(if (liked) 1000f else -1000f, animationSpec = tween(300))
                    rotation.animateTo(if (liked) 20f else -20f, animationSpec = tween(300))
                }
            }

            // Bir sonraki adıma geç
            val nextIndex = currentIndexInLevel + 1
            if (nextIndex >= currentWatchables.size) {
                val nextLevel = currentLevelIndex + 1
                if (nextLevel >= levels.size) {
                    onComplete(ratings)
                } else {
                    currentLevelIndex = nextLevel
                    currentIndexInLevel = 0
                }
            } else {
                currentIndexInLevel = nextIndex
            }
        }
    }

    Scaffold(
        containerColor = Color.Transparent,
        modifier = modifier.fillMaxSize().background(Brush.verticalGradient(listOf(NightBlue, Color.Black)))
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Üst Başlık ve Seviye Göstergesi
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Zevk Serüveni",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Turquoise
                )
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Turquoise.copy(alpha = 0.2f),
                    border = BorderStroke(1.dp, Turquoise.copy(alpha = 0.5f))
                ) {
                    Text(
                        text = "Aşama ${currentLevelIndex + 1}/${levels.size}",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        color = Turquoise,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                }
            }

            // İlerleme Çubuğu
            LinearProgressIndicator(
                progress = { (currentIndexInLevel.toFloat() + 1) / currentWatchables.size.toFloat() },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp).height(8.dp).clip(RoundedCornerShape(4.dp)),
                color = Turquoise,
                trackColor = Color.White.copy(alpha = 0.2f)
            )

            // Kart Alanı
            Box(
                modifier = Modifier.weight(1f).padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                if (currentWatchable != null) {
                    key(currentWatchable.id) {
                        MovieRatingCard(
                            modifier = Modifier
                                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                                .rotate(rotation.value)
                                .alpha(alpha.value),
                            watchable = currentWatchable
                        )
                    }
                }
            }

            // Buton Alanı
            if (currentWatchable != null) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(24.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { handleAction(false, currentWatchable) },
                        modifier = Modifier.size(72.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935))
                    ) {
                        Icon(Icons.Rounded.Close, null, modifier = Modifier.size(36.dp))
                    }
                    Button(
                        onClick = { handleAction(null, currentWatchable) },
                        modifier = Modifier.size(64.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.5f))
                    ) {
                        Icon(Icons.AutoMirrored.Rounded.Undo, null, modifier = Modifier.size(32.dp))
                    }
                    Button(
                        onClick = { handleAction(true, currentWatchable) },
                        modifier = Modifier.size(72.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF43A047))
                    ) {
                        Icon(Icons.Rounded.Favorite, null, modifier = Modifier.size(36.dp))
                    }
                }
            } else {
                Box(
                    modifier = Modifier.weight(1f).padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Harika! Zevklerini öğrendik.",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        }
    }
}

@Composable
private fun MovieRatingCard(
    watchable: Watchable,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(watchable.posterUrl)
                .crossfade(true)
                .build(),
            contentDescription = watchable.title,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(24.dp))
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = watchable.title,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .background(
                    color = if (watchable.type == "Film") Turquoise.copy(alpha = 0.3f) else ElectricBlue.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 16.dp, vertical = 6.dp)
        ) {
            Text(
                text = watchable.type,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                color = if (watchable.type == "Film") Turquoise else ElectricBlue
            )
        }
    }
}
