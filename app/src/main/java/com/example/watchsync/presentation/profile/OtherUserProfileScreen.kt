package com.example.watchsync.presentation.profile

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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.watchsync.data.FakeData
import com.example.watchsync.data.model.RatedWatchable
import com.example.watchsync.data.model.User
import com.example.watchsync.data.model.Watchable
import com.example.watchsync.ui.theme.ElectricBlue
import com.example.watchsync.ui.theme.NightBlue
import com.example.watchsync.ui.theme.Turquoise
import com.example.watchsync.ui.theme.WatchSyncTheme

@Composable
fun OtherUserProfileScreen(
    userId: String = "1",
    onBackClick: () -> Unit = {},
    onConnectClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    // FakeData'dan kullanıcıyı bul
    val user = remember(userId) {
        FakeData.findUserById(userId)
    }

    // Ortak zevkler (sahte veri)
    val commonMovies = remember {
        listOf(
            Watchable(
                id = "1",
                title = "Dune",
                posterUrl = "https://image.tmdb.org/t/p/w500/9xjZS2rlVxm8SFx8kPC3aIGCOYQ.jpg",
                type = "Film"
            ),
            Watchable(
                id = "2",
                title = "Inception",
                posterUrl = "https://image.tmdb.org/t/p/w500/7d6eBwDHgW6I8I3c4h5c4o4F4z4.jpg",
                type = "Film"
            ),
            Watchable(
                id = "3",
                title = "Interstellar",
                posterUrl = "https://image.tmdb.org/t/p/w500/4u1PTSVg1lqH5S3V4r6nV3gJ3p3.jpg",
                type = "Film"
            ),
            Watchable(
                id = "4",
                title = "The Dark Knight",
                posterUrl = "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
                type = "Film"
            )
        )
    }

    // Kullanıcının oyladığı filmler (sahte veri)
    val ratedMovies = remember {
        listOf(
            RatedWatchable(
                watchable = Watchable(
                    id = "1",
                    title = "Dune",
                    posterUrl = "https://image.tmdb.org/t/p/w500/9xjZS2rlVxm8SFx8kPC3aIGCOYQ.jpg",
                    type = "Film"
                ),
                rating = 5
            ),
            RatedWatchable(
                watchable = Watchable(
                    id = "2",
                    title = "Oppenheimer",
                    posterUrl = "https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg",
                    type = "Film"
                ),
                rating = 5
            ),
            RatedWatchable(
                watchable = Watchable(
                    id = "3",
                    title = "The Dark Knight",
                    posterUrl = "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
                    type = "Film"
                ),
                rating = 5
            ),
            RatedWatchable(
                watchable = Watchable(
                    id = "4",
                    title = "Inception",
                    posterUrl = "https://image.tmdb.org/t/p/w500/7d6eBwDHgW6I8I3c4h5c4o4F4z4.jpg",
                    type = "Film"
                ),
                rating = 4
            ),
            RatedWatchable(
                watchable = Watchable(
                    id = "5",
                    title = "Interstellar",
                    posterUrl = "https://image.tmdb.org/t/p/w500/4u1PTSVg1lqH5S3V4r6nV3gJ3p3.jpg",
                    type = "Film"
                ),
                rating = 5
            ),
            RatedWatchable(
                watchable = Watchable(
                    id = "6",
                    title = "Game of Thrones",
                    posterUrl = "https://image.tmdb.org/t/p/w500/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                    type = "Dizi"
                ),
                rating = 5
            ),
            RatedWatchable(
                watchable = Watchable(
                    id = "7",
                    title = "Breaking Bad",
                    posterUrl = "https://image.tmdb.org/t/p/w500/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                    type = "Dizi"
                ),
                rating = 5
            ),
            RatedWatchable(
                watchable = Watchable(
                    id = "8",
                    title = "Stranger Things",
                    posterUrl = "https://image.tmdb.org/t/p/w500/1XDDXPXGiI6id7J1Gx6f5g3xXxr.jpg",
                    type = "Dizi"
                ),
                rating = 4
            )
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        NightBlue,
                        Color.Black
                    )
                )
            )
    ) {
        // Kullanıcı bulunamadıysa hata mesajı göster
        if (user == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Kullanıcı bulunamadı",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Aradığınız kullanıcı mevcut değil.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 16.sp
                    ),
                    color = Color.White.copy(alpha = 0.7f),
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
        } else {
            // Kullanıcı bulunduysa profil sayfasını göster
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                // Üst bölüm: Profil resmi, geri butonu ve bağlantı kur butonu
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    ) {
                        // Profil resmi
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(user.profileImageUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = user.username,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        // Gradient overlay (resmin üzerine)
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black.copy(alpha = 0.7f)
                                        )
                                    )
                                )
                        )

                        // Geri butonu (sol üst)
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Geri",
                            tint = Color.White,
                            modifier = Modifier
                                .padding(16.dp)
                                .size(32.dp)
                                .clickable(onClick = onBackClick)
                        )

                        // Bağlantı Kur butonu (sağ üst)
                        Button(
                            onClick = onConnectClick,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(16.dp)
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Turquoise,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(24.dp)
                        ) {
                            Text(
                                text = "Bağlantı Kur",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        // Kullanıcı adı ve biyografi (alt kısım)
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .fillMaxWidth()
                                .padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = user.username,
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = Color.White
                            )
                            Text(
                                text = user.bio,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = 14.sp
                                ),
                                color = Color.White.copy(alpha = 0.9f),
                                lineHeight = 20.sp
                            )
                        }
                    }
                }

                // Ortak Zevkleriniz bölümü
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 24.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Ortak Zevkleriniz",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.White
                        )

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            contentPadding = PaddingValues(horizontal = 4.dp)
                        ) {
                            items(commonMovies) { movie ->
                                CommonMoviePoster(
                                    movie = movie,
                                    onClick = {
                                        // TODO: Film detay sayfasına yönlendir
                                    }
                                )
                            }
                        }
                    }
                }

                // Kullanıcının oyladığı filmler listesi
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Oyladığı Filmler ve Diziler",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.White
                        )
                    }
                }

                items(ratedMovies, key = { it.watchable.id }) { movie ->
                    RatedMovieItem(
                        movie = movie,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

                // Alt boşluk
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
private fun CommonMoviePoster(
    movie: Watchable,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(100.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.posterUrl)
                .crossfade(true)
                .build(),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun RatedMovieItem(
    movie: RatedWatchable,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White.copy(alpha = 0.1f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Poster
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.watchable.posterUrl)
                .crossfade(true)
                .build(),
            contentDescription = movie.watchable.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(60.dp)
                .height(90.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        // Başlık ve puan
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = movie.watchable.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = movie.watchable.type,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 12.sp
                    ),
                    color = Turquoise.copy(alpha = 0.8f)
                )
                Text(
                    text = "•",
                    color = Color.White.copy(alpha = 0.5f)
                )
                Text(
                    text = "${movie.rating} / 5",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = Turquoise
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OtherUserProfileScreenPreview() {
    WatchSyncTheme(darkTheme = true) {
        OtherUserProfileScreen()
    }
}

