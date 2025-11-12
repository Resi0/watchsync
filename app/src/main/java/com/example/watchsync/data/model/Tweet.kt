package com.example.watchsync.data.model

/**
 * Keşfet ekranında gösterilecek bir tweet'i temsil eden veri sınıfı.
 */
data class Tweet(
    val id: String,
    val user: User, // Tweet'i atan kullanıcı
    val realName: String, // @kullaniciadi
    val timestamp: String, // "1s önce" gibi
    val content: String, // Tweet metni
    val watchable: Watchable? = null, // İlişkili film/dizi (opsiyonel)
    val hasSpoiler: Boolean = false,
    val likeCount: Int,
    val retweetCount: Int,
    val commentCount: Int
)
