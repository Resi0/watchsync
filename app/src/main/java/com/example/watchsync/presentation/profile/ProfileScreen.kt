package com.example.watchsync.presentation.profile

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.runtime.collectAsState
import com.example.watchsync.data.FakeData
import com.example.watchsync.data.model.User
import com.example.watchsync.data.model.UserLibrary
import com.example.watchsync.data.model.Watchable
import com.example.watchsync.data.repository.LibraryRepository
import com.example.watchsync.data.repository.LikeRepository
import com.example.watchsync.ui.theme.Turquoise

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(
    onEditProfileClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onNavigateToFollowers: (String, FollowType) -> Unit = { _, _ -> },
    onNavigateToShowDetail: (Watchable) -> Unit = {},
    ratings: Map<String, Int> = emptyMap(), // Parametre eklendi
    modifier: Modifier = Modifier
) {
    val user = remember {
        FakeData.findUserById("user_1") ?: User(
            id = "current_user",
            username = "Kullanıcı Bulunamadı",
            bio = "",
            profileImageUrl = ""
        )
    }

    val profileImages = remember(user.profileImages, user.profileImageUrl) {
        if (user.profileImages.isNotEmpty()) user.profileImages else listOf(user.profileImageUrl)
    }

    var selectedCategory by remember { mutableStateOf("Kütüphane") }
    val favoriteWatchables = remember { FakeData.getFavoriteWatchables(user.id) }
    val followerCount = remember { FakeData.getFollowerCount(user.id) }
    val followingCount = remember { FakeData.getFollowingCount(user.id) }
    val likeCount by LikeRepository.likeCount.collectAsState()
    
    // Library Repository ve State
    val libraryRepository = LibraryRepository // Singleton object, doğrudan erişim
    var userLibrary by remember { mutableStateOf(UserLibrary()) }

    // Oylama sonuçlarını kütüphaneye ekle
    LaunchedEffect(ratings) {
        if (ratings.isNotEmpty()) {
            ratings.forEach { (watchableId, rating) ->
                val watchable = FakeData.getAllWatchables().find { it.id == watchableId }
                if (watchable != null) {
                    libraryRepository.addRatedWatchable(watchable, rating)
                }
            }
        }
        // Kütüphaneyi yükle
        userLibrary = libraryRepository.getUserLibrary()
    }

    LazyColumn(
        modifier = modifier.fillMaxSize().background(Color.Black)
    ) {
        // Profil Resimleri ve Kullanıcı Bilgileri
        item {
            Box {
                ProfileImagesSection(
                    profileImages = profileImages,
                    onEditClick = onEditProfileClick,
                    onSettingsClick = onSettingsClick
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = 16.dp, vertical = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = user.username,
                            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                            color = Color.White
                        )
                        if (user.nickname.isNotEmpty()) {
                            Text(
                                text = "@${user.nickname}",
                                style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp, fontWeight = FontWeight.Normal),
                                color = Turquoise
                            )
                        }
                    }
                    Text(
                        text = user.bio,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(alpha = 0.8f),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        FollowCounter(label = "Takipçi", count = followerCount) { onNavigateToFollowers(user.id, FollowType.FOLLOWERS) }
                        FollowCounter(label = "Takip Edilen", count = followingCount) { onNavigateToFollowers(user.id, FollowType.FOLLOWING) }
                        FollowCounter(label = "Beğeni", count = likeCount) { }
                    }
                }
            }
        }

        // Kategoriler
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("Kütüphane", "Gönderiler", "Yanıtlar", "Favoriler").forEach { category ->
                    CategoryChip(
                        text = category,
                        isSelected = selectedCategory == category,
                        onClick = { selectedCategory = category }
                    )
                }
            }
        }

        // Kategori içeriği
        item {
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                when (selectedCategory) {
                    "Kütüphane" -> {
                        LibrarySection(
                            library = userLibrary,
                            onShowClick = onNavigateToShowDetail
                        )
                    }
                    "Gönderiler" -> {
                        EmptyState(text = "Henüz hiç gönderi yok.")
                    }
                    "Yanıtlar" -> {
                        EmptyState(text = "Henüz hiç yanıt yok.")
                    }
                    "Favoriler" -> {
                        FavoriteWatchablesSection(favoriteWatchables = favoriteWatchables)
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyState(text: String) {
    Box(
        modifier = Modifier.fillMaxWidth().height(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = Color.White.copy(alpha = 0.6f))
    }
}

@Composable
private fun FollowCounter(label: String, count: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier.clickable(onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            color = Color.White
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
            color = Color.White.copy(alpha = 0.7f)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ProfileImagesSection(
    profileImages: List<String>,
    onEditClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val pagerState = rememberPagerState { profileImages.size }

    Box(
        modifier = Modifier.fillMaxWidth().height(350.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(profileImages.getOrNull(page)).crossfade(true).build(),
                contentDescription = "Profil fotoğrafı ${page + 1}",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)))))

        Row(
            modifier = Modifier.align(Alignment.TopEnd).padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Profili Düzenle",
                tint = Color.White,
                modifier = Modifier.size(28.dp).clickable(onClick = onEditClick)
            )
            Icon(
                imageVector = Icons.Rounded.Settings,
                contentDescription = "Ayarlar",
                tint = Color.White,
                modifier = Modifier.size(28.dp).clickable(onClick = onSettingsClick)
            )
        }

        if (profileImages.size > 1) {
            Row(
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                profileImages.forEachIndexed { index, _ ->
                    Box(
                        modifier = Modifier.size(8.dp).clip(CircleShape).background(if (index == pagerState.currentPage) Color.White else Color.White.copy(alpha = 0.4f))
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryChip(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .background(
                color = if (isSelected) Turquoise.copy(alpha = 0.3f) else Color.White.copy(alpha = 0.1f),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal),
            color = if (isSelected) Turquoise else Color.White.copy(alpha = 0.8f)
        )
    }
}

@Composable
private fun FavoriteWatchablesSection(favoriteWatchables: List<Watchable>) {
    LazyRow(contentPadding = PaddingValues(vertical = 8.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items(favoriteWatchables, key = { it.id }) { watchable ->
            FavoriteWatchableItem(watchable = watchable)
        }
    }
}

@Composable
private fun FavoriteWatchableItem(
    watchable: Watchable,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.width(120.dp).height(180.dp).clip(RoundedCornerShape(12.dp)).clickable { /* TODO: Detay sayfasına git */ }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(watchable.posterUrl).crossfade(true).build(),
            contentDescription = watchable.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)))))
        Text(
            text = watchable.title,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp, fontWeight = FontWeight.SemiBold),
            color = Color.White,
            modifier = Modifier.align(Alignment.BottomStart).padding(8.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}