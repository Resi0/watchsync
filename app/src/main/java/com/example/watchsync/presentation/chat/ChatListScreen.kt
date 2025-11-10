package com.example.watchsync.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.watchsync.data.model.User
import com.example.watchsync.ui.theme.NightBlue
import com.example.watchsync.ui.theme.WatchSyncTheme

data class ChatItem(
    val user: User,
    val lastMessage: String,
    val timestamp: String // "1s önce", "5dk önce", "2sa önce" gibi
)

@Composable
fun ChatListScreen(
    onChatClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    // Sahte sohbet verileri
    val chatItems = remember {
        listOf(
            ChatItem(
                user = User(
                    id = "1",
                    username = "Ahmet",
                    bio = "Film ve dizi tutkunu",
                    profileImageUrl = "https://i.pravatar.cc/150?img=1"
                ),
                lastMessage = "Dizinin finali hakkında ne düşünüyorsun?",
                timestamp = "1s önce"
            ),
            ChatItem(
                user = User(
                    id = "2",
                    username = "Ayşe",
                    bio = "Bilim kurgu sever",
                    profileImageUrl = "https://i.pravatar.cc/150?img=5"
                ),
                lastMessage = "Bu filmi izledin mi? Çok etkileyici!",
                timestamp = "5dk önce"
            ),
            ChatItem(
                user = User(
                    id = "3",
                    username = "Mehmet",
                    bio = "Drama filmleri seviyorum",
                    profileImageUrl = "https://i.pravatar.cc/150?img=12"
                ),
                lastMessage = "Hafta sonu birlikte izleyelim mi?",
                timestamp = "15dk önce"
            ),
            ChatItem(
                user = User(
                    id = "4",
                    username = "Zeynep",
                    bio = "Fantastik dizi hayranı",
                    profileImageUrl = "https://i.pravatar.cc/150?img=9"
                ),
                lastMessage = "Yeni sezon çıkmış, izledin mi?",
                timestamp = "1sa önce"
            ),
            ChatItem(
                user = User(
                    id = "5",
                    username = "Can",
                    bio = "Aksiyon filmleri sever",
                    profileImageUrl = "https://i.pravatar.cc/150?img=33"
                ),
                lastMessage = "Bu sahne çok etkileyiciydi!",
                timestamp = "2sa önce"
            ),
            ChatItem(
                user = User(
                    id = "6",
                    username = "Elif",
                    bio = "Romantik komedi tutkunu",
                    profileImageUrl = "https://i.pravatar.cc/150?img=47"
                ),
                lastMessage = "Birlikte izleyelim mi?",
                timestamp = "3sa önce"
            ),
            ChatItem(
                user = User(
                    id = "7",
                    username = "Burak",
                    bio = "Gerilim filmleri seviyorum",
                    profileImageUrl = "https://i.pravatar.cc/150?img=20"
                ),
                lastMessage = "Son bölümü izledin mi?",
                timestamp = "1gün önce"
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
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Başlık
            Text(
                text = "Sohbetler",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp)
            )

            // Sohbet listesi
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                items(chatItems) { chatItem ->
                    ChatListItem(
                        chatItem = chatItem,
                        onClick = {
                            onChatClick(chatItem.user.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ChatListItem(
    chatItem: ChatItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(Color.White.copy(alpha = 0.05f))
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Sol: Dairesel profil resmi
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(chatItem.user.profileImageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = chatItem.user.username,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Orta: Kullanıcı adı ve son mesaj
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = chatItem.user.username,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = chatItem.lastMessage,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp
                ),
                color = Color.White.copy(alpha = 0.7f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        // Sağ: Son mesaj zamanı
        Text(
            text = chatItem.timestamp,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 12.sp
            ),
            color = Color.White.copy(alpha = 0.6f),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatListScreenPreview() {
    WatchSyncTheme(darkTheme = true) {
        ChatListScreen()
    }
}


