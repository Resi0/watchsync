package com.example.watchsync.presentation.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.watchsync.data.FakeData
import com.example.watchsync.data.model.RecommendedWatchable
import com.example.watchsync.data.model.UserComment
import com.example.watchsync.data.model.Watchable
import com.example.watchsync.ui.theme.ElectricBlue
import com.example.watchsync.ui.theme.NightBlue
import com.example.watchsync.ui.theme.WatchSyncTheme

@Composable
fun HomeScreen(
    onNavigateToProfile: (String) -> Unit = {},
    onNavigateToChat: () -> Unit = {},
    ratings: Map<String, Int> = emptyMap(),
    modifier: Modifier = Modifier
) {
    val movieRecommendations = remember(ratings) {
        FakeData.getHomeScreenRecommendations(ratings)
    }
    val followedComments = remember {
        FakeData.getFollowedUsersComments()
    }
    val sciFiMovies = remember {
        FakeData.getWatchablesByGenre("Bilim Kurgu")
    }
    val comedyMovies = remember {
        FakeData.getWatchablesByGenre("Komedi")
    }
    val dramaMovies = remember {
        FakeData.getWatchablesByGenre("Drama")
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(NightBlue, Color.Black)
                )
            )
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "WatchSync",
                        style = MaterialTheme.typography.headlineLarge.copy(fontSize = 28.sp, fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                    IconButton(onClick = onNavigateToChat) {
                        Icon(Icons.Rounded.Email, "Mesajlar", tint = Color.White)
                    }
                }
            }

            item {
                FeaturedContentSection(
                    recommendations = movieRecommendations,
                    onMovieClick = { /* TODO */ }
                )
            }

            item {
                FollowedUsersCommentsSection(
                    comments = followedComments,
                    onProfileClick = onNavigateToProfile,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            item {
                GenreMoviesSection(
                    genre = "Bilim Kurgu",
                    movies = sciFiMovies,
                    onMovieClick = { /* TODO */ },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            item {
                GenreMoviesSection(
                    genre = "Komedi",
                    movies = comedyMovies,
                    onMovieClick = { /* TODO */ },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            item {
                GenreMoviesSection(
                    genre = "Drama",
                    movies = dramaMovies,
                    onMovieClick = { /* TODO */ },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
fun FeaturedContentSection(
    recommendations: List<RecommendedWatchable>,
    onMovieClick: (Watchable) -> Unit,
    modifier: Modifier = Modifier
) {
    if (recommendations.isEmpty()) return

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = "Sana Özel Öneriler",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(recommendations, key = { it.watchable.id }) { recommendation ->
                FeaturedMovieItem(
                    recommendation = recommendation,
                    onClick = { onMovieClick(recommendation.watchable) }
                )
            }
        }
    }
}

@Composable
fun FeaturedMovieItem(
    recommendation: RecommendedWatchable,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(targetValue = if (isPressed) 1.02f else 1f, animationSpec = tween(200), label = "scale")

    Row(
        modifier = modifier.width(340.dp).scale(scale).clickable(onClick = onClick, interactionSource = interactionSource, indication = null),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier.weight(1f).height(280.dp).clip(RoundedCornerShape(16.dp))
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(recommendation.watchable.posterUrl).crossfade(true).build(),
                contentDescription = recommendation.watchable.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier.align(Alignment.BottomStart).fillMaxWidth().background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)))).padding(12.dp)
            ) {
                Text(
                    text = recommendation.reason,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp, fontWeight = FontWeight.Medium),
                    color = Color.White
                )
            }
        }
        Box(
            modifier = Modifier.width(120.dp).height(280.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = recommendation.watchable.title.uppercase(),
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 20.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp),
                color = Color.White,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun FollowedUsersCommentsSection(
    comments: List<UserComment>,
    onProfileClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Takip Ettiklerinin Yorumları", style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp, fontWeight = FontWeight.Bold), color = Color.White)
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            comments.chunked(3).forEach { rowComments ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    rowComments.forEach { comment ->
                        Box(modifier = Modifier.weight(1f)) {
                            CommentItem(comment = comment, onClick = { onProfileClick(comment.user.id) })
                        }
                    }
                    repeat(3 - rowComments.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun CommentItem(
    comment: UserComment,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(targetValue = if (isPressed) 1.05f else 1f, animationSpec = tween(200), label = "scale")

    Column(
        modifier = modifier.fillMaxWidth().scale(scale),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth().height(120.dp).clip(RoundedCornerShape(12.dp))) {
            comment.watchable?.let {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(it.posterUrl).crossfade(true).build(),
                    contentDescription = it.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Box(modifier = Modifier.align(Alignment.TopEnd).padding(6.dp).size(40.dp).clip(CircleShape).clickable(onClick = onClick, interactionSource = interactionSource, indication = null)) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(comment.user.profileImageUrl).crossfade(true).build(),
                    contentDescription = comment.user.username,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        Text(comment.comment, style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp), color = Color.White, maxLines = 2, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
fun GenreMoviesSection(
    genre: String,
    movies: List<Watchable>,
    onMovieClick: (Watchable) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.White)) { append("") }
                withStyle(style = SpanStyle(color = ElectricBlue, fontWeight = FontWeight.Bold)) { append(genre) }
            },
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(movies, key = { it.id }) { movie ->
                MoviePosterItem(movie = movie, onClick = { onMovieClick(movie) })
            }
        }
    }
}

@Composable
fun MoviePosterItem(movie: Watchable, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(targetValue = if (isPressed) 1.05f else 1f, animationSpec = tween(200), label = "scale")

    Box(
        modifier = modifier.width(120.dp).height(180.dp).scale(scale).clip(RoundedCornerShape(12.dp)).clickable(onClick = onClick, interactionSource = interactionSource, indication = null)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(movie.posterUrl).crossfade(true).build(),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    WatchSyncTheme {
        HomeScreen()
    }
}
