package com.example.watchsync.presentation.explore

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.watchsync.data.FakeData
import com.example.watchsync.data.model.Tweet
import com.example.watchsync.ui.theme.Turquoise

@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableStateOf("Keşfet") }
    var searchQuery by remember { mutableStateOf("") }

    val tweets = remember(selectedCategory, searchQuery) {
        val initialTweets = when (selectedCategory) {
            "Film" -> FakeData.getTweetsByCategory("Film")
            "Dizi" -> FakeData.getTweetsByCategory("Dizi")
            else -> FakeData.getExploreTweets()
        }

        if (searchQuery.isBlank()) {
            initialTweets
        } else {
            val query = searchQuery.trim().lowercase()
            initialTweets.filter { tweet ->
                tweet.content.lowercase().contains(query) ||
                tweet.user.username.lowercase().contains(query) ||
                tweet.realName.lowercase().contains(query) ||
                (tweet.watchable?.title?.lowercase()?.contains(query) == true)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0A0E27))
    ) {
        // Üst bar ve Arama
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(40.dp).background(Brush.horizontalGradient(listOf(Turquoise, Color(0xFF00D4FF))), RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("W", style = MaterialTheme.typography.headlineMedium.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold), color = Color.White)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text("WatchSync", style = MaterialTheme.typography.headlineMedium.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold), color = Color.White)
            }

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                placeholder = { Text("Ara...", color = Color.White.copy(alpha = 0.5f)) },
                leadingIcon = { Icon(Icons.Rounded.Search, "Ara", tint = Color.White.copy(alpha = 0.7f)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = Turquoise,
                    unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
                    focusedContainerColor = Color(0xFF1A1F2E),
                    unfocusedContainerColor = Color(0xFF1A1F2E)
                ),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
            )
        }

        // Kategoriler
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Keşfet", "Film", "Dizi").forEach { category ->
                CategoryChip(
                    text = category,
                    isSelected = selectedCategory == category,
                    onClick = { selectedCategory = category }
                )
            }
        }

        // Tweet listesi
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tweets, key = { it.id }) { tweet ->
                TweetCard(tweet = tweet)
            }
        }
    }
}

@Composable
private fun CategoryChip(text: String, isSelected: Boolean, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        colors = androidx.compose.material3.ButtonDefaults.textButtonColors(contentColor = if (isSelected) Turquoise else Color.White.copy(alpha = 0.7f)),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(text, style = MaterialTheme.typography.bodyMedium.copy(fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal))
    }
}

@Composable
private fun TweetCard(tweet: Tweet, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1F2E))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(tweet.user.profileImageUrl).crossfade(true).build(),
                contentDescription = tweet.user.username,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(48.dp).clip(CircleShape)
            )

            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(tweet.user.username, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), color = Color.White)
                    Text("@${tweet.realName}", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.6f))
                    Text("· ${tweet.timestamp}", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.5f))
                }

                tweet.watchable?.let {
                    Text("📺 ${it.title}", style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium), color = Turquoise, modifier = Modifier.padding(bottom = 2.dp))
                }

                Text(tweet.content, style = MaterialTheme.typography.bodyMedium, color = Color.White, lineHeight = 20.sp)

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    TweetActionButton(icon = Icons.Default.Favorite, text = tweet.likeCount.toString())
                    TweetActionButton(icon = Icons.Default.Repeat, text = tweet.retweetCount.toString())
                    TweetActionButton(icon = Icons.Default.Comment, text = tweet.commentCount.toString())
                    Icon(Icons.Default.Share, contentDescription = "Paylaş", tint = Color.White.copy(alpha = 0.5f))
                }
            }
        }
    }
}

@Composable
private fun TweetActionButton(icon: ImageVector, text: String, onClick: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = Color.White.copy(alpha = 0.6f), modifier = Modifier.size(18.dp))
        Text(text, style = MaterialTheme.typography.bodySmall, color = Color.White.copy(alpha = 0.6f))
    }
}
