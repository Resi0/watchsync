package com.example.watchsync.data.model

data class Watchable(
    val id: String,
    val title: String,
    val posterUrl: String,
    val type: String, // "Film" veya "Dizi"
    val genres: List<String> = emptyList() // Film/Dizinin türleri
)


