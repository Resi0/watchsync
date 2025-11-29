package com.example.watchsync.data.model

/**
 * Bir kullanıcıyı temsil eden, tüm alanları tamamlanmış ve sabitlenmiş veri sınıfı.
 */
data class User(
    val id: String,
    val username: String,
    val bio: String,
    val profileImageUrl: String,
    val age: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val nickname: String = "",
    val credits: Int = 0,
    val city: String = "",
    val country: String = "",
    val followerIds: List<String> = emptyList(),
    val followingIds: List<String> = emptyList(),
    val profileImages: List<String> = emptyList()
)
