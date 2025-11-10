package com.example.watchsync.presentation.onboarding

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Undo
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
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
    val watchables = remember { FakeData.getOnboardingWatchables() }
    var currentIndex by remember { mutableIntStateOf(0) }
    var ratings by remember { mutableStateOf<Map<String, Int>>(emptyMap()) }
    var isAnimating by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    
    // Screen width'i px cinsinden hesapla
    val screenWidthPx = remember(configuration) {
        with(density) { configuration.screenWidthDp.dp.toPx() }
    }

    // Kart animasyon state'leri
    val offsetX = remember { Animatable(0f) }
    val rotation = remember { Animatable(0f) }
    val alpha = remember { Animatable(1f) }

    fun handleAction(liked: Boolean?, watchable: Watchable) {
        if (isAnimating) return
        
        val rating = when (liked) {
            true -> 5  // Beğendim
            false -> 1 // Beğenmedim
            null -> 0  // İzlemedim
        }
        if (rating > 0) {
            ratings = ratings + (watchable.id to rating)
        }

        isAnimating = true
        val direction = when (liked) {
            true -> 1f      // Sağa (BEĞEN)
            false -> -1f    // Sola (GEÇ)
            null -> 0f      // Yukarı (ATLA) - fade out
        }

        scope.launch {
            if (direction != 0f) {
                // GEÇ veya BEĞEN: Kart ekran dışına çıkarken dönerek kayar
                val targetOffset = direction * screenWidthPx * 1.5f
                val targetRotation = direction * 30f
                
                kotlinx.coroutines.coroutineScope {
                    launch {
                        offsetX.animateTo(
                            targetValue = targetOffset,
                            animationSpec = tween(durationMillis = 300)
                        )
                    }
                    launch {
                        rotation.animateTo(
                            targetValue = targetRotation,
                            animationSpec = tween(durationMillis = 300)
                        )
                    }
                    launch {
                        alpha.animateTo(
                            targetValue = 0f,
                            animationSpec = tween(durationMillis = 300)
                        )
                    }
                }
            } else {
                // ATLA: Sadece fade out
                alpha.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = 200)
                )
            }

            // Animasyon tamamlandıktan sonra bir sonraki karta geç
            val nextIndex = currentIndex + 1
            if (nextIndex >= watchables.size) {
                onComplete(ratings)
            } else {
                currentIndex = nextIndex
                // State'leri sıfırla
                offsetX.snapTo(0f)
                rotation.snapTo(0f)
                alpha.snapTo(1f)
                isAnimating = false
            }
        }
    }

    Scaffold(
        containerColor = Color.Transparent,
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(NightBlue, Color(0xFF000000), Color(0xFF0A0A1A))
                )
            )
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Üst Başlık ve İlerleme Çubuğu
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Zevk Serüveni",
                    style = MaterialTheme.typography.displayMedium.copy(fontSize = 32.sp, fontWeight = FontWeight.Bold),
                    color = Turquoise,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Animasyonlu ilerleme çubuğu
                val progress by animateFloatAsState(
                    targetValue = if (watchables.isNotEmpty()) {
                        (currentIndex + 1).toFloat() / watchables.size.toFloat()
                    } else {
                        0f
                    },
                    animationSpec = tween(durationMillis = 500),
                    label = "progress_animation"
                )
                
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = Turquoise,
                    trackColor = Color.White.copy(alpha = 0.2f)
                )
            }

            // Kart Alanı - Animasyonlu kart
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                if (currentIndex < watchables.size) {
                    val currentWatchable = watchables[currentIndex]
                    key(currentWatchable.id) {
                        AnimatedMovieRatingCard(
                            watchable = currentWatchable,
                            offsetX = offsetX,
                            rotation = rotation,
                            alpha = alpha,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            // Buton Alanı
            if (currentIndex < watchables.size) {
                val currentWatchable = watchables[currentIndex]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Beğenmedim (Kırmızı, X)
                    Button(
                        onClick = { handleAction(false, currentWatchable) },
                        enabled = !isAnimating,
                        modifier = Modifier.size(72.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935), contentColor = Color.White)
                    ) {
                        Icon(imageVector = Icons.Rounded.Close, contentDescription = "Beğenmedim", modifier = Modifier.size(36.dp))
                    }

                    // İzlemedim (Gri)
                    Button(
                        onClick = { handleAction(null, currentWatchable) },
                        enabled = !isAnimating,
                        modifier = Modifier.size(64.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.5f), contentColor = Color.White)
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Rounded.Undo, contentDescription = "İzlemedim", modifier = Modifier.size(32.dp))
                    }

                    // Beğendim (Yeşil, Kalp)
                    Button(
                        onClick = { handleAction(true, currentWatchable) },
                        enabled = !isAnimating,
                        modifier = Modifier.size(72.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF43A047), contentColor = Color.White)
                    ) {
                        Icon(imageVector = Icons.Rounded.Favorite, contentDescription = "Beğendim", modifier = Modifier.size(36.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun AnimatedMovieRatingCard(
    watchable: Watchable,
    offsetX: Animatable<Float, androidx.compose.animation.core.AnimationVector1D>,
    rotation: Animatable<Float, androidx.compose.animation.core.AnimationVector1D>,
    alpha: Animatable<Float, androidx.compose.animation.core.AnimationVector1D>,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    
    Column(
        modifier = modifier
            .offset {
                IntOffset(
                    x = with(density) { offsetX.value.toDp().toPx().roundToInt() },
                    y = 0
                )
            }
            .rotate(rotation.value)
            .alpha(alpha.value),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Poster
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(watchable.posterUrl)
                .crossfade(true)
                .build(),
            contentDescription = watchable.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(24.dp))
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Başlık ve Type
        Text(
            text = watchable.title,
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold),
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
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 14.sp, fontWeight = FontWeight.SemiBold),
                color = if (watchable.type == "Film") Turquoise else ElectricBlue
            )
        }
    }
}
