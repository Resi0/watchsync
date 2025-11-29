package com.example.watchsync.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object LikeRepository {
    private val _likeCount = MutableStateFlow(0)
    val likeCount: StateFlow<Int> = _likeCount.asStateFlow()

    fun incrementLikeCount() {
        _likeCount.value = _likeCount.value + 1
    }

    fun getLikeCount(): Int = _likeCount.value
}

