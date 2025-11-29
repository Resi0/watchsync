package com.example.watchsync.presentation.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Headphones
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.watchsync.data.model.ChatRoom
import com.example.watchsync.ui.theme.ElectricBlue
import com.example.watchsync.ui.theme.Turquoise

@Composable
fun RoomCard(
    room: ChatRoom,
    modifier: Modifier = Modifier
) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1F2639),
            Color(0xFF151A27)
        )
    )

    val activeBorder = Brush.linearGradient(
        colors = listOf(
            Turquoise,
            ElectricBlue
        )
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(gradientBrush)
        ) {
            
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                // Üst Kısım: CANLI Etiketi ve Katılımcı Sayısı
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(
                                color = Color(0xFFFF3B30).copy(alpha = 0.2f),
                                shape = RoundedCornerShape(100)
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(Color(0xFFFF3B30), CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "CANLI",
                            color = Color(0xFFFF3B30),
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Headphones,
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.6f),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${room.participantCount}",
                            color = Color.White.copy(alpha = 0.6f),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Oda Başlığı
                Text(
                    text = room.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = (-0.5).sp
                    ),
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Konu ve Dizi/Film Bilgisi
                Row(verticalAlignment = Alignment.CenterVertically) {
                     room.watchable?.let { watchable ->
                         Text(
                             text = watchable.title,
                             style = MaterialTheme.typography.bodyMedium.copy(color = Turquoise, fontWeight = FontWeight.Medium),
                             modifier = Modifier.padding(end = 8.dp)
                         )
                         Box(
                             modifier = Modifier
                                 .size(4.dp)
                                 .background(Color.White.copy(alpha = 0.3f), CircleShape)
                         )
                         Spacer(modifier = Modifier.width(8.dp))
                     }
                    
                    Text(
                        text = room.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.7f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Alt Kısım: Oluşturan ve Konuşmacılar (Şimdilik statik veya FakeData'dan geliştirilebilir)
                // Basitlik için sadece "Katıl" butonunu gösteriyorum, creator bilgisi ChatRoom modeline tam olarak eklenirse burası zenginleşebilir.
                // FakeData'da creator bilgisi yoktu, o yüzden burayı sade tutuyoruz veya FakeData'yı daha da zenginleştirmek gerekebilir.
                // Şimdilik sadece "Katıl" butonu ve genel düzen.
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End // Sadece sağa yasla
                ) {
                    // Join Button
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(100))
                            .background(
                                Brush.horizontalGradient(listOf(ElectricBlue, Turquoise))
                            )
                            .clickable { /* Join Room */ }
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        Text(
                            text = "Katıl",
                            color = Color.White,
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }
        }
    }
}
