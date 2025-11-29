package com.example.watchsync.presentation.library

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.watchsync.data.model.Episode
import com.example.watchsync.data.model.Season
import com.example.watchsync.data.model.Watchable
import com.example.watchsync.data.repository.LibraryRepository
import com.example.watchsync.ui.theme.Turquoise

@Composable
fun ShowDetailScreen(
    show: Watchable,
    onBackClick: () -> Unit
) {
    val library by LibraryRepository.userLibrary.collectAsState()
    val watchedContent = library.watched.find { it.watchable.id == show.id }
    val seasons = remember { LibraryRepository.getSeasonsForShow(show.id) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A0E27))
    ) {
        item {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(show.posterUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = show.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.9f))
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(20.dp)
                ) {
                    Text(
                        text = show.title,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White
                    )
                }
            }
        }

        // Sezonlar
        items(seasons) { season ->
            SeasonCard(
                season = season,
                showId = show.id,
                watchedEpisodes = watchedContent?.watchedEpisodes?.get(season.seasonNumber) ?: emptySet()
            )
        }
    }
}

@Composable
fun SeasonCard(
    season: Season,
    showId: String,
    watchedEpisodes: Set<Int>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1F2E))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Sezon ${season.seasonNumber}",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Bölümler
            season.episodes.forEach { episode ->
                EpisodeRow(
                    episode = episode,
                    isWatched = watchedEpisodes.contains(episode.episodeNumber),
                    onClick = {
                        LibraryRepository.toggleEpisodeWatched(
                            showId,
                            season.seasonNumber,
                            episode.episodeNumber
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun EpisodeRow(
    episode: Episode,
    isWatched: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            imageVector = if (isWatched) Icons.Rounded.CheckCircle else Icons.Rounded.Circle,
            contentDescription = null,
            tint = if (isWatched) Turquoise else Color.White.copy(alpha = 0.5f),
            modifier = Modifier.size(24.dp)
        )
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "${episode.episodeNumber}. ${episode.title}",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = if (isWatched) FontWeight.Bold else FontWeight.Normal
                ),
                color = if (isWatched) Color.White else Color.White.copy(alpha = 0.7f)
            )
            Text(
                text = "${episode.duration} dakika",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.5f)
            )
        }
    }
}

