package com.example.watchsync.presentation.login.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

// Popüler Film ve Dizi Afişleri - Geniş liste
private val moviePosters = listOf(
    "https://image.tmdb.org/t/p/w500/9xjZS2rlVxm8SFx8kPC3aIGCOYQ.jpg", // Dune
    "https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg", // Oppenheimer
    "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg", // The Dark Knight
    "https://image.tmdb.org/t/p/w500/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg", // Game of Thrones
    "https://image.tmdb.org/t/p/w500/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg", // Breaking Bad
    "https://image.tmdb.org/t/p/w500/7d6eBwDHgW6I8I3c4h5c4o4F4z4.jpg", // Inception
    "https://image.tmdb.org/t/p/w500/4u1PTSVg1lqH5S3V4r6nV3gJ3p3.jpg", // Interstellar
    "https://image.tmdb.org/t/p/w500/1XDDXPXGiI6id7J1Gx6f5g3xXxr.jpg", // Stranger Things
    "https://image.tmdb.org/t/p/w500/8Vt6mWEReuy4Of61eXV51AoYi5s.jpg", // The Last of Us
    "https://image.tmdb.org/t/p/w500/9f5sIJEgvUpFv0ozfA6TurGMMjH.jpg", // House of Dragon
    "https://image.tmdb.org/t/p/w500/39LR3vp5WQxZ3fRGKZgslR3g4fw.jpg", // The Matrix
    "https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg", // Parasite
    "https://image.tmdb.org/t/p/w500/3bhkrj58Vtu7enYsRolD1fZdja1.jpg", // The Godfather
    "https://image.tmdb.org/t/p/w500/5bFK5d3mVTAvBCY5QWIy7qWEHf4.jpg", // Spider-Man
    "https://image.tmdb.org/t/p/w500/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg", // Avengers
    "https://image.tmdb.org/t/p/w500/kDp1vUBnMpe8ak4rjgl3cLELqjU.jpg", // Barbie
    "https://image.tmdb.org/t/p/w500/39wmItIWsg5sZMyRUHLkWBcuVCM.jpg", // Spider-Man: No Way Home
    "https://image.tmdb.org/t/p/w500/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg", // Spider-Man: Across the Spider-Verse
    "https://image.tmdb.org/t/p/w500/4m1Au3YkjqsxF8wQ3fBTRl3xPCZ.jpg", // Black Panther
    "https://image.tmdb.org/t/p/w500/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", // Frozen
    "https://image.tmdb.org/t/p/w500/5VTN0pR8gcqV3EPUHHfMGuaJ1Eu.jpg", // The Crown
    "https://image.tmdb.org/t/p/w500/AtsgWh18W4G1G3T93i8e8a7I05G.jpg", // Pulp Fiction
    "https://image.tmdb.org/t/p/w500/2CAL2433ZeIihfX1Hb2139CX0pW.jpg", // Fight Club
    "https://image.tmdb.org/t/p/w500/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg", // Forrest Gump
    "https://image.tmdb.org/t/p/w500/1XDDXPXGiI6id7J1Gx6f5g3xXxr.jpg", // Stranger Things
    "https://image.tmdb.org/t/p/w500/8Vt6mWEReuy4Of61eXV51AoYi5s.jpg", // The Last of Us
    "https://image.tmdb.org/t/p/w500/9f5sIJEgvUpFv0ozfA6TurGMMjH.jpg", // House of Dragon
    "https://image.tmdb.org/t/p/w500/39LR3vp5WQxZ3fRGKZgslR3g4fw.jpg", // The Matrix
    "https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg", // Parasite
    "https://image.tmdb.org/t/p/w500/3bhkrj58Vtu7enYsRolD1fZdja1.jpg", // The Godfather
    "https://image.tmdb.org/t/p/w500/5bFK5d3mVTAvBCY5QWIy7qWEHf4.jpg", // Spider-Man
    "https://image.tmdb.org/t/p/w500/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg", // Avengers
    "https://image.tmdb.org/t/p/w500/kDp1vUBnMpe8ak4rjgl3cLELqjU.jpg", // Barbie
    "https://image.tmdb.org/t/p/w500/9xjZS2rlVxm8SFx8kPC3aIGCOYQ.jpg", // Dune
    "https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg", // Oppenheimer
    "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg", // The Dark Knight
    "https://image.tmdb.org/t/p/w500/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg", // Game of Thrones
    "https://image.tmdb.org/t/p/w500/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg", // Breaking Bad
    "https://image.tmdb.org/t/p/w500/7d6eBwDHgW6I8I3c4h5c4o4F4z4.jpg", // Inception
    "https://image.tmdb.org/t/p/w500/4u1PTSVg1lqH5S3V4r6nV3gJ3p3.jpg", // Interstellar
)

@Composable
fun MoviePosterBackground(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        // 5 sütunlu grid - ekranı tamamen doldurmak için, padding ve spacing yok
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            // Afişleri tekrar ederek ekranın tamamını doldur
            val columnsPerRow = 5
            val totalRows = 8 // Satır sayısı
            
            repeat(totalRows) { rowIndex ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), // Her satır eşit yükseklikte
                    horizontalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    repeat(columnsPerRow) { colIndex ->
                        val posterIndex = (rowIndex * columnsPerRow + colIndex) % moviePosters.size
                        PosterItem(
                            posterUrl = moviePosters[posterIndex],
                            index = rowIndex * columnsPerRow + colIndex
                        )
                    }
                }
            }
        }

        // Gradient overlay - merkeze doğru koyulaşan, form okunabilirliği için
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.4f),
                            Color.Black.copy(alpha = 0.6f),
                            Color.Black.copy(alpha = 0.75f)
                        ),
                        radius = 1500f
                    )
                )
        )
    }
}

@Composable
private fun RowScope.PosterItem(
    posterUrl: String,
    index: Int
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val infiniteTransition = rememberInfiniteTransition(label = "poster_$index")

    // Hafif pulse animasyonu
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.98f,
        targetValue = 1.02f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3000 + (index * 200),
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale_$index"
    )

    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxSize() // AspectRatio kaldırıldı, tam doldur
            .hoverable(interactionSource = interactionSource)
            .scale(if (isHovered) 1.08f else scale)
            .alpha(if (isHovered) 1f else 0.8f),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(posterUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Film afişi",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
