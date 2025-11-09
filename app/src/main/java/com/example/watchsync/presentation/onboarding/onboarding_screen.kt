package com.example.watchsync.presentation.onboarding

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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
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
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

private enum class SwipeDirection { None, Like, Dislike, Skip }

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onComplete: (Map<String, Int>) -> Unit = {}
) {
    // Veri listesini bir daha değişmeyecek şekilde hatırlıyoruz.
    val watchables = remember { FakeData.getOnboardingWatchables() }
    // Değişken olan, sadece hangi kartın en üstte olduğu.
    var currentIndex by remember { mutableIntStateOf(0) }
    var ratings by remember { mutableStateOf<Map<String, Int>>(emptyMap()) }

    fun handleSwipe(action: SwipeDirection, watchable: Watchable) {
        val rating = when (action) {
            SwipeDirection.Dislike -> 1
            SwipeDirection.Skip -> 0
            SwipeDirection.Like -> 5
            else -> -1
        }
        if (rating > 0) {
            ratings = ratings + (watchable.id to rating)
        }
        val nextIndex = currentIndex + 1
        if (nextIndex >= watchables.size) {
            onComplete(ratings)
        } else {
            currentIndex = nextIndex
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxSize().background(
                Brush.verticalGradient(colors = listOf(NightBlue, Color(0xFF000000), Color(0xFF0A0A1A)))
            )
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Zevklerini Oluştur",
                    style = MaterialTheme.typography.displayMedium.copy(fontSize = 32.sp, fontWeight = FontWeight.Bold),
                    color = Turquoise,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "${currentIndex + 1} / ${watchables.size}",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp, fontWeight = FontWeight.Medium),
                    color = Color.White.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                // Sadece mevcut kartı çiziyoruz ve animasyonu ona uyguluyoruz.
                // key, Compose'a bunun yeni bir kart olduğunu ve eskiyi unutması gerektiğini söyler.
                key(currentIndex) {
                    val currentWatchable = watchables[currentIndex]
                    MovieRatingCard(
                        watchable = currentWatchable,
                        onDislike = { handleSwipe(SwipeDirection.Dislike, currentWatchable) },
                        onSkip = { handleSwipe(SwipeDirection.Skip, currentWatchable) },
                        onLike = { handleSwipe(SwipeDirection.Like, currentWatchable) },
                        modifier = Modifier.fillMaxWidth().height(600.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun MovieRatingCard(
    watchable: Watchable,
    onDislike: () -> Unit,
    onSkip: () -> Unit,
    onLike: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White.copy(alpha = 0.1f), shape = RoundedCornerShape(24.dp))
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = Color.White.copy(alpha = 0.1f), shape = RoundedCornerShape(16.dp))
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(watchable.posterUrl).crossfade(true).build(),
                    contentDescription = watchable.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
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

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onDislike, enabled = enabled, modifier = Modifier.size(64.dp), shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4444), contentColor = Color.White)
                ) {
                    Icon(imageVector = Icons.Rounded.Close, contentDescription = "Beğenmedim", modifier = Modifier.size(32.dp))
                }

                Button(
                    onClick = onSkip, enabled = enabled, modifier = Modifier.size(64.dp), shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.5f), contentColor = Color.White)
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Rounded.Undo, contentDescription = "İzlemedim", modifier = Modifier.size(32.dp))
                }

                Button(
                    onClick = onLike, enabled = enabled, modifier = Modifier.size(64.dp), shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50), contentColor = Color.White)
                ) {
                    Icon(imageVector = Icons.Rounded.Favorite, contentDescription = "Beğendim", modifier = Modifier.size(32.dp))
                }
            }
        }
    }
}
