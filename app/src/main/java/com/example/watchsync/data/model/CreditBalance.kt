package com.example.watchsync.data.model

data class CreditBalance(
    val totalCredits: Int = 0,
    val dailyCreditsRemaining: Int = 10, // Varsayılan günlük hak
    val lastDailyReset: Long = System.currentTimeMillis() // Son sıfırlanma zamanı
)
